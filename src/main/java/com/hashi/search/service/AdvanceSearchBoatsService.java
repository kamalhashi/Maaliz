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
import com.hashi.rest.domain.MotorBoats;
import com.hashi.rest.domain.MotorBoats_;
import com.hashi.rest.domain.Product;
import com.hashi.search.vo.AdvanceSearchBoats;
import com.hashi.search.vo.AdvanceSearchCarParts;


public class AdvanceSearchBoatsService extends ISearchService  {
	private static final Logger log = Logger
			.getLogger(AdvanceSearchBoatsService.class
					.getCanonicalName());


	public static List<Predicate> advanceSearchBoatsBySellerType(
			AdvanceSearchBoats advanceSearchBoats,
			Root<Product> product, CriteriaBuilder cb,
			CriteriaQuery<?> cq) {
		log.info("Executing****");

		Subquery<MotorBoats> subQuery = cq
				.subquery(MotorBoats.class);
		Root<MotorBoats> car = subQuery
				.from(MotorBoats.class);

		Path<Integer> path3 = car.get(MotorBoats_.sellerType);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchBoats.getSellerType() != null
				&& advanceSearchBoats.getSellerType() > 0) {
			
			if (advanceSearchBoats.getSellerType() == 1
					|| advanceSearchBoats.getSellerType() == 2
					|| advanceSearchBoats.getSellerType() == 3) {
				subQuery.select(car);
				subQuery.where(cb.equal(path3,
						advanceSearchBoats.getSellerType()));
				predicates.add(cb.in(product).value(subQuery));
				return predicates;
			}
		}
		return predicates;
	}

}
