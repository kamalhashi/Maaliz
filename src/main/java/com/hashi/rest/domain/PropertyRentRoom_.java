package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-02T14:45:38.747+0300")
@StaticMetamodel(PropertyRentRoom.class)
public class PropertyRentRoom_ extends Product_ {
	public static volatile SingularAttribute<PropertyRentRoom, Integer> furnished;
	public static volatile SingularAttribute<PropertyRentRoom, Integer> rentTime;
	public static volatile SingularAttribute<PropertyRentRoom, Integer> ownerType;
	public static volatile SingularAttribute<PropertyRentRoom, Integer> noBathrooms;
	public static volatile SingularAttribute<PropertyRentRoom, String> neighbourhood;
}
