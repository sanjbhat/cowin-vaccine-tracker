package org.sabhat.notif.schema;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Hospital schema as per API setu See https://apisetu.gov.in/public/api/cowin
 * for more details.
 * 
 * @author sabhat
 *
 */
public class Hospital {

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
	private String session_id;

	@JsonProperty
	private String date;

	@JsonProperty
	private String available_capacity;

	@JsonProperty
	private String fee;

	@JsonProperty
	private int min_age_limit;

	@JsonProperty
	private String vaccine;

	@JsonProperty
	private String[] slots;

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

	public String getAvailable_capacity() {
		return available_capacity;
	}

	public void setAvailable_capacity(String available_capacity) {
		this.available_capacity = available_capacity;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
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
		return "Hospital [center_id=" + center_id + ", name=" + name + ", address=" + address + ", state_name="
				+ state_name + ", district_name=" + district_name + ", block_name=" + block_name + ", pincode="
				+ pincode + ", from=" + from + ", to=" + to + ", lat=" + lat + ", longitude=" + longitude
				+ ", fee_type=" + fee_type + ", session_id=" + session_id + ", date=" + date + ", available_capacity="
				+ available_capacity + ", fee=" + fee + ", min_age_limit=" + min_age_limit + ", vaccine=" + vaccine
				+ ", slots=" + Arrays.toString(slots) + "]";
	}

}
