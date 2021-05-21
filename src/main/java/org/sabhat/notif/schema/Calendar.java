package org.sabhat.notif.schema;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Calendar schema as per API setu See https://apisetu.gov.in/public/api/cowin
 * for more details.
 * 
 * @author sabhat
 *
 */
public class Calendar {

	@JsonProperty
	private CalendarCenter[] centers;

	public CalendarCenter[] getCenters() {
		return centers;
	}

	public void setCenters(CalendarCenter[] centers) {
		this.centers = centers;
	}

	@Override
	public String toString() {
		return "Calendar [centers=" + Arrays.toString(centers) + "]";
	}

}
