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

import com.hashi.rest.domain.MotorCarParts;
import com.hashi.rest.domain.MotorCarParts_;
import com.hashi.rest.domain.Product;
import com.hashi.search.vo.AdvanceSearchCarParts;


public class AdvanceSearchCarPartsService extends ISearchService  {
	private static final Logger log = Logger
			.getLogger(AdvanceSearchCarPartsService.class
					.getCanonicalName());


	public static List<Predicate> advanceSearchCarPartsBySellerType(
			AdvanceSearchCarParts advanceSearchCarParts,
			Root<Product> product, CriteriaBuilder cb,
			CriteriaQuery<?> cq) {
		log.info("Executing****");

		Subquery<MotorCarParts> subQuery = cq
				.subquery(MotorCarParts.class);
		Root<MotorCarParts> car = subQuery
				.from(MotorCarParts.class);

		Path<Integer> path3 = car.get(MotorCarParts_.sellerType);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchCarParts.getSellerType() != null
				&& advanceSearchCarParts.getSellerType() > 0) {
			
			if (advanceSearchCarParts.getSellerType() == 1
					|| advanceSearchCarParts.getSellerType() == 2
					|| advanceSearchCarParts.getSellerType() == 3) {
				subQuery.select(car);
				subQuery.where(cb.equal(path3,
						advanceSearchCarParts.getSellerType()));
				predicates.add(cb.in(product).value(subQuery));
				return predicates;
			}
		}
		return predicates;
	}

}
