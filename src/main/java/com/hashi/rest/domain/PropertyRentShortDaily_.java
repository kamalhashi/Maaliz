package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-27T14:56:59.838+0400")
@StaticMetamodel(PropertyRentShortDaily.class)
public class PropertyRentShortDaily_ extends Product_ {
	public static volatile SingularAttribute<PropertyRentShortDaily, String> propertySize;
	public static volatile SingularAttribute<PropertyRentShortDaily, Integer> noBedrooms;
	public static volatile SingularAttribute<PropertyRentShortDaily, Integer> noBathrooms;
	public static volatile SingularAttribute<PropertyRentShortDaily, Integer> furnished;
	public static volatile SingularAttribute<PropertyRentShortDaily, Integer> rentTime;
	public static volatile SingularAttribute<PropertyRentShortDaily, Integer> ownerType;
	public static volatile SingularAttribute<PropertyRentShortDaily, String> neighbourhood;
	public static volatile ListAttribute<PropertyRentShortDaily, RentShortDailyExtras> propertyExtras;
}
