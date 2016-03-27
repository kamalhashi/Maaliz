package com.hashi.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSIFIED_LOST_FOUND")
public class ClassifiedLostFound extends Product implements Serializable {

	@Column(name = "LAST_SEEN")
	private String lastSeen;

	public String getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}

}
