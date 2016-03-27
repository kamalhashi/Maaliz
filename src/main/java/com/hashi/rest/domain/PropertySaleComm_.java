package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-10T10:54:08.557+0300")
@StaticMetamodel(PropertySaleComm.class)
public class PropertySaleComm_ extends Product_ {
	public static volatile SingularAttribute<PropertySaleComm, String> propertySize;
	public static volatile SingularAttribute<PropertySaleComm, Integer> ownerType;
	public static volatile SingularAttribute<PropertySaleComm, String> neighbourhood;
	public static volatile ListAttribute<PropertySaleComm, SaleCommExtras> propertyExtras;
}
