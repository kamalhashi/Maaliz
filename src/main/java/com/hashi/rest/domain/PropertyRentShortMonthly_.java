package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-10T10:53:49.598+0300")
@StaticMetamodel(PropertyRentShortMonthly.class)
public class PropertyRentShortMonthly_ extends Product_ {
	public static volatile SingularAttribute<PropertyRentShortMonthly, String> propertySize;
	public static volatile SingularAttribute<PropertyRentShortMonthly, Integer> noBedrooms;
	public static volatile SingularAttribute<PropertyRentShortMonthly, Integer> noBathrooms;
	public static volatile SingularAttribute<PropertyRentShortMonthly, Integer> furnished;
	public static volatile SingularAttribute<PropertyRentShortMonthly, Integer> rentTime;
	public static volatile SingularAttribute<PropertyRentShortMonthly, Integer> ownerType;
	public static volatile SingularAttribute<PropertyRentShortMonthly, String> neighbourhood;
	public static volatile ListAttribute<PropertyRentShortMonthly, RentShortMonthlyExtras> propertyExtras;
}
