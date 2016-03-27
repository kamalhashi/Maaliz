package com.hashi.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import com.hashi.rest.domain.Product;
import com.hashi.rest.domain.PropertyRentShortMonthly;
import com.hashi.rest.domain.PropertyRentShortMonthly_;
import com.hashi.search.vo.AdvanceSearchRentShortMonthly;


public class AdvanceSearchRentShortMonthlyService extends ISearchService {
	private static final Logger log = Logger
			.getLogger(AdvanceSearchRentShortMonthlyService.class.getCanonicalName());
	
	public static List<Predicate> advanceSearchRentShortMonthlyByBedrooms(
			AdvanceSearchRentShortMonthly advanceSearchRentShortMonthly,
			Root<Product> product, CriteriaBuilder cb,
			CriteriaQuery<?> cq) {

		Subquery<PropertyRentShortMonthly> subQuery = cq
				.subquery(PropertyRentShortMonthly.class);
		Root<PropertyRentShortMonthly> car = subQuery
				.from(PropertyRentShortMonthly.class);

		Path<Integer> path3 = car.get(PropertyRentShortMonthly_.noBedrooms);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchRentShortMonthly.getBedroomMin() != null
				&& advanceSearchRentShortMonthly.getBedroomMin() > 0
				&& advanceSearchRentShortMonthly.getBedroomMax() != null
				&& advanceSearchRentShortMonthly.getBedroomMax() > 0) {

			subQuery.select(car);
			subQuery.where(cb.and(cb.greaterThanOrEqualTo(path3,
					advanceSearchRentShortMonthly.getBedroomMin())), cb
					.lessThanOrEqualTo(path3,
							advanceSearchRentShortMonthly.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		else if (advanceSearchRentShortMonthly.getBedroomMin() != null
				&& advanceSearchRentShortMonthly.getBedroomMin() > 0) {
			subQuery.select(car);
			subQuery.where(cb.greaterThanOrEqualTo(path3,
					advanceSearchRentShortMonthly.getBedroomMin()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		} else if (advanceSearchRentShortMonthly.getBedroomMax() != null
				&& advanceSearchRentShortMonthly.getBedroomMax() > 0) {
			subQuery.select(car);
			subQuery.where(cb.lessThanOrEqualTo(path3,
					advanceSearchRentShortMonthly.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		if (advanceSearchRentShortMonthly.getBedroomMin() != null
				&& advanceSearchRentShortMonthly.getBedroomMin() == 0) {
			subQuery.select(car);
			subQuery.where(cb.equal(path3,
					advanceSearchRentShortMonthly.getBedroomMin()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		
		if (advanceSearchRentShortMonthly.getBedroomMax() != null
				&& advanceSearchRentShortMonthly.getBedroomMax() == 0) {
			subQuery.select(car);
			subQuery.where(cb.equal(path3,
					advanceSearchRentShortMonthly.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		return predicates;
	}
}
