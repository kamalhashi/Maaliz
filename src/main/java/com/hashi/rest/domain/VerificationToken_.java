package com.hashi.rest.domain;

import com.hashi.rest.enums.MailType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-06T08:52:28.499+0300")
@StaticMetamodel(VerificationToken.class)
public class VerificationToken_ {
	public static volatile SingularAttribute<VerificationToken, Long> id;
	public static volatile SingularAttribute<VerificationToken, String> token;
	public static volatile SingularAttribute<VerificationToken, Boolean> verified;
	public static volatile SingularAttribute<VerificationToken, User> user;
	public static volatile SingularAttribute<VerificationToken, Date> timeCreated;
	public static volatile SingularAttribute<VerificationToken, Date> expirationDate;
	public static volatile SingularAttribute<VerificationToken, MailType> tokenType;
	public static volatile SingularAttribute<VerificationToken, Long> version;
}
