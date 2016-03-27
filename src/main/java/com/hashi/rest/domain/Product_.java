package com.hashi.rest.domain;

import com.hashi.rest.enums.ImageStatus;
import com.hashi.rest.enums.LanguageType;
import com.hashi.rest.enums.LiveType;
import com.hashi.rest.enums.ProductPriority;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-31T15:35:13.899+0300")
@StaticMetamodel(Product.class)
public class Product_ {
	public static volatile SingularAttribute<Product, Long> productId;
	public static volatile SingularAttribute<Product, String> productTitle;
	public static volatile SingularAttribute<Product, Double> productPrice;
	public static volatile SingularAttribute<Product, String> productDescription;
	public static volatile SingularAttribute<Product, String> coverPhoto;
	public static volatile SingularAttribute<Product, String> logoPhoto;
	public static volatile SingularAttribute<Product, LiveType> liveType;
	public static volatile SingularAttribute<Product, LanguageType> productLanguage;
	public static volatile SingularAttribute<Product, ProductPriority> productPriority;
	public static volatile SingularAttribute<Product, ImageStatus> imageStatus;
	public static volatile ListAttribute<Product, Images> images;
	public static volatile SingularAttribute<Product, CategoryEnglish> categoriesEntity;
	public static volatile SingularAttribute<Product, User> user;
	public static volatile ListAttribute<Product, ProfileProduct> profileProduct;
	public static volatile SingularAttribute<Product, Locations> location;
	public static volatile SingularAttribute<Product, Long> version;
	public static volatile SingularAttribute<Product, Date> created;
	public static volatile SingularAttribute<Product, Date> updated;
	public static volatile SingularAttribute<Product, Date> expiryDate;
}
