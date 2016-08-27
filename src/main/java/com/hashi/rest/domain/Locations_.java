package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-08-27T11:54:08.165+0300")
@StaticMetamodel(Locations.class)
public class Locations_ {
	public static volatile SingularAttribute<Locations, Long> locationId;
	public static volatile SingularAttribute<Locations, String> country;
	public static volatile SingularAttribute<Locations, String> city;
	public static volatile SingularAttribute<Locations, Double> latitude;
	public static volatile SingularAttribute<Locations, Double> longtitude;
	public static volatile SingularAttribute<Locations, Product> product;
	public static volatile SingularAttribute<Locations, String> neighborhood;
}
