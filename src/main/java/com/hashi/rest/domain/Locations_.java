package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-12-09T09:03:59.951+0300")
@StaticMetamodel(Locations.class)
public class Locations_ {
	public static volatile SingularAttribute<Locations, Long> locationId;
	public static volatile SingularAttribute<Locations, String> country;
	public static volatile SingularAttribute<Locations, String> city;
	public static volatile SingularAttribute<Locations, String> region;
	public static volatile SingularAttribute<Locations, String> routeName;
	public static volatile SingularAttribute<Locations, Double> latitude;
	public static volatile SingularAttribute<Locations, Double> longtitude;
	public static volatile SingularAttribute<Locations, Product> product;
}
