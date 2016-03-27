package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-10T10:52:46.845+0300")
@StaticMetamodel(PropertyRentRes.class)
public class PropertyRentRes_ extends Product_ {
	public static volatile SingularAttribute<PropertyRentRes, String> propertySize;
	public static volatile SingularAttribute<PropertyRentRes, Integer> noBedrooms;
	public static volatile SingularAttribute<PropertyRentRes, Integer> noBathrooms;
	public static volatile SingularAttribute<PropertyRentRes, Integer> furnished;
	public static volatile SingularAttribute<PropertyRentRes, Integer> rentTime;
	public static volatile SingularAttribute<PropertyRentRes, Integer> ownerType;
	public static volatile SingularAttribute<PropertyRentRes, String> neighbourhood;
	public static volatile ListAttribute<PropertyRentRes, RentResExtras> propertyExtras;
}
