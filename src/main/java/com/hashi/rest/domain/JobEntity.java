package com.hashi.rest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "JOBS")
public class JobEntity extends Product {

	@Column(name = "CAREER_LEVEL")
	private Integer careerLevel;
	@Column(name = "EMPLOYMENT_TYPE")
	private Integer employmentType;
	@Column(name = "WORK_EXPERIENCE")
	private Integer workExperience;
	@Column(name = "EDUCATION_LEVEL")
	private Integer educationLevel;
	@Column(name = "CV_REQUIRED")
	private Integer cvRequired;
	@Column(name = "MONTHLY_SALARY")
	private Integer monthSalary;
	@Column(name = "BENEFITS")
	private String benefits;
	@Column(name = "COMPANY_NAME")
	private String companyName;
	@Column(name = "JOB_LOCATION")
	private String jobLocation;
	@Column(name = "COMPANY_SIZE")
	private Integer companySize;
	@Column(name = "LISTED_BY")
	private Integer listedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "CLOSING_DATE", nullable = false)
	private Date closingDate;
	
	public Integer getCareerLevel() {
		return careerLevel;
	}
	public void setCareerLevel(Integer careerLevel) {
		this.careerLevel = careerLevel;
	}
	public Integer getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}
	public Integer getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(Integer workExperience) {
		this.workExperience = workExperience;
	}
	public Integer getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(Integer educationLevel) {
		this.educationLevel = educationLevel;
	}
	public Integer getCvRequired() {
		return cvRequired;
	}
	public void setCvRequired(Integer cvRequired) {
		this.cvRequired = cvRequired;
	}
	public Integer getMonthSalary() {
		return monthSalary;
	}
	public void setMonthSalary(Integer monthSalary) {
		this.monthSalary = monthSalary;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getCompanySize() {
		return companySize;
	}
	public void setCompanySize(Integer companySize) {
		this.companySize = companySize;
	}
	public Integer getListedBy() {
		return listedBy;
	}
	public void setListedBy(Integer listedBy) {
		this.listedBy = listedBy;
	}
	public Date getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	public String getJobLocation() {
		return jobLocation;
	}
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}	
}
