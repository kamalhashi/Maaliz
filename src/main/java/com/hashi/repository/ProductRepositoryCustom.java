package com.hashi.repository;


import com.hashi.rest.domain.Product;
import com.hashi.rest.enums.ImageStatus;
import com.hashi.rest.enums.ProductPriority;

public interface ProductRepositoryCustom {
   public int updateImageStatusForProduct(Product product, ImageStatus imageStatus);
   public int updatePriorityForProduct(Product product, ProductPriority productPriority);
   
}
