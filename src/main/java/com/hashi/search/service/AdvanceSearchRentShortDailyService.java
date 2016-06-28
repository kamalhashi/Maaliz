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
import com.hashi.rest.domain.PropertyRentShortDaily;
import com.hashi.rest.domain.PropertyRentShortDaily_;
import com.hashi.search.vo.AdvanceSearchRentShortDaily;


public class AdvanceSearchRentShortDailyService extends ISearchService {
	private static final Logger log = Logger
			.getLogger(AdvanceSearchRentShortDailyService.class.getCanonicalName());
	
	public static List<Predicate> advanceSearchRentShortDailyByBedrooms(
			AdvanceSearchRentShortDaily advanceSearchRentShortDaily,
			Root<Product> product, CriteriaBuilder cb,
			CriteriaQuery<?> cq) {

		Subquery<PropertyRentShortDaily> subQuery = cq
				.subquery(PropertyRentShortDaily.class);
		Root<PropertyRentShortDaily> car = subQuery
				.from(PropertyRentShortDaily.class);

		Path<Integer> path3 = car.get(PropertyRentShortDaily_.noBedrooms);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchRentShortDaily.getBedroomMin() != null
				&& advanceSearchRentShortDaily.getBedroomMin() > 0
				&& advanceSearchRentShortDaily.getBedroomMax() != null
				&& advanceSearchRentShortDaily.getBedroomMax() > 0) {

			subQuery.select(car);
			subQuery.where(cb.and(cb.greaterThanOrEqualTo(path3,
					advanceSearchRentShortDaily.getBedroomMin())), cb
					.lessThanOrEqualTo(path3,
							advanceSearchRentShortDaily.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		else if (advanceSearchRentShortDaily.getBedroomMin() != null
				&& advanceSearchRentShortDaily.getBedroomMin() > 0) {
			subQuery.select(car);
			subQuery.where(cb.greaterThanOrEqualTo(path3,
					advanceSearchRentShortDaily.getBedroomMin()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		} else if (advanceSearchRentShortDaily.getBedroomMax() != null
				&& advanceSearchRentShortDaily.getBedroomMax() > 0) {
			subQuery.select(car);
			subQuery.where(cb.lessThanOrEqualTo(path3,
					advanceSearchRentShortDaily.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		if (advanceSearchRentShortDaily.getBedroomMin() != null
				&& advanceSearchRentShortDaily.getBedroomMin() == 0) {
			subQuery.select(car);
			subQuery.where(cb.equal(path3,
					advanceSearchRentShortDaily.getBedroomMin()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		
		if (advanceSearchRentShortDaily.getBedroomMax() != null
				&& advanceSearchRentShortDaily.getBedroomMax() == 0) {
			subQuery.select(car);
			subQuery.where(cb.equal(path3,
					advanceSearchRentShortDaily.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		return predicates;
	}
}
