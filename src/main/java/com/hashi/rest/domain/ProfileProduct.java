package com.hashi.rest.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "JOB_USER")
public class ProfileProduct implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "JOB_USER_ID")
	private Long jobUserId;
	

	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private Product product;
	
	
	 @ManyToOne(fetch=FetchType.EAGER)
	 @JoinColumn(name = "PROFILE_ID", referencedColumnName = "PROFILE_ID")
	 private Profile profile;


	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED", nullable = false)
	private Date created;


	@PrePersist
	protected void onCreate() {
		created = new Date();
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Product getProduct() {
		return product;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Long getJobUserId() {
		return jobUserId;
	}

	public void setJobUserId(Long jobUserId) {
		this.jobUserId = jobUserId;
	}

}
