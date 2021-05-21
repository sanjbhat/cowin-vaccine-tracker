package org.sabhat.notif.schema;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Calendar Center (hospital) schema as per API setu See
 * https://apisetu.gov.in/public/api/cowin for more details.
 * 
 * @author sabhat
 *
 */
public class CalendarCenter {
	@JsonProperty
	private Number center_id;

	@JsonProperty
	private String name;

	@JsonProperty
	private String address;

	@JsonProperty
	private String state_name;

	@JsonProperty
	private String district_name;

	@JsonProperty
	private String block_name;

	@JsonProperty
	private String pincode;

	@JsonProperty
	private String from;

	@JsonProperty
	private String to;

	@JsonProperty
	private Number lat;

	@JsonProperty("long")
	private Number longitude;

	@JsonProperty
	private String fee_type;

	@JsonProperty
	private CalendarSession[] sessions;

	public Number getCenter_id() {
		return center_id;
	}

	public void setCenter_id(Number center_id) {
		this.center_id = center_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getBlock_name() {
		return block_name;
	}

	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Number getLat() {
		return lat;
	}

	public void setLat(Number lat) {
		this.lat = lat;
	}

	public Number getLong() {
		return longitude;
	}

	public void setLong(Number longitude) {
		this.longitude = longitude;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public CalendarSession[] getSessions() {
		return sessions;
	}

	public void setSessions(CalendarSession[] calendarSessions) {
		this.sessions = calendarSessions;
	}

	@Override
	public String toString() {
		return "CalendarCenter [center_id=" + center_id + ", name=" + name + ", address=" + address + ", state_name="
				+ state_name + ", district_name=" + district_name + ", block_name=" + block_name + ", pincode="
				+ pincode + ", from=" + from + ", to=" + to + ", lat=" + lat + ", longitude=" + longitude
				+ ", fee_type=" + fee_type + ", calendarSessions=" + Arrays.toString(sessions) + "]";
	}

}
