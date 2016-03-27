package com.hashi.rest.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hashi.rest.enums.MailType;



@Entity
@Table(name = "VERIFICATION_TOKEN")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VerificationToken  implements Serializable{

	private final static Integer DEFAULT_TIME_TO_LIVE_IN_SECONDS = (60 * 60 * 24); //24 hourse

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "TOKEN")
	private String token;
	private boolean verified;

	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private User user;

	@Column(name = "TIME_CREATED")
	private Date timeCreated;
	@Column(name = "EXPIRATION_DATE")
	private Date expirationDate;
    
	@Column(name = "TOKEN_TYPE")
	@Enumerated(EnumType.STRING)
	private MailType tokenType;
	
	@Version
	@Column(name = "version")
	private Long version;

	public VerificationToken() {}

	public VerificationToken(User user, MailType tokenType) {
		this(user, tokenType, DEFAULT_TIME_TO_LIVE_IN_SECONDS);
	}

	public VerificationToken(User user, MailType tokenType, Integer timeToLiveInSeconds) {
		this.token = UUID.randomUUID().toString();
		this.user = user;
		this.timeCreated = new Date();
		this.expirationDate = new Date(System.currentTimeMillis() + (timeToLiveInSeconds * 1000L));
        this.tokenType = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean hasExpired() {
		return this.expirationDate != null && this.expirationDate.before(new Date());
	}

	public MailType getTokenType() {
		return tokenType;
	}

	public void setTokenType(MailType tokenType) {
		this.tokenType = tokenType;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
