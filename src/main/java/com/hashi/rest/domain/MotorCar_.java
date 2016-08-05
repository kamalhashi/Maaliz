package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-08-05T11:11:18.781+0300")
@StaticMetamodel(MotorCar.class)
public class MotorCar_ extends Product_ {
	public static volatile SingularAttribute<MotorCar, Integer> yearMade;
	public static volatile SingularAttribute<MotorCar, Integer> kilometers;
	public static volatile SingularAttribute<MotorCar, Integer> bodyType;
	public static volatile SingularAttribute<MotorCar, String> model;
	public static volatile SingularAttribute<MotorCar, Integer> colour;
	public static volatile SingularAttribute<MotorCar, Integer> transmissionType;
	public static volatile SingularAttribute<MotorCar, Integer> fuelType;
	public static volatile SingularAttribute<MotorCar, Integer> sellerType;
	public static volatile SingularAttribute<MotorCar, Double> engineSize;
	public static volatile ListAttribute<MotorCar, CarExtras> carExtras;
}
