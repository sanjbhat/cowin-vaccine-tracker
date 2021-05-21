package org.sabhat.notif.schema;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Session schema as per API setu See https://apisetu.gov.in/public/api/cowin
 * for more details.
 * 
 * @author sabhat
 *
 */
public class CalendarSession {
	
	@JsonProperty
	private String session_id;
	
	@JsonProperty
	private String date;
	
	@JsonProperty
	private int available_capacity;
	
	@JsonProperty
	private int min_age_limit;
	
	@JsonProperty
	private String vaccine;
	
	@JsonProperty
	private String[] slots;

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAvailable_capacity() {
		return available_capacity;
	}

	public void setAvailable_capacity(int available_capacity) {
		this.available_capacity = available_capacity;
	}

	public int getMin_age_limit() {
		return min_age_limit;
	}

	public void setMin_age_limit(int min_age_limit) {
		this.min_age_limit = min_age_limit;
	}

	public String getVaccine() {
		return vaccine;
	}

	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}

	public String[] getSlots() {
		return slots;
	}

	public void setSlots(String[] slots) {
		this.slots = slots;
	}

	@Override
	public String toString() {
		return "CalendarSession [session_id=" + session_id + ", date=" + date + ", available_capacity="
				+ available_capacity  + ", min_age_limit=" + min_age_limit + ", vaccine=" + vaccine
				+ ", slots=" + Arrays.toString(slots) + "]";
	}

}
