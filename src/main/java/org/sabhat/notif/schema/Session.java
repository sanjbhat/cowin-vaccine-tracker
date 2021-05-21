package org.sabhat.notif.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Array of Hospitals inside named root node sessions.
 * 
 * @author sabhat
 *
 */
public class Session {

	@JsonProperty
	private Hospital[] sessions;

	public Hospital[] getSessions() {
		return sessions;
	}

	public void setSessions(Hospital[] sessions) {
		this.sessions = sessions;
	}
}
