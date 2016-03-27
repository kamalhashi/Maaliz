package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-11T16:23:16.402+0300")
@StaticMetamodel(CityEnglish.class)
public class CityEnglish_ {
	public static volatile SingularAttribute<CityEnglish, Long> cityId;
	public static volatile SingularAttribute<CityEnglish, String> cityName;
	public static volatile SingularAttribute<CityEnglish, CountryEnglish> country;
}
