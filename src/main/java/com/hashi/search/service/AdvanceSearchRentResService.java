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
import com.hashi.rest.domain.PropertyRentRes;
import com.hashi.rest.domain.PropertyRentRes_;
import com.hashi.rest.domain.PropertySaleRes;
import com.hashi.rest.domain.PropertySaleRes_;
import com.hashi.search.vo.AdvanceSearchRentRes;
import com.hashi.search.vo.AdvanceSearchSaleRes;

public class AdvanceSearchRentResService extends ISearchService {
	private static final Logger log = Logger
			.getLogger(AdvanceSearchRentResService.class.getCanonicalName());
	
	public static List<Predicate> advanceSearchRentResByBedrooms(
			AdvanceSearchRentRes advanceSearchRentResidential,
			Root<Product> product, CriteriaBuilder cb,
			CriteriaQuery<?> cq) {

		Subquery<PropertyRentRes> subQuery = cq
				.subquery(PropertyRentRes.class);
		Root<PropertyRentRes> car = subQuery
				.from(PropertyRentRes.class);

		Path<Integer> path3 = car.get(PropertyRentRes_.noBedrooms);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchRentResidential.getBedroomMin() != null
				&& advanceSearchRentResidential.getBedroomMin() > 0
				&& advanceSearchRentResidential.getBedroomMax() != null
				&& advanceSearchRentResidential.getBedroomMax() > 0) {

			subQuery.select(car);
			subQuery.where(cb.and(cb.greaterThanOrEqualTo(path3,
					advanceSearchRentResidential.getBedroomMin())), cb
					.lessThanOrEqualTo(path3,
							advanceSearchRentResidential.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		else if (advanceSearchRentResidential.getBedroomMin() != null
				&& advanceSearchRentResidential.getBedroomMin() > 0) {
			subQuery.select(car);
			subQuery.where(cb.greaterThanOrEqualTo(path3,
					advanceSearchRentResidential.getBedroomMin()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		} else if (advanceSearchRentResidential.getBedroomMax() != null
				&& advanceSearchRentResidential.getBedroomMax() > 0) {
			subQuery.select(car);
			subQuery.where(cb.lessThanOrEqualTo(path3,
					advanceSearchRentResidential.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		if (advanceSearchRentResidential.getBedroomMin() != null
				&& advanceSearchRentResidential.getBedroomMin() == 0) {
			subQuery.select(car);
			subQuery.where(cb.equal(path3,
					advanceSearchRentResidential.getBedroomMin()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		
		if (advanceSearchRentResidential.getBedroomMax() != null
				&& advanceSearchRentResidential.getBedroomMax() == 0) {
			subQuery.select(car);
			subQuery.where(cb.equal(path3,
					advanceSearchRentResidential.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		return predicates;
	}
}
