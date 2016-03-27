package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-10T10:51:54.132+0300")
@StaticMetamodel(PropertyRentComm.class)
public class PropertyRentComm_ extends Product_ {
	public static volatile SingularAttribute<PropertyRentComm, String> propertySize;
	public static volatile SingularAttribute<PropertyRentComm, Integer> furnished;
	public static volatile SingularAttribute<PropertyRentComm, Integer> rentTime;
	public static volatile SingularAttribute<PropertyRentComm, Integer> ownerType;
	public static volatile SingularAttribute<PropertyRentComm, String> neighbourhood;
	public static volatile ListAttribute<PropertyRentComm, RentCommExtras> propertyExtras;
}
