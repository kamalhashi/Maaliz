package com.hashi.rest.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hashi.util.Serializer;
import com.hashi.util.View;

@Entity
@Table(name = "USER_PROFILE")

public class Profile implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PROFILE_ID")
	private Long profileId;

	@Column(name = "CAREER_LEVEL")
	private Integer careerLevel;
	
	@Column(name = "CURRENT_POSITION")
	private String currentPosition;
	
	@Column(name = "CURRENT_COMPANY")
	private String currentCompany;
	
	@Column(name = "COVER_LETTER")
	private String coverLetter;
	
	@Column(name = "CV_NAME")
	private String cvName;
	
	@Column(name = "NATIONALITY")
	private String nationality;
	
	@Column(name = "CURRENT_LOCATION")
	private String currentLocation;
	
	@Column(name = "IMAGE_NAME")
	private String imageName;
	
	@Column(name = "AGE")
	private Integer age;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn( name = "USER_ID", referencedColumnName = "USER_ID")
	@JsonView(View.High.class)
	@JsonSerialize(using = Serializer.class)
	private User user;
	
	
	
	@Version
	@Column(name = "version")
	private Long version;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED", nullable = false)
	private Date created;

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED", nullable = false)
	private Date updated;
	
	public Profile(){}
	

	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

	public Long getProfileId() {
		return profileId;
	}

	public Integer getCareerLevel() {
		return careerLevel;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public User getUser() {
		return user;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public void setCareerLevel(Integer careerLevel) {
		this.careerLevel = careerLevel;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCurrentCompany() {
		return currentCompany;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public String getCvName() {
		return cvName;
	}

	public String getNationality() {
		return nationality;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentCompany(String currentCompany) {
		this.currentCompany = currentCompany;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Long getVersion() {
		return version;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}