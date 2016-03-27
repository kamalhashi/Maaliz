package com.hashi.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.SingularAttribute;

import com.hashi.rest.domain.Product;
import com.hashi.rest.domain.PropertySaleRes;
import com.hashi.rest.domain.PropertySaleRes_;
import com.hashi.search.vo.AdvanceSearchSaleRes;

public class AdvanceSearchSaleUnitsService extends ISearchService {
	private static final Logger log = Logger
			.getLogger(AdvanceSearchSaleUnitsService.class.getCanonicalName());
	
}
