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
import com.hashi.rest.domain.MotorCycles;
import com.hashi.rest.domain.MotorCycles_;
import com.hashi.rest.domain.Product;
import com.hashi.search.vo.AdvanceSearchBoats;
import com.hashi.search.vo.AdvanceSearchCarParts;
import com.hashi.search.vo.AdvanceSearchMotorcycle;


public class AdvanceSearchMotorcycleService extends ISearchService  {
	private static final Logger log = Logger
			.getLogger(AdvanceSearchMotorcycleService.class
					.getCanonicalName());


	public static List<Predicate> advanceSearchMotorcycleBySellerType(
			AdvanceSearchMotorcycle advanceSearchMotorcycle,
			Root<Product> product, CriteriaBuilder cb,
			CriteriaQuery<?> cq) {
		log.info("Executing****");

		Subquery<MotorCycles> subQuery = cq
				.subquery(MotorCycles.class);
		Root<MotorCycles> car = subQuery
				.from(MotorCycles.class);

		Path<Integer> path3 = car.get(MotorCycles_.sellerType);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchMotorcycle.getSellerType() != null
				&& advanceSearchMotorcycle.getSellerType() > 0) {
			
			if (advanceSearchMotorcycle.getSellerType() == 1
					|| advanceSearchMotorcycle.getSellerType() == 2
					|| advanceSearchMotorcycle.getSellerType() == 3) {
				subQuery.select(car);
				subQuery.where(cb.equal(path3,
						advanceSearchMotorcycle.getSellerType()));
				predicates.add(cb.in(product).value(subQuery));
				return predicates;
			}
		}
		return predicates;
	}

}
