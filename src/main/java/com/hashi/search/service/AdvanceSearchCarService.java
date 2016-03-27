package com.hashi.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.hashi.rest.domain.MotorCar;
import com.hashi.rest.domain.MotorCar_;
import com.hashi.rest.domain.Product;
import com.hashi.search.vo.AdvanceSearchCar;


public class AdvanceSearchCarService  extends ISearchService {
	private static final Logger log = Logger
			.getLogger(AdvanceSearchCarService.class.getCanonicalName());

	

	public static List<Predicate> advanceSearchCarByYear(
			AdvanceSearchCar advanceSearchCar, Root<Product> product,
			CriteriaBuilder cb, CriteriaQuery<?> cq) {

		Subquery<MotorCar> subQuery = cq.subquery(MotorCar.class);
		Root<MotorCar> car = subQuery.from(MotorCar.class);

		Path<Integer> path3 = car.get(MotorCar_.yearMade);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchCar.getYearMin() != null
				&& advanceSearchCar.getYearMin() > 0
				&& advanceSearchCar.getYearMax() != null
				&& advanceSearchCar.getYearMax() > 0) {

			subQuery.select(car);
			subQuery.where(cb.and(cb.greaterThanOrEqualTo(path3,
					advanceSearchCar.getYearMin())), cb.lessThanOrEqualTo(
					path3, advanceSearchCar.getYearMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		if (advanceSearchCar.getYearMin() != null
				&& advanceSearchCar.getYearMin() > 0) {
			subQuery.select(car);
			subQuery.where(cb.greaterThanOrEqualTo(path3,
					advanceSearchCar.getYearMin()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		if (advanceSearchCar.getYearMax() != null
				&& advanceSearchCar.getYearMax() > 0) {
			subQuery.select(car);
			subQuery.where(cb.lessThanOrEqualTo(path3,
					advanceSearchCar.getYearMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		return predicates;
	}

	public static List<Predicate> advanceSearchCarByKilometers(
			AdvanceSearchCar advanceSearchCar, Root<Product> product,
			CriteriaBuilder cb, CriteriaQuery<?> cq) {

		Subquery<MotorCar> subQuery = cq.subquery(MotorCar.class);
		Root<MotorCar> car = subQuery.from(MotorCar.class);

		Path<Integer> path3 = car.get(MotorCar_.kilometers);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchCar.getKmFrom() != null
				&& advanceSearchCar.getKmFrom() > 0
				&& advanceSearchCar.getKmTo() != null
				&& advanceSearchCar.getKmTo() > 0) {

			subQuery.select(car);
			subQuery.where(
					cb.and(cb.greaterThanOrEqualTo(path3,
							advanceSearchCar.getKmFrom())),
					cb.lessThanOrEqualTo(path3, advanceSearchCar.getKmTo()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		if (advanceSearchCar.getKmFrom() != null
				&& advanceSearchCar.getKmFrom() > 0) {
			subQuery.select(car);
			subQuery.where(cb.greaterThanOrEqualTo(path3,
					advanceSearchCar.getKmFrom()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		if (advanceSearchCar.getKmTo() != null
				&& advanceSearchCar.getKmTo() > 0) {
			subQuery.select(car);
			subQuery.where(cb.lessThanOrEqualTo(path3,
					advanceSearchCar.getKmTo()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		return predicates;
	}

	public static List<Predicate> advanceSearchCarBySellerType(
			AdvanceSearchCar advanceSearchCar, Root<Product> product,
			CriteriaBuilder cb, CriteriaQuery<?> cq) {

		Subquery<MotorCar> subQuery = cq.subquery(MotorCar.class);
		Root<MotorCar> car = subQuery.from(MotorCar.class);

		Path<Integer> path3 = car.get(MotorCar_.sellerType);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchCar.getSellerType() != null
				&& advanceSearchCar.getSellerType() > 0) {
			if (advanceSearchCar.getSellerType() == 1
					|| advanceSearchCar.getSellerType() == 2) {
				subQuery.select(car);
				subQuery.where(cb.equal(path3,
						advanceSearchCar.getSellerType()));
				predicates.add(cb.in(product).value(subQuery));
				return predicates;
			}
		}
		return predicates;
	}

}
