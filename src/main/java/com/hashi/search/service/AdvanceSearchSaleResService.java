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

public class AdvanceSearchSaleResService extends ISearchService {
	private static final Logger log = Logger
			.getLogger(AdvanceSearchSaleResService.class.getCanonicalName());
	
	public static List<Predicate> advanceSearchSaleResByBedrooms(
			AdvanceSearchSaleRes advanceSearchSaleResidential,
			Root<Product> product, CriteriaBuilder cb,
			CriteriaQuery<?> cq) {

		Subquery<PropertySaleRes> subQuery = cq
				.subquery(PropertySaleRes.class);
		Root<PropertySaleRes> car = subQuery
				.from(PropertySaleRes.class);

		Path<Integer> path3 = car.get(PropertySaleRes_.noBedrooms);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchSaleResidential.getBedroomMin() != null
				&& advanceSearchSaleResidential.getBedroomMin() > 0
				&& advanceSearchSaleResidential.getBedroomMax() != null
				&& advanceSearchSaleResidential.getBedroomMax() > 0) {

			subQuery.select(car);
			subQuery.where(cb.and(cb.greaterThanOrEqualTo(path3,
					advanceSearchSaleResidential.getBedroomMin())), cb
					.lessThanOrEqualTo(path3,
							advanceSearchSaleResidential.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		else if (advanceSearchSaleResidential.getBedroomMin() != null
				&& advanceSearchSaleResidential.getBedroomMin() > 0) {
			subQuery.select(car);
			subQuery.where(cb.greaterThanOrEqualTo(path3,
					advanceSearchSaleResidential.getBedroomMin()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		} else if (advanceSearchSaleResidential.getBedroomMax() != null
				&& advanceSearchSaleResidential.getBedroomMax() > 0) {
			subQuery.select(car);
			subQuery.where(cb.lessThanOrEqualTo(path3,
					advanceSearchSaleResidential.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		if (advanceSearchSaleResidential.getBedroomMin() != null
				&& advanceSearchSaleResidential.getBedroomMin() == 0) {
			subQuery.select(car);
			subQuery.where(cb.equal(path3,
					advanceSearchSaleResidential.getBedroomMin()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		
		if (advanceSearchSaleResidential.getBedroomMax() != null
				&& advanceSearchSaleResidential.getBedroomMax() == 0) {
			subQuery.select(car);
			subQuery.where(cb.equal(path3,
					advanceSearchSaleResidential.getBedroomMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		return predicates;
	}
}
