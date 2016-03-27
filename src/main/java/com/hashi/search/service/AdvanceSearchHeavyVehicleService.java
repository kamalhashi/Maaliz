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
import com.hashi.rest.domain.MotorHeavyVehicle;
import com.hashi.rest.domain.MotorHeavyVehicle_;
import com.hashi.rest.domain.Product;
import com.hashi.search.vo.AdvanceSearchCar;
import com.hashi.search.vo.AdvanceSearchHeavyVehicle;


public class AdvanceSearchHeavyVehicleService  extends ISearchService {
	private static final Logger log = Logger
			.getLogger(AdvanceSearchHeavyVehicleService.class.getCanonicalName());

	

	public static List<Predicate> advanceSearchHeavyVehicleByYear(
			AdvanceSearchHeavyVehicle advanceSearchHeavyVehicle, Root<Product> product,
			CriteriaBuilder cb, CriteriaQuery<?> cq) {

		Subquery<MotorHeavyVehicle> subQuery = cq.subquery(MotorHeavyVehicle.class);
		Root<MotorHeavyVehicle> car = subQuery.from(MotorHeavyVehicle.class);

		Path<Integer> path3 = car.get(MotorHeavyVehicle_.yearMade);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchHeavyVehicle.getYearMin() != null
				&& advanceSearchHeavyVehicle.getYearMin() > 0
				&& advanceSearchHeavyVehicle.getYearMax() != null
				&& advanceSearchHeavyVehicle.getYearMax() > 0) {

			subQuery.select(car);
			subQuery.where(cb.and(cb.greaterThanOrEqualTo(path3,
					advanceSearchHeavyVehicle.getYearMin())), cb.lessThanOrEqualTo(
					path3, advanceSearchHeavyVehicle.getYearMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		if (advanceSearchHeavyVehicle.getYearMin() != null
				&& advanceSearchHeavyVehicle.getYearMin() > 0) {
			subQuery.select(car);
			subQuery.where(cb.greaterThanOrEqualTo(path3,
					advanceSearchHeavyVehicle.getYearMin()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		if (advanceSearchHeavyVehicle.getYearMax() != null
				&& advanceSearchHeavyVehicle.getYearMax() > 0) {
			subQuery.select(car);
			subQuery.where(cb.lessThanOrEqualTo(path3,
					advanceSearchHeavyVehicle.getYearMax()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		return predicates;
	}

	public static List<Predicate> advanceSearchHeavyVehicleByKilometers(
			AdvanceSearchHeavyVehicle advanceSearchHeavyVehicle, Root<Product> product,
			CriteriaBuilder cb, CriteriaQuery<?> cq) {

		Subquery<MotorHeavyVehicle> subQuery = cq.subquery(MotorHeavyVehicle.class);
		Root<MotorHeavyVehicle> car = subQuery.from(MotorHeavyVehicle.class);

		Path<Integer> path3 = car.get(MotorHeavyVehicle_.kilometers);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchHeavyVehicle.getKmFrom() != null
				&& advanceSearchHeavyVehicle.getKmFrom() > 0
				&& advanceSearchHeavyVehicle.getKmTo() != null
				&& advanceSearchHeavyVehicle.getKmTo() > 0) {

			subQuery.select(car);
			subQuery.where(
					cb.and(cb.greaterThanOrEqualTo(path3,
							advanceSearchHeavyVehicle.getKmFrom())),
					cb.lessThanOrEqualTo(path3, advanceSearchHeavyVehicle.getKmTo()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}

		if (advanceSearchHeavyVehicle.getKmFrom() != null
				&& advanceSearchHeavyVehicle.getKmFrom() > 0) {
			subQuery.select(car);
			subQuery.where(cb.greaterThanOrEqualTo(path3,
					advanceSearchHeavyVehicle.getKmFrom()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		if (advanceSearchHeavyVehicle.getKmTo() != null
				&& advanceSearchHeavyVehicle.getKmTo() > 0) {
			subQuery.select(car);
			subQuery.where(cb.lessThanOrEqualTo(path3,
					advanceSearchHeavyVehicle.getKmTo()));
			predicates.add(cb.in(product).value(subQuery));
			return predicates;
		}
		return predicates;
	}

	public static List<Predicate> advanceSearchHeavyVehicleBySellerType(
			AdvanceSearchHeavyVehicle advanceSearchHeavyVehicle, Root<Product> product,
			CriteriaBuilder cb, CriteriaQuery<?> cq) {

		Subquery<MotorHeavyVehicle> subQuery = cq.subquery(MotorHeavyVehicle.class);
		Root<MotorHeavyVehicle> car = subQuery.from(MotorHeavyVehicle.class);

		Path<Integer> path3 = car.get(MotorHeavyVehicle_.sellerType);
		List<Predicate> predicates = new ArrayList<>();

		if (advanceSearchHeavyVehicle.getSellerType() != null
				&& advanceSearchHeavyVehicle.getSellerType() > 0) {
			if (advanceSearchHeavyVehicle.getSellerType() == 1
					|| advanceSearchHeavyVehicle.getSellerType() == 2) {
				subQuery.select(car);
				subQuery.where(cb.equal(path3,
						advanceSearchHeavyVehicle.getSellerType()));
				predicates.add(cb.in(product).value(subQuery));
				return predicates;
			}
		}
		return predicates;
	}

}
