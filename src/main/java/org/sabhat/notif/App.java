package org.sabhat.notif;

import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sabhat.notif.schema.Calendar;
import org.sabhat.notif.schema.CalendarCenter;
import org.sabhat.notif.schema.CalendarSession;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Contains methods to make call to cowin site and send signal message.
 * Basically, the skeleton of this project.
 * 
 * @author sabhat
 *
 */
public class App {
	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {

		scanCowinCalendar();
	}

	/**
	 * Scans cowin site and sends message to each of the config present in
	 * application.properties
	 */
	public static void scanCowinCalendar() {

		try (InputStream input = App.class.getClassLoader().getResourceAsStream("application.properties")) {

			logger.info("Starting cowin website scan");

			Properties properties = new Properties();
			properties.load(input);

			String url = properties.getProperty("cowin.url");
			StringBuilder text = new StringBuilder(properties.getProperty("cowin.signal.textPrefix"));
			String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Integer minAge = Integer.parseInt(properties.getProperty("cowin.minage"));
			String configs[] = properties.getProperty("cowin.config.list").split(",");

			// for every configlist of pincode and phone number (each phone number might
			// want to subscribe to a different pincode)
			for (String config : configs) {

				String pincodes[] = properties.getProperty("cowin.pincode." + config).split(",");
				String signalProps[] = properties.getProperty("cowin.signal." + config).split(",");
				String phone = signalProps[0];
				String apikey = signalProps[1];
				// for each pincode
				for (String pincode : pincodes) {
					logger.info("Checking slots for pincode {} ", pincode);
					makeRequestCowinSlots(url, pincode, today, minAge, phone, apikey, text.toString(),
							properties.getProperty("signal.url"));
				}
			}

		} catch (Exception ex) {

			logger.error(ex);
		}
	}

	/**
	 * Make request to cowin site, filter the data based on age (configured in
	 * application.properties), send message
	 * 
	 * @param url         URL of cowin
	 * @param pincode     configured pincode sent as param with cowin request
	 * @param date        the date parameter for cowin request - is today's date by
	 *                    default
	 * @param minAge      age slot to check in cowin website
	 * @param phonenumber Phonenumber to send message to
	 * @param apikey      ApiKey for callmebot, for the given phonenumber
	 * @param text        Text content of signal message
	 * @param signalUrl   URL for callmebot to send signal message
	 */
	private static void makeRequestCowinSlots(String url, String pincode, String date, Integer minAge,
			String phonenumber, String apikey, String text, String signalUrl) {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try (CloseableHttpClient client = HttpClients.createDefault()) {

			URI cowinUri = new URIBuilder(new URI(url)).addParameter("pincode", pincode).addParameter("date", date)
					.build();
			String result = getCowinData(cowinUri);

			Calendar calendar = mapper.readValue(result, Calendar.class);
			List<CalendarCenter> centerList = new ArrayList<>();
			centerList.addAll(Arrays.asList(calendar.getCenters()));

			for (CalendarCenter center : centerList) {
				List<CalendarSession> availableHospitals = Arrays.asList(center.getSessions()).stream()
						.filter(hospital -> hospital.getMin_age_limit() == minAge)
						.filter(hospital -> hospital.getAvailable_capacity() > 0).collect(Collectors.toList());

				if (availableHospitals.size() > 0) {
					text += " " + pincode;
					text += " for date " + (availableHospitals.get(0).getDate());
					text += (" at center " + center.getName());
					logger.info("sending signal message to phone {} text = {}", phonenumber, text);
					URI signalUri = new URIBuilder(new URI(signalUrl)).addParameter("phone", phonenumber)
							.addParameter("apikey", apikey).addParameter("text", text).build();
					sendSignalMessage(signalUri);
					break;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e);
		}
	}

	/**
	 * Sends message to signal via callmebot api
	 * 
	 * @param uri request details
	 * @throws Exception
	 */
	private static void sendSignalMessage(URI uri) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		try (CloseableHttpClient client = HttpClients.createDefault()) {

			HttpGet request = new HttpGet();
			request.setURI(uri);
			try {
				client.execute(request,
						httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), String.class));

			} catch (Exception ex) {
				// ignore this error for now.
				// for some strange reason, this above way of executing get request and reading
				// the value works.
				// Only this sends the message.So do not change code.

			}

		}
	}

	/**
	 * Makes call to cowin api to get hospital details
	 * 
	 * @param uri request params
	 * @return
	 * @throws Exception
	 */
	private static String getCowinData(URI uri) throws Exception {

		try (CloseableHttpClient client = HttpClients.createDefault()) {

			HttpGet request = new HttpGet();
			request.setURI(uri);
			request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
			request.setHeader("Host", "cdn-api.co-vin.in");
			request.setHeader("Cache-Control", "no-cache");
			request.setHeader("method", "GET");
			request.setHeader("USER-AGENT",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");
			CloseableHttpResponse response = null;
			try {
				response = client.execute(request);

			} catch (Exception ex) {
				logger.error(ex);

			}
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			return result;

		}
	}
}
