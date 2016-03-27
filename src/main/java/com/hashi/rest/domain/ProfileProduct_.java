package com.hashi.rest.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-30T10:26:10.556+0300")
@StaticMetamodel(ProfileProduct.class)
public class ProfileProduct_ {
	public static volatile SingularAttribute<ProfileProduct, Long> jobUserId;
	public static volatile SingularAttribute<ProfileProduct, Product> product;
	public static volatile SingularAttribute<ProfileProduct, Profile> profile;
	public static volatile SingularAttribute<ProfileProduct, Date> created;
}
