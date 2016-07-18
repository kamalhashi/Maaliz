var hashiApp = angular.module('hashiApp') // gets it


hashiApp.controller('HomeController', function($scope, $http, $translate, cityFactory, bucketName,
		$location, categoryFactory, categoryRange, $state, productFactory,promiseFeatured,
		searchTypeConstant, categoryIdConstant, sellerTypeSearchEnglish, sellerTypeSearchSomali,
		noBedroomsApartmentEnglish, noBedroomsApartmentSomali, dispatcherDetailsFactory, 
		promiseCities, promiseRootCategories, $rootScope) {


	$scope.listFeatured= null;
	$scope.listCities=null;

	//fields for simple search
	$scope.basicSelectedCategory=2;
	$scope.basicSelectedCityId=0;
	$scope.basicTextSearch=null;
	/*********Advance search variables**********************/
	//advanced search
	$scope.advSelectedCityId=0;
	$scope.advTextSearch=null;


	$scope.advCategoryLevelOneObject=[];
	$scope.advCategoryLevelTwoObject=[];
	$scope.advCategoryLevelThreeObject=[];
	$scope.advCategoryLevelFourObject=[];

	//these variables created for > issue.
	$scope.advFinalSelectedCategoryId=0;
	$scope.advSelectedCategoryIdLevelOne=2;
	$scope.advSelectedCategoryIdLevelTwo=0;
	$scope.advSelectedCategoryIdLevelThree=0;
	$scope.advSelectedCategoryIdLevelFour=0;

	//for showing and hiding categories
	$scope.showSubCategoryLevelOne = false;
	$scope.showSubCategoryLevelTwo = false;
	$scope.showSubCategoryLevelThree = false;
	$scope.showSubCategoryLevelFour = false;

	/*************Advance div search for cars, house, properties*****************/



	//fields for advance search
	//this field belongs to initializeYears() , for car years drop down populate. need it in here
	$scope.years=[];
	$scope.yearForm;

	$scope.priceFrom;
	$scope.priceTo;
	$scope.yearMin;
	$scope.yearMax;
	$scope.KMFrom;
	$scope.KMTo;
	$scope.bedroomMin;
	$scope.bedroomMax;

	$scope.selectedSellerType=0;
	//show advanced car Div to search price, from to
	$scope.showAdvanceSearchCarDiv= false;
	$scope.showAdvanceSearchCarPartsDiv= false;
	$scope.showAdvanceSearchBoatsDiv= false;
	$scope.showAdvanceSearchHeavyVehicleDiv= false;
	$scope.showAdvanceSearchMotorcycleDiv= false;
	$scope.showAdvanceSearchSaleResDiv= false;
	$scope.showAdvanceSearchSaleCommDiv= false;
	$scope.showAdvanceSearchSaleUnitsDiv= false;
	$scope.showAdvanceSearchSaleLandDiv= false;
	//rent residential
	$scope.showAdvanceSearchRentResDiv= false;
	$scope.showAdvanceSearchRentCommDiv= false;
	$scope.showAdvanceSearchRentRommDiv= false;
	$scope.showAdvanceSearchRentShortDailyDiv= false;
	$scope.showAdvanceSearchRentShortMonthlyDiv= false;
	//classified stuff
	$scope.showAdvanceSearchClassifiedDiv= false;
	
	
	//bucket name variable to show the images products in featured listing
    $scope.bucketName= bucketName;

    /***ROOT SCOPE FOR COUNT PRODUCTS***/


	(function init() {
		//initialize cities
		$scope.listCities= promiseCities.data;
		//product featured
		$scope.listFeatured=promiseFeatured.data;
		//populate categories level one
		//initializeCategoriesLevelOne();
		$scope.categories= promiseRootCategories.data;
		$scope.advCategoryLevelOneObject= $scope.categories[0];
		
		
		if($translate.use() === 'so_SO'){
			$scope.listSellerTypes= sellerTypeSearchSomali; 
			$scope.constantNoBedrooms= noBedroomsApartmentSomali;
		}
		if($translate.use() === 'en_US'){
			$scope.listSellerTypes= sellerTypeSearchEnglish; 
			$scope.constantNoBedrooms= noBedroomsApartmentEnglish;

		}
		initializeCountProducts();
	})();

  
	$scope.basicSearch = function() {
		$state.go('listing' , {searchType:'BasicSearch' , basicSelectedCityId: $scope.basicSelectedCityId, 
			basicSelectedCategoryId:  $scope.basicSelectedCategory, basicSearchText: 
				$scope.basicTextSearch});
	}


	//when user submits
	$scope.advanceSearch = function() {
		$state.go('listing' , {searchType:typeOfAdvanceSearch() , advSelectedCityId : $scope.advSelectedCityId, advFinalSelectedCategoryId:  $scope.advFinalSelectedCategoryId, 
			advSelectedCategoryIdLevelOne:  $scope.advSelectedCategoryIdLevelOne,
			advSelectedCategoryIdLevelTwo:  $scope.advSelectedCategoryIdLevelTwo,
			advSelectedCategoryIdLevelThree:  $scope.advSelectedCategoryIdLevelThree,
			advSelectedCategoryIdLevelFour:  $scope.advSelectedCategoryIdLevelFour,
			advSearchText:$scope.advTextSearch,
			advPriceFrom: $scope.priceFrom,
			advPriceTo:   $scope.priceTo,
			advYearMin: $scope.yearMin,
			advYearMax: $scope.yearMax,
			advKMFrom: $scope.KMFrom,
			advKMTo: $scope.KMTo,
			advSellerType: $scope.selectedSellerType,
			advBedroomMin: $scope.bedroomMin,
			advBedroomMax: $scope.bedroomMax,
			//basic parameters
			basicSearchText: $scope.basicTextSearch,priceFrom: $scope.priceFrom, priceTo: $scope.priceTo, yearMin:$scope.yearMin, yearMax:$scope.yearMax} );
	}

	/**to get list of sub categories level two***/

	$scope.getSubCategoriesLevelTwo = function(category) {
		hideAdvanceSearchDiv(category.categoryId);

		//reset all values
		$scope.showSubCategoryLevelTwo = false;
		$scope.showSubCategoryLevelThree = false;
		$scope.showSubCategoryLevelFour = false;
		$scope.advSelectedCategoryIdLevelTwo=0;
		$scope.advSelectedCategoryIdLevelThree=0;
		$scope.advSelectedCategoryIdLevelFour=0;
		$scope.listSubCategoriesLevelTwo=[];
		$scope.listSubCategoriesLevelThree =[];
		$scope.listSubCategoriesLevelFour=[]; 

		$scope.advFinalSelectedCategoryId=category.categoryId;
		//store the selected category level one in case is needed ">"
		$scope.advSelectedCategoryIdLevelOne=category.categoryId;


		categoryFactory.getSubCategoriesByCategoryId(category.categoryId).success(
				function(categ) {
					if(categ.length > 0){
						$scope.listSubCategoriesLevelTwo = categ;
						$scope.advCategoryLevelTwoObject= $scope.listSubCategoriesLevelTwo[0];
						$scope.advSelectedCategoryIdLevelTwo=$scope.listSubCategoriesLevelTwo[0].categoryId;
						$scope.showSubCategoryLevelTwo= true;
					}
				});

	}
	/*
	/**to get list of sub categories level three**/

	$scope.getSubCategoriesLevelThree = function(category) {
		//for showing advanced search div
		$scope.showSubCategoryLevelThree = false;
		$scope.showSubCategoryLevelFour = false;


		$scope.advSelectedCategoryIdLevelThree=0;
		$scope.advSelectedCategoryIdLevelFour=0;
		$scope.listSubCategoriesLevelThree =[];
		$scope.listSubCategoriesLevelFour=[]; 

		//if category object is not null process request
		if(category){
			hideAdvanceSearchDiv(category.categoryId);
			showAdvanceSearchDiv(category.categoryId);
			if(!category.categoryName.match(">")){
				$scope.advFinalSelectedCategoryId=category.categoryId;
				$scope.advSelectedCategoryIdLevelTwo=category.categoryId;

			}else{	
				$scope.advFinalSelectedCategoryId= $scope.advSelectedCategoryIdLevelOne;
				$scope.advSelectedCategoryIdLevelTwo=$scope.advSelectedCategoryIdLevelOne;
			}
			categoryFactory.getSubCategoriesByCategoryId(category.categoryId).success(
					function(categ) {
						if(categ.length > 0){
							$scope.listSubCategoriesLevelThree = categ;
							$scope.advCategoryLevelThreeObject= $scope.listSubCategoriesLevelThree[0];
							//initialize selectedCategoyrIdLevel THree
							$scope.advSelectedCategoryIdLevelThree=$scope.listSubCategoriesLevelThree[0].categoryId;

							$scope.showSubCategoryLevelThree = true;
						}
					});//end of getSubCategories if
		}//end of category if

	}

	/**to get list of sub categories level three**/

	$scope.getSubCategoriesLevelFour = function(category) {
		$scope.showSubCategoryLevelFour = false;
		$scope.advSelectedCategoryIdLevelFour=0;
		$scope.listSubCategoriesLevelFour=[]; 
		//if category object is not null process request
		if(category){
			if(!category.categoryName.match(">")){
				$scope.advFinalSelectedCategoryId=category.categoryId;
				$scope.advSelectedCategoryIdLevelThree=category.categoryId;
			}else{	
				$scope.advFinalSelectedCategoryId= $scope.advSelectedCategoryIdLevelTwo;
				$scope.advSelectedCategoryIdLevelThree=$scope.advSelectedCategoryIdLevelTwo;

			}
			categoryFactory.getSubCategoriesByCategoryId(category.categoryId).success(
					function(categ) {
						if(categ.length > 0){
							$scope.listSubCategoriesLevelFour = categ;
							$scope.advCategoryLevelFourObject= $scope.listSubCategoriesLevelFour[0];
							$scope.advSelectedCategoryIdLevelFour=$scope.listSubCategoriesLevelFour[0].categoryId;
							$scope.showSubCategoryLevelFour = true;
						}

					}); // end of getSubCategories if
		}//end of category if

	}


	/**to get list of sub categories level four, basically no more categories but to set selected**/

	$scope.getSubCategoriesLevelFive = function(category) {

		if(category){
			if(!category.categoryName.match(">")){
				$scope.advFinalSelectedCategoryId=category.categoryId;
				$scope.advSelectedCategoryIdLevelFour=category.categoryId;
			}else{	
				$scope.advFinalSelectedCategoryId= $scope.advSelectedCategoryIdLevelThree;
				$scope.advSelectedCategoryIdLevelFour=$scope.advSelectedCategoryIdLevelThree;

			}
		}
	}

	/***********Helper function***************/

	//initialize and populate root categories or categories level one 
	function initializeCategoriesLevelOne(){
		categoryFactory.getRootCategories().success(
				function(data) {
					$scope.categories= data;
					$scope.advCategoryLevelOneObject= $scope.categories[0];

				});
	}	
	//initialize and populate cities
	function initializeCities(){
		cityFactory.getCities().success(
				function(data) {
					$scope.listCities= data;
				});
	}
	//initialize years 
	function initializeYears(){
		if($scope.years.length == 0){
			//generate years 
			var currentYear = new Date().getFullYear();
			var range = [];
			for(var i=1900; i <currentYear + 2;i++) {
				console.log("lopping" + i);
				range.push(i);
			}
			$scope.years = range;
			$scope.yearMax= currentYear + 1;
		}
		return;
	}
	//for showing advance search Div, like price , kilo meters , home
	function showAdvanceSearchDiv(categoryId){
		//iliminate categoryId=20 because it has = category ID
		if(categoryId == categoryIdConstant.CAR_CATEGORY_ID){
			//for motor car search
			initializeYears();
			$scope.showAdvanceSearchCarDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.CAR_PARTS_CATEGORY_ID){
			$scope.showAdvanceSearchCarPartsDiv= true;
			return;
		}else if(categoryId == categoryIdConstant.BOATS_CATEGORY_ID){
			$scope.showAdvanceSearchBoatsDiv= true;
			return;
		}else if(categoryId == categoryIdConstant.HEAVY_VEHICLE_CATEGORY_ID){
			$scope.showAdvanceSearchHeavyVehicleDiv= true;
			return;
		}else if(categoryId == categoryIdConstant.MOTORCYCLES_CATEGORY_ID){
			$scope.showAdvanceSearchMotorcycleDiv= true;
			return;
		}
		//residential div show 
		else if(categoryId == categoryIdConstant.SALE_RES_CATEGORY_ID){
			$scope.showAdvanceSearchSaleResDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.SALE_COMM_CATEGORY_ID){
			$scope.showAdvanceSearchSaleCommDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.SALE_UNITS_CATEGORY_ID){
			$scope.showAdvanceSearchSaleUnitsDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.SALE_LAND_CATEGORY_ID){
			$scope.showAdvanceSearchSaleLandDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.RENT_RES_CATEGORY_ID){
			$scope.showAdvanceSearchRentResDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.RENT_COMM_CATEGORY_ID){
			$scope.showAdvanceSearchRentCommDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.RENT_ROOM_CATEGORY_ID){
			$scope.showAdvanceSearchRentRoomDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.RENT_SHORT_DAILY_CATEGORY_ID){
			$scope.showAdvanceSearchRentShortDailyDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.RENT_SHORT_MONTHLY_CATEGORY_ID){
			$scope.showAdvanceSearchRentShortMonthlyDiv= true;
			return;
		}
		//classified baby items
		else if(categoryId == categoryIdConstant.CLASSIFIED_BABY_ITEMS_CATEGORY_ID){
			$scope.showAdvanceSearchClassifiedDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_BOOKS_CATEGORY_ID){
			$scope.showAdvanceSearchClassifiedDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_BUSINESS_INDUSTRIAL_CATEGORY_ID){
			$scope.showAdvanceSearchClassifiedDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_CLOTHING_CATEGORY_ID){
			$scope.showAdvanceSearchClassifiedDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_CAMERA_CATEGORY_ID){
			$scope.showAdvanceSearchClassifiedDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_COLLECTIBLES_CATEGORY_ID){
			$scope.showAdvanceSearchClassifiedDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_COMPUTER_NETWORKING_CATEGORY_ID){
			$scope.showAdvanceSearchClassifiedDiv= true;
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_TV_CATEGORY_ID){
			$scope.showAdvanceSearchClassifiedDiv= true;
			return;
		}
		else{
			$scope.showAdvanceSearchCarDiv= false;
			$scope.showAdvanceSearchCarPartsDiv= false;
			$scope.showAdvanceSearchBoatsDiv= false;
			$scope.showAdvanceSearchHeavyVehicleDiv= false;
			$scope.showAdvanceSearchMotorcycleDiv= false;
			$scope.showAdvanceSearchSaleResDiv= false;
			$scope.showAdvanceSearchSaleCommDiv= false;
			$scope.showAdvanceSearchSaleUnitsDiv= false;
			$scope.showAdvanceSearchSaleLandDiv= false;
			$scope.showAdvanceSearchRentResDiv= false;
			$scope.showAdvanceSearchRentCommDiv= false;
			$scope.showAdvanceSearchRentRoomDiv= false;
			$scope.showAdvanceSearchRentDailyDiv= false;
			$scope.showAdvanceSearchRentShortMonthlyDiv= false;
			$scope.showAdvanceSearchClassifiedDiv= false;
			return;
		}

	}
	//hide the div advance search
	function hideAdvanceSearchDiv(categoryId){
		$scope.showAdvanceSearchCarDiv= false;
		$scope.showAdvanceSearchCarPartsDiv= false;
		$scope.showAdvanceSearchBoatsDiv= false;
		$scope.showAdvanceSearchHeavyVehicleDiv= false;
		$scope.showAdvanceSearchMotorcycleDiv= false;
		$scope.showAdvanceSearchSaleResDiv= false;
		$scope.showAdvanceSearchSaleCommDiv= false;
		$scope.showAdvanceSearchSaleUnitsDiv= false;
		$scope.showAdvanceSearchSaleLandDiv= false;
		$scope.showAdvanceSearchRentResDiv= false;
		$scope.showAdvanceSearchRentCommDiv= false;
		$scope.showAdvanceSearchRentRoomDiv= false;
		$scope.showAdvanceSearchRentShortDailyDiv= false;
		$scope.showAdvanceSearchRentShortMonthlyDiv= false;
		$scope.showAdvanceSearchClassifiedDiv= false;
	}

	function typeOfAdvanceSearch(){
		if($scope.showAdvanceSearchCarDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_CAR;
		}else if($scope.showAdvanceSearchCarPartsDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_CAR_PARTS;
		}else if($scope.showAdvanceSearchBoatsDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_BOATS;
		}else if($scope.showAdvanceSearchHeavyVehicleDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_HEAVY_VEHICLE;
		}else if($scope.showAdvanceSearchMotorcycleDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_MOTORCYCLE;
		}else if($scope.showAdvanceSearchSaleResDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_SALE_RES;
		}else if($scope.showAdvanceSearchSaleCommDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_SALE_COMM;
		}else if($scope.showAdvanceSearchSaleUnitsDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_SALE_UNITS;
		}else if($scope.showAdvanceSearchSaleLandDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_SALE_LAND;
		}else if($scope.showAdvanceSearchRentResDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_RENT_RES;
		}else if($scope.showAdvanceSearchRentCommDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_RENT_COMM;
		}else if($scope.showAdvanceSearchRentRoomDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_RENT_ROOM;
		}else if($scope.showAdvanceSearchRentShortDailyDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_DAILY;
		}else if($scope.showAdvanceSearchRentShortMonthlyDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_MONTHLY;
		}else if($scope.showAdvanceSearchClassifiedDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED;
		}
		return searchTypeConstant.ADVANCE_SEARCH;
	}
	
	$scope.forwardToDetailsPage = function (productId, categoryId ,categoryLft, categoryRgt){
		dispatcherDetailsFactory.forwardToDetailsPage(productId, categoryId ,categoryLft, categoryRgt);
	}
	
	
	//count product functionality
	  function initializeCountProducts(){
	    	//count products for different categories.
			countProductsAvailableByCategoryId(21, 0);
			countProductsAvailableByCategoryId(22, 0);
			countProductsAvailableByCategoryId(23, 0);
			countProductsAvailableByCategoryId(24,0);
			countProductsAvailableByCategoryId(25,0);
			countProductsAvailableByCategoryId(categoryIdConstant.SALE_RES_APARTMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.SALE_RES_VILLA_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.SALE_COMM_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.SALE_UNITS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.SALE_LAND_CATEGORY_ID,0);
			//sub category of residential rent 
			countProductsAvailableByCategoryId(categoryIdConstant.RENT_RES_APARTMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.RENT_RES_VILLA_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.RENT_COMM_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.RENT_ROOM_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.RENT_SHORT_DAILY_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.RENT_SHORT_MONTHLY_CATEGORY_ID,0);
			//Job top level
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_CONSULTING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_ACCOUNTING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_CONSTRUCTION_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_BANKING_FINANCE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_AIRLINES_AVIATION_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_GOV_ADMIN_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_MEDIA_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_MARKETING_ADVERTISING_CATEGORY_ID,0);

			
            //job second level bottom
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_SALES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_BEAUTY_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_EDUCATION_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_OIL_GAS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_ENGINEERING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_SECRETARIAL_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_HR_RECRUITMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_CUSTOMER_SERVICE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_PUBLIC_RELATIONS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_ART_ENTERTAINMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_EXECUTIVE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_GRAPHIC_DESIGN_CATEGORY_ID,0);	
			//classified 
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_BABY_ITEMS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_BOOKS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_BUSINESS_INDUSTRIAL_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_CAMERA_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_CLOTHING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_COLLECTIBLES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_COMPUTER_NETWORKING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_TV_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_ELECTRONICS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_FURNITURE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_GAMING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_HOME_APPLIANCES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_WOMEN_JEWELRY_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_WATCHES_CATEGORY_ID,0);

			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_MISC_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_MOBILE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_TICKETS_VOUCHERS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_PETS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_SPORTS_EQUIPMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_TOYS_CATEGORY_ID,0);
			//Community stuff
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_ACTIVITIES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_ARTIST_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_CAR_LIFT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_CHARITIES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_CHILDCARE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_CLASSES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_DOMESTIC_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_SERVICES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_FREELANCERS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_LOST_FOUND_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_FREE_STUFF_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_SPORTS_CATEGORY_ID,0);
	    }

		// a function for counting cars available 
		function countProductsAvailableByCategoryId (categoryId, cityId){
			productFactory.countProductsByCategoryId(categoryId, cityId).success(
					function(data) {
						if(categoryId == categoryIdConstant.CAR_CATEGORY_ID){
							$rootScope.countCars=data;
							return;
						}else if(categoryId == categoryIdConstant.BOATS_CATEGORY_ID){
							$rootScope.countBoats=data;
							return;
						}else if(categoryId == categoryIdConstant.CAR_PARTS_CATEGORY_ID){
							$rootScope.countCarParts=data;
							return;
						}else if(categoryId == categoryIdConstant.HEAVY_VEHICLE_CATEGORY_ID){
							$rootScope.countHeavyVehicles=data;
							return;
						}
						else if(categoryId == categoryIdConstant.MOTORCYCLES_CATEGORY_ID){
							$rootScope.countMotorcycles=data;
							return;
						}
						else if(categoryId == categoryIdConstant.SALE_RES_APARTMENT_CATEGORY_ID){
							$rootScope.countSaleResApartment=data;
							return;
						}
						else if(categoryId == categoryIdConstant.SALE_RES_VILLA_CATEGORY_ID){
							$rootScope.countSaleResVilla=data;
							return;
						}
						else if(categoryId == categoryIdConstant.SALE_COMM_CATEGORY_ID){
							$rootScope.countSaleComm=data;
							return;
						}
						else if(categoryId == categoryIdConstant.SALE_UNITS_CATEGORY_ID){
							$rootScope.countSaleUnits=data;
							return;
						}
						else if(categoryId == categoryIdConstant.SALE_LAND_CATEGORY_ID){
							$rootScope.countSaleLand=data;
							return;
						}
						else if(categoryId == categoryIdConstant.RENT_RES_APARTMENT_CATEGORY_ID){
							$rootScope.countRentResApartment=data;
							return;
						}
						else if(categoryId == categoryIdConstant.RENT_RES_VILLA_CATEGORY_ID){
							$rootScope.countRentResVilla=data;
							return;
						}
						else if(categoryId == categoryIdConstant.RENT_COMM_CATEGORY_ID){
							$rootScope.countRentComm=data;
							return;
						}
						else if(categoryId == categoryIdConstant.RENT_ROOM_CATEGORY_ID){
							$rootScope.countRentRoom=data;
							return;
						}
						else if(categoryId == categoryIdConstant.RENT_SHORT_DAILY_CATEGORY_ID){
							$rootScope.countRentShortDaily=data;
							return;
						}
						else if(categoryId == categoryIdConstant.RENT_SHORT_MONTHLY_CATEGORY_ID){
							$rootScope.countRentShortMonthly=data;
							return;
						}
						//JOB TOP LEVEL
						else if(categoryId == categoryIdConstant.JOB_CONSULTING_CATEGORY_ID){
							$rootScope.countJobConsulting=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_ACCOUNTING_CATEGORY_ID){
							$rootScope.countJobAccounting=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_CONSTRUCTION_CATEGORY_ID){
							$rootScope.countJobConstruction=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_BANKING_FINANCE_CATEGORY_ID){
							$rootScope.countJobBankingFinance=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_AIRLINES_AVIATION_CATEGORY_ID){
							$rootScope.countJobAirlinesAviation=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_GOV_ADMIN_CATEGORY_ID){
							$rootScope.countJobGovAdmin=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_MEDIA_CATEGORY_ID){
							$rootScope.countJobMedia=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_MARKETING_ADVERTISING_CATEGORY_ID){
							$rootScope.countJobMarkettingAdvertising=data;
							return;
						}
						
						//classified stuff
						else if(categoryId == categoryIdConstant.CLASSIFIED_BABY_ITEMS_CATEGORY_ID){
							$rootScope.countClassifiedBabyItems=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_BOOKS_CATEGORY_ID){
							$rootScope.countClassifiedBooks=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_BUSINESS_INDUSTRIAL_CATEGORY_ID){
							$rootScope.countClassifiedBusinessIndustrial=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_CAMERA_CATEGORY_ID){
							$rootScope.countClassifiedCamera=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_CLOTHING_CATEGORY_ID){
							$rootScope.countClassifiedClothing=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_COLLECTIBLES_CATEGORY_ID){
							$rootScope.countClassifiedCollectibles=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_COMPUTER_NETWORKING_CATEGORY_ID){
							$rootScope.countClassifiedComputerNetwork=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_TV_CATEGORY_ID){
							$rootScope.countClassifiedTV=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_ELECTRONICS_CATEGORY_ID){
							$rootScope.countClassifiedElectronics=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_FURNITURE_CATEGORY_ID){
							$rootScope.countClassifiedFurniture=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_GAMING_CATEGORY_ID){
							$rootScope.countClassifiedGaming=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_HOME_APPLIANCES_CATEGORY_ID){
							$rootScope.countClassifiedHomeAppliances=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_WATCHES_CATEGORY_ID){
							$rootScope.countClassifiedWatches=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_WOMEN_JEWELRY_CATEGORY_ID){
							$rootScope.countClassifiedWomenJewelry=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_MISC_CATEGORY_ID){
							$rootScope.countClassifiedMisc=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_MOBILE_CATEGORY_ID){
							$rootScope.countClassifiedMobile=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_PETS_CATEGORY_ID){
							$rootScope.countClassifiedPets=data;
							return;
						}
						else if(categoryId == categoryIdConstant.CLASSIFIED_SPORTS_EQUIPMENT_CATEGORY_ID){
							$rootScope.countClassifiedSportsEquipment=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.CLASSIFIED_TICKETS_VOUCHERS_CATEGORY_ID){
							$rootScope.countClassifiedTicketsVouchers=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.CLASSIFIED_TOYS_CATEGORY_ID){
							$rootScope.countClassifiedToys=data;
							return;
						}
						//Community staff related
						else if(categoryId == categoryIdConstant.COMMUNITY_ACTIVITIES_CATEGORY_ID){
							$rootScope.countCommunityActivities=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_ARTIST_CATEGORY_ID){
							$rootScope.countCommunityArtist=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_CAR_LIFT_CATEGORY_ID){
							$rootScope.countCommunityCarLift=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_CHARITIES_CATEGORY_ID){
							$rootScope.countCommunityCharities=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_CHILDCARE_CATEGORY_ID){
							$rootScope.countCommunityChildCare=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_CLASSES_CATEGORY_ID){
							$rootScope.countCommunityClasses=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_DOMESTIC_CATEGORY_ID){
							$rootScope.countCommunityDomestic=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_SERVICES_CATEGORY_ID){
							$rootScope.countCommunityServices=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_FREELANCERS_CATEGORY_ID){
							$rootScope.countCommunityFreelancers=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_LOST_FOUND_CATEGORY_ID){
							$rootScope.countCommunityLostFound=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_FREE_STUFF_CATEGORY_ID){
							$rootScope.countCommunityFreeStuff=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_SPORTS_CATEGORY_ID){
							$rootScope.countCommunitySports=data;
							return;
						}
						//BOTTOM LEVEL JOBS
						else if(categoryId == categoryIdConstant.JOB_SALES_CATEGORY_ID){
							$rootScope.countJobSales=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_BEAUTY_CATEGORY_ID){
							$rootScope.countJobBeauty=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_EDUCATION_CATEGORY_ID){
							$rootScope.countJobEducation=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_OIL_GAS_CATEGORY_ID){
							$rootScope.countJobOilGas=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_ENGINEERING_CATEGORY_ID){
							$rootScope.countJobEngineering=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_SECRETARIAL_ID){
							$rootScope.countJobSecretarial =data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_HR_RECRUITMENT_CATEGORY_ID){
							$rootScope.countJobHRRecruitment=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_CUSTOMER_SERVICE_CATEGORY_ID){
							$rootScope.countJobCustomerService=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_PUBLIC_RELATIONS_CATEGORY_ID){
							$rootScope.countJobPublicRelations=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_ART_ENTERTAINMENT_CATEGORY_ID){
							$rootScope.countJobArtEntertainment=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_EXECUTIVE_CATEGORY_ID){
							$rootScope.countJobExecutive=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_GRAPHIC_DESIGN_CATEGORY_ID){
							$rootScope.countJobGraphicDesign=data;
							return;
						}
						
					});
		}


});