package com.hashi.scheduled;

import java.time.Instant;
import java.util.Date;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hashi.config.ApplicationConfig;
import com.hashi.rest.domain.Product;
import com.hashi.rest.resource.ProductResource;
import com.hashi.rest.service.ProductService;
import com.hashi.scheduled.service.ProductsPurgeService;
import com.hashi.util.AmazonS3Service;

@Component
public class ProductsPurgeTask {

	private final static Logger logger = Logger.getLogger(ProductsPurgeTask.class.getName());

	private ProductService productService;
	private AmazonS3Service amazonS3Service;
	private ApplicationConfig config;


	@Autowired
	public ProductsPurgeTask(ProductService productService, AmazonS3Service amazonS3Service, ApplicationConfig config) {
		this.productService = productService;
		this.amazonS3Service=amazonS3Service;
		this.config= config;
	}

	@Scheduled(cron = "${purge.cron.expression}")
	public void purgeExpired() {
		Date now = Date.from(Instant.now());
		logger.log(Level.INFO, "purgeExpired() started at:" + now);
		List<Product> expiredProducts=  productService.findAllExpiredProducts(now);
		for (Product product : expiredProducts) {
			logger.log(Level.INFO, "Found product Id:" + product.getProductId());
			productService.deleteProduct(product.getProductId());
			amazonS3Service.asyncDeleteS3Directory(product.getProductId(), config.getBucketProductImages());
			amazonS3Service.asyncDeleteS3Directory(product.getProductId(), config.getBucketProductLogo());	   
		}
		logger.log(Level.INFO, "purgeExpired() ended at:" + now);	   
	}

}
