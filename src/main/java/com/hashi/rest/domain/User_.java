package com.hashi.rest.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-08-05T11:15:39.657+0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> surname;
	public static volatile SingularAttribute<User, String> telephone;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> gender;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> receiveOffers;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SetAttribute<User, UserRole> userRole;
	public static volatile SetAttribute<User, VerificationToken> verificationTokens;
	public static volatile SingularAttribute<User, Long> version;
	public static volatile SingularAttribute<User, Date> created;
	public static volatile SingularAttribute<User, Date> updated;
}
