package com.hashi.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hashi.rest.domain.CategoryEnglish;
import com.hashi.rest.domain.CategoryEnglish_;
import com.hashi.rest.domain.Locations;
import com.hashi.rest.domain.Locations_;
import com.hashi.rest.domain.Product;
import com.hashi.rest.domain.Product_;
import com.hashi.rest.enums.LiveType;
import com.hashi.search.service.AdvanceSearchBoatsService;
import com.hashi.search.service.AdvanceSearchCarPartsService;
import com.hashi.search.service.AdvanceSearchCarService;
import com.hashi.search.service.AdvanceSearchHeavyVehicleService;
import com.hashi.search.service.AdvanceSearchMotorcycleService;
import com.hashi.search.service.AdvanceSearchRentCommService;
import com.hashi.search.service.AdvanceSearchRentResService;
import com.hashi.search.service.AdvanceSearchRentRoomService;
import com.hashi.search.service.AdvanceSearchRentShortMonthlyService;
import com.hashi.search.service.AdvanceSearchSaleCommService;
import com.hashi.search.service.AdvanceSearchSaleLandService;
import com.hashi.search.service.AdvanceSearchSaleResService;
import com.hashi.search.service.AdvanceSearchSaleUnitsService;
import com.hashi.search.service.ISearchService;
import com.hashi.search.vo.AdvanceSearch;
import com.hashi.search.vo.AdvanceSearchBoats;
import com.hashi.search.vo.AdvanceSearchCar;
import com.hashi.search.vo.AdvanceSearchCarParts;
import com.hashi.search.vo.AdvanceSearchClassified;
import com.hashi.search.vo.AdvanceSearchHeavyVehicle;
import com.hashi.search.vo.AdvanceSearchMotorcycle;
import com.hashi.search.vo.AdvanceSearchRentComm;
import com.hashi.search.vo.AdvanceSearchRentHotel;
import com.hashi.search.vo.AdvanceSearchRentRes;
import com.hashi.search.vo.AdvanceSearchRentRoom;
import com.hashi.search.vo.AdvanceSearchRentShortMonthly;
import com.hashi.search.vo.AdvanceSearchSaleComm;
import com.hashi.search.vo.AdvanceSearchSaleLand;
import com.hashi.search.vo.AdvanceSearchSaleRes;
import com.hashi.search.vo.AdvanceSearchSaleUnits;



public class ProductSpecification {
	private final static Logger logger = Logger.getLogger(ProductSpecification.class
			.getName());
	
	

	public static Specification<Product> searchBasic(final Long categoryId, final Long cityId, final String textSearch, CityEnglishRepository cityEnglishRepository) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				if(categoryId >  2){
					Root<CategoryEnglish> parent = query.from(CategoryEnglish.class);
					Root<CategoryEnglish> child = query.from(CategoryEnglish.class);
					predicates.add(cb.equal(parent.get(CategoryEnglish_.categoryId),
							categoryId));

					predicates.add(cb.between(child.<Integer> get(CategoryEnglish_.lft),
							parent.<Integer> get(CategoryEnglish_.lft),
							parent.<Integer> get(CategoryEnglish_.rgt)));

					predicates.add(cb.equal(root.get(Product_.categoriesEntity)
							.get(CategoryEnglish_.categoryId), child
							.get(CategoryEnglish_.categoryId)));
				}	


				//get only live ad products
				predicates.add(cb.equal(root.get(Product_.liveType), LiveType.LIVE));

				predicates.add(cb.greaterThanOrEqualTo(
						root.get(Product_.expiryDate), new Date()));

				// check the city name and then load products related to that city
				if (cityId > 0) {
					Path<Locations> location = root.get(Product_.location);
					Path<String> region = location.get(Locations_.region);
					predicates.add(cb.equal(cb.upper(region), cityEnglishRepository.findByCityId(cityId).getCityName()
							.toUpperCase()));
				}

				// check the text search whether is null or empty, before adding it to
				// criteria
				logger.info("textSearch:" + textSearch);
				if (textSearch != null && textSearch.trim().length() > 0) {
					predicates.add(cb.like(root.get(Product_.productTitle),
							'%' + textSearch + '%'));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]) );
			}
		};

	}


	public static Specification<Product> searchAdvance(final Long categoryId, final Long cityId, final String textSearch, final AdvanceSearch advanceSearch, CityEnglishRepository cityEnglishRepository) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if(categoryId >  2){
					Root<CategoryEnglish> parent = query.from(CategoryEnglish.class);
					Root<CategoryEnglish> child = query.from(CategoryEnglish.class);
					predicates.add(cb.equal(parent.get(CategoryEnglish_.categoryId),
							categoryId));

					predicates.add(cb.between(child.<Integer> get(CategoryEnglish_.lft),
							parent.<Integer> get(CategoryEnglish_.lft),
							parent.<Integer> get(CategoryEnglish_.rgt)));

					predicates.add(cb.equal(root.get(Product_.categoriesEntity)
							.get(CategoryEnglish_.categoryId), child
							.get(CategoryEnglish_.categoryId)));
				}	


				//get only live ad products
				predicates.add(cb.equal(root.get(Product_.liveType), LiveType.LIVE));

				predicates.add(cb.greaterThanOrEqualTo(
						root.get(Product_.expiryDate), new Date()));

				// check the city name and then load products related to that city
				if (cityId > 0) {
					Path<Locations> location = root.get(Product_.location);
					Path<String> region = location.get(Locations_.region);
					predicates.add(cb.equal(cb.upper(region), cityEnglishRepository.findByCityId(cityId).getCityName()
							.toUpperCase()));
				}

				// check the text search whether is null or empty, before adding it to
				// criteria
				if (textSearch != null && textSearch.trim().length() > 0) {
					predicates.add(cb.like(root.get(Product_.productTitle),
							'%' + textSearch + '%'));
				}
				
				
				if (advanceSearch instanceof AdvanceSearchClassified) {
					predicates.addAll(ISearchService.advanceSearchByPrice(
							(AdvanceSearch) advanceSearch, root, cb));
				}

				else if (advanceSearch instanceof AdvanceSearchRentHotel) {
					System.out.println("Advance Search Rent Hotel" + advanceSearch.getPriceFrom());
					predicates.addAll(ISearchService.advanceSearchByPrice(
							(AdvanceSearch) advanceSearch, root, cb));
				}
			
				
				/*****FOR SEARCH MOTORS FIELDS FILTERING ******/
				else if (advanceSearch instanceof AdvanceSearchCar) {
					predicates.addAll(AdvanceSearchCarService
							.advanceSearchByPrice(
									(AdvanceSearchCar)advanceSearch,
									root, cb));
					predicates
							.addAll(AdvanceSearchCarService.advanceSearchCarByYear(
									(AdvanceSearchCar)advanceSearch,
									root, cb, query));
					predicates.addAll(AdvanceSearchCarService
							.advanceSearchCarByKilometers(
									(AdvanceSearchCar)advanceSearch,
									root, cb, query));
					predicates.addAll(AdvanceSearchCarService
							.advanceSearchCarBySellerType(
									(AdvanceSearchCar)advanceSearch,
									root, cb, query));
									
				}else if (advanceSearch instanceof AdvanceSearchCarParts) {
					predicates.addAll(AdvanceSearchCarPartsService
							.advanceSearchByPrice(
									(AdvanceSearchCarParts)advanceSearch,
									root, cb));
					predicates.addAll(AdvanceSearchCarPartsService
							.advanceSearchCarPartsBySellerType(
									(AdvanceSearchCarParts)advanceSearch,
									root, cb, query));
					
				} 
				// for boat search filtering 
				else if (advanceSearch instanceof AdvanceSearchBoats) {
					predicates.addAll(AdvanceSearchBoatsService
							.advanceSearchByPrice(
									(AdvanceSearchBoats)advanceSearch,
									root, cb));
					predicates.addAll(AdvanceSearchBoatsService
							.advanceSearchBoatsBySellerType(
									(AdvanceSearchBoats)advanceSearch,
									root, cb, query));
				} 
				// For advance search, heavy vehicle
				else if (advanceSearch instanceof AdvanceSearchHeavyVehicle) {
					predicates.addAll(AdvanceSearchHeavyVehicleService
							.advanceSearchByPrice(
									(AdvanceSearchHeavyVehicle)advanceSearch,
									root, cb));
					predicates
							.addAll(AdvanceSearchHeavyVehicleService.advanceSearchHeavyVehicleByYear(
									(AdvanceSearchHeavyVehicle)advanceSearch,
									root, cb, query));
					
					predicates.addAll(AdvanceSearchHeavyVehicleService
							.advanceSearchHeavyVehicleByKilometers(
									(AdvanceSearchHeavyVehicle)advanceSearch,
									root, cb, query));
					
					predicates.addAll(AdvanceSearchHeavyVehicleService
							.advanceSearchHeavyVehicleBySellerType(
									(AdvanceSearchHeavyVehicle)advanceSearch,
									root, cb, query));		
				}
				// for motorcycle search filtering 
				else if (advanceSearch instanceof AdvanceSearchMotorcycle) {
					predicates.addAll(AdvanceSearchMotorcycleService
							.advanceSearchByPrice(
									(AdvanceSearchMotorcycle)advanceSearch,
									root, cb));
					predicates.addAll(AdvanceSearchMotorcycleService
							.advanceSearchMotorcycleBySellerType(
									(AdvanceSearchMotorcycle)advanceSearch,
									root, cb, query));
				} 
				
				/*****FOR PROPERTY SALE FIELDS FILTERING ******/
				else if (advanceSearch instanceof AdvanceSearchSaleRes) {
					predicates.addAll(AdvanceSearchSaleResService
							.advanceSearchByPrice(
									(AdvanceSearchSaleRes) advanceSearch,
									root, cb));
					predicates.addAll(AdvanceSearchSaleResService
							.advanceSearchSaleResByBedrooms(
									(AdvanceSearchSaleRes)advanceSearch,
									root, cb, query));
				} 
				// for property residential search filtering 
				else if (advanceSearch instanceof AdvanceSearchSaleComm) {
					predicates.addAll(AdvanceSearchSaleCommService
							.advanceSearchByPrice(
									(AdvanceSearchSaleComm) advanceSearch,
									root, cb));
				} 
				// for property UNITS search filtering 
				else if (advanceSearch instanceof AdvanceSearchSaleUnits) {
					predicates.addAll(AdvanceSearchSaleUnitsService
							.advanceSearchByPrice(
									(AdvanceSearchSaleUnits) advanceSearch,
									root, cb));
				} 
				// for property LAND search filtering 
				else if (advanceSearch instanceof AdvanceSearchSaleLand) {
					predicates.addAll(AdvanceSearchSaleLandService
							.advanceSearchByPrice(
									(AdvanceSearchSaleLand) advanceSearch,
									root, cb));
				} 
				/*****FOR PROPERTY RENT FIELDS FILTERING ******/
				else if (advanceSearch instanceof AdvanceSearchRentRes) {
					predicates.addAll(AdvanceSearchRentResService
							.advanceSearchByPrice(
									(AdvanceSearchRentRes) advanceSearch,
									root, cb));
					predicates.addAll(AdvanceSearchRentResService
							.advanceSearchRentResByBedrooms(
									(AdvanceSearchRentRes)advanceSearch,
									root, cb, query));
				} 
				// search rent commercial 
				else if (advanceSearch instanceof AdvanceSearchRentComm) {
					predicates.addAll(AdvanceSearchRentCommService
							.advanceSearchByPrice(
									(AdvanceSearchRentComm) advanceSearch,
									root, cb));
				} 
				// search rent room
				else if (advanceSearch instanceof AdvanceSearchRentRoom) {
					predicates.addAll(AdvanceSearchRentRoomService
							.advanceSearchByPrice(
									(AdvanceSearchRentRoom) advanceSearch,
									root, cb));
				} 
				// search rent short Monthly
				else if (advanceSearch instanceof AdvanceSearchRentShortMonthly) {
					System.out.println("AdvanceSearchRentShortMonthly" + advanceSearch.getPriceTo()) ;
					predicates.addAll(AdvanceSearchRentShortMonthlyService
							.advanceSearchByPrice(
									(AdvanceSearchRentShortMonthly) advanceSearch,
									root, cb));
					
					predicates.addAll(AdvanceSearchRentShortMonthlyService
							.advanceSearchRentShortMonthlyByBedrooms(
									(AdvanceSearchRentShortMonthly)advanceSearch,
									root, cb, query));
				} 
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};

	}
 
	//count product by category id, used in the home page
	public static Specification<Product> countProductsByCategoryId(final Long categoryId, final Long cityId, CityEnglishRepository cityEnglishRepository) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				
					Root<CategoryEnglish> parent = query.from(CategoryEnglish.class);
					Root<CategoryEnglish> child = query.from(CategoryEnglish.class);
					predicates.add(cb.equal(parent.get(CategoryEnglish_.categoryId),
							categoryId));

					predicates.add(cb.between(child.<Integer> get(CategoryEnglish_.lft),
							parent.<Integer> get(CategoryEnglish_.lft),
							parent.<Integer> get(CategoryEnglish_.rgt)));

					predicates.add(cb.equal(root.get(Product_.categoriesEntity)
							.get(CategoryEnglish_.categoryId), child
							.get(CategoryEnglish_.categoryId)));
				


				//get only live ad products
				predicates.add(cb.equal(root.get(Product_.liveType), LiveType.LIVE));

				predicates.add(cb.greaterThanOrEqualTo(
						root.get(Product_.expiryDate), new Date()));

				// check the city name and then load products related to that city
				if (cityId > 0) {
					Path<Locations> location = root.get(Product_.location);
					Path<String> region = location.get(Locations_.region);
					predicates.add(cb.equal(cb.upper(region), cityEnglishRepository.findByCityId(cityId).getCityName()
							.toUpperCase()));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]) );
			}
		};

	}






}
