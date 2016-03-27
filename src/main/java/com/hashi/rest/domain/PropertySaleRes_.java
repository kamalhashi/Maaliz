package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-10T10:54:39.941+0300")
@StaticMetamodel(PropertySaleRes.class)
public class PropertySaleRes_ extends Product_ {
	public static volatile SingularAttribute<PropertySaleRes, String> propertySize;
	public static volatile SingularAttribute<PropertySaleRes, Integer> noBedrooms;
	public static volatile SingularAttribute<PropertySaleRes, Integer> noBathrooms;
	public static volatile SingularAttribute<PropertySaleRes, Double> ownerType;
	public static volatile SingularAttribute<PropertySaleRes, String> neighbourhood;
	public static volatile ListAttribute<PropertySaleRes, SaleResExtras> propertyExtras;
}
