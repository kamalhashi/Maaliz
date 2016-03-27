package com.hashi.search.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.hashi.rest.domain.Product;
import com.hashi.rest.domain.Product_;
import com.hashi.search.vo.AdvanceSearch;
import com.hashi.search.vo.AdvanceSearchCar;

public abstract class ISearchService {
	
	public static List<Predicate> advanceSearchByPrice(
			AdvanceSearch advanceSearch, Root<Product> product,
			CriteriaBuilder cb) {

		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearch.getPriceFrom() != null
				&& advanceSearch.getPriceFrom() > 0
				&& advanceSearch.getPriceTo() != null
				&& advanceSearch.getPriceTo() > 0) {
			predicates.add(cb.and(cb.greaterThanOrEqualTo(
					product.get(Product_.productPrice),
					advanceSearch.getPriceFrom())));
			predicates.add(cb.lessThanOrEqualTo(
					product.get(Product_.productPrice),
					advanceSearch.getPriceTo()));
			return predicates;
		}

		if (advanceSearch.getPriceFrom() != null
				&& advanceSearch.getPriceFrom() > 0) {

			predicates.add(cb.greaterThanOrEqualTo(
					product.get(Product_.productPrice),
					advanceSearch.getPriceFrom()));
			return predicates;
		}

		if (advanceSearch.getPriceTo() != null
				&& advanceSearch.getPriceTo() > 0) {
			predicates.add(cb.lessThanOrEqualTo(
					product.get(Product_.productPrice),
					advanceSearch.getPriceTo()));
			return predicates;
		}
		return predicates;
	}


}
