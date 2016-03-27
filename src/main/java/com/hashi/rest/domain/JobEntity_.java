package com.hashi.rest.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-01T16:23:52.378+0300")
@StaticMetamodel(JobEntity.class)
public class JobEntity_ extends Product_ {
	public static volatile SingularAttribute<JobEntity, Integer> careerLevel;
	public static volatile SingularAttribute<JobEntity, Integer> employmentType;
	public static volatile SingularAttribute<JobEntity, Integer> workExperience;
	public static volatile SingularAttribute<JobEntity, Integer> educationLevel;
	public static volatile SingularAttribute<JobEntity, Integer> cvRequired;
	public static volatile SingularAttribute<JobEntity, Integer> monthSalary;
	public static volatile SingularAttribute<JobEntity, String> benefits;
	public static volatile SingularAttribute<JobEntity, String> companyName;
	public static volatile SingularAttribute<JobEntity, String> jobLocation;
	public static volatile SingularAttribute<JobEntity, Integer> companySize;
	public static volatile SingularAttribute<JobEntity, Integer> listedBy;
	public static volatile SingularAttribute<JobEntity, Date> closingDate;
}
