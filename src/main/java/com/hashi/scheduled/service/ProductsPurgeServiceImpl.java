package com.hashi.scheduled.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;


@Service("productsPurgeService")
public class ProductsPurgeServiceImpl  implements ProductsPurgeService {
	private final static Logger logger = Logger
			.getLogger(ProductsPurgeServiceImpl.class.getName());

}

