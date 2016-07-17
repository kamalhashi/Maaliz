package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-13T13:03:14.685+0300")
@StaticMetamodel(CategorySomali.class)
public class CategorySomali_ {
	public static volatile SingularAttribute<CategorySomali, Long> categoryId;
	public static volatile SingularAttribute<CategorySomali, String> categoryName;
	public static volatile SingularAttribute<CategorySomali, String> categoryDescription;
	public static volatile SingularAttribute<CategorySomali, Integer> lft;
	public static volatile SingularAttribute<CategorySomali, Integer> rgt;
	public static volatile SingularAttribute<CategorySomali, Integer> depth;
	public static volatile SingularAttribute<CategorySomali, Boolean> isSubcategory;
}
