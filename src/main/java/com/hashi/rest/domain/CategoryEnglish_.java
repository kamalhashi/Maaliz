package com.hashi.rest.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-13T13:03:14.635+0300")
@StaticMetamodel(CategoryEnglish.class)
public class CategoryEnglish_ {
	public static volatile SingularAttribute<CategoryEnglish, Long> categoryId;
	public static volatile SingularAttribute<CategoryEnglish, String> categoryName;
	public static volatile SingularAttribute<CategoryEnglish, String> categoryDescription;
	public static volatile SingularAttribute<CategoryEnglish, Integer> lft;
	public static volatile SingularAttribute<CategoryEnglish, Integer> rgt;
	public static volatile SingularAttribute<CategoryEnglish, Integer> depth;
	public static volatile SingularAttribute<CategoryEnglish, Boolean> isSubcategory;
}
