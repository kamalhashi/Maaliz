package com.hashi.rest.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-24T11:35:04.206+0300")
@StaticMetamodel(Profile.class)
public class Profile_ {
	public static volatile SingularAttribute<Profile, Long> profileId;
	public static volatile SingularAttribute<Profile, Integer> careerLevel;
	public static volatile SingularAttribute<Profile, String> currentPosition;
	public static volatile SingularAttribute<Profile, String> currentCompany;
	public static volatile SingularAttribute<Profile, String> coverLetter;
	public static volatile SingularAttribute<Profile, String> cvName;
	public static volatile SingularAttribute<Profile, String> nationality;
	public static volatile SingularAttribute<Profile, String> currentLocation;
	public static volatile SingularAttribute<Profile, String> imageName;
	public static volatile SingularAttribute<Profile, Integer> age;
	public static volatile SingularAttribute<Profile, User> user;
	public static volatile SingularAttribute<Profile, Long> version;
	public static volatile SingularAttribute<Profile, Date> created;
	public static volatile SingularAttribute<Profile, Date> updated;
}
