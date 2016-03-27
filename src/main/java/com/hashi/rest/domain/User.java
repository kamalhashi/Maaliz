package com.hashi.rest.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hashi.rest.enums.MailType;
import com.hashi.util.View;

@Entity
@Table(name = "USERS")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")

public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long userId;
    
	@NotNull
	@Column(name = "FIRST_NAME")
	private String firstname;

	@NotNull
	@Column(name = "SURNAME")
	private String surname;

	@Column(name = "TELEPHONE")
	private String telephone;

	@NotNull
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "GENDER")
	private String gender;
	

	@Column(name = "PASSWORD")
	@JsonView(View.High.class)
	private String password;

	@Column(name = "RECEIVE_OFFERS")
	@JsonView(View.High.class)
	private String receiveOffers;

	@Column(name = "enabled")
	@JsonView(View.High.class)
	private boolean enabled;

	@JsonIgnore
	@OneToMany(mappedBy="user" , cascade=CascadeType.ALL , fetch = FetchType.LAZY)
	private Set<UserRole> userRole = new HashSet<UserRole>();
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL ,fetch = FetchType.LAZY)
	private Set<VerificationToken> verificationTokens = new HashSet<VerificationToken>();
	
	@Version
	@Column(name = "version")
	private Long version;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED", nullable = false)
	private Date created;

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED", nullable = false)
	private Date updated;



	public User() {
		super();
		this.enabled=false;
	}
	

	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReceiveOffers() {
		return receiveOffers;
	}

	public void setReceiveOffers(String receiveOffers) {
		this.receiveOffers = receiveOffers;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public synchronized void setUserRole(UserRole role) {
		 userRole.add(role);
	}
	public Set<VerificationToken> getVerificationTokens() {
		return verificationTokens;
	}

	 public synchronized void setVerificationTokens(VerificationToken token) {
        verificationTokens.add(token);
	}

	/**
     * If the user has a VerificationToken of type VerificationTokenType.lostPassword
     * that is active return it otherwise return null
     *
     * @return verificationToken
     */
    public VerificationToken getActiveLostPasswordToken() {
        return getActiveToken(MailType.LOST_PASSWORD);
    }
    
    
    private VerificationToken getActiveToken(MailType tokenType) {
        VerificationToken activeToken = null;
       for (VerificationToken token : getVerificationTokens()) {
           if (token.getTokenType().equals(tokenType)
                   && !token.hasExpired() && !token.isVerified()) {
               activeToken = token;
               break;
           }
       }
       return activeToken;
   }

}
