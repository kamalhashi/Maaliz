package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-12T16:42:09.148+0300")
@StaticMetamodel(CountryEnglish.class)
public class CountryEnglish_ {
	public static volatile SingularAttribute<CountryEnglish, Integer> countryId;
	public static volatile SingularAttribute<CountryEnglish, String> countryName;
	public static volatile SingularAttribute<CountryEnglish, String> countryCode;
	public static volatile ListAttribute<CountryEnglish, CityEnglish> cities;
	public static volatile SingularAttribute<CountryEnglish, String> messageKey;
}
