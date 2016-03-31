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
	$scope.showAdvanceSearchRentHotelDiv= false;
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
		else if(categoryId == categoryIdConstant.RENT_HOTEL_CATEGORY_ID){
			$scope.showAdvanceSearchRentHotelDiv= true;
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
		else if(categoryId == categoryIdConstant.CLASSIFIED_DVD_MOVIES_CATEGORY_ID){
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
			$scope.showAdvanceSearchRentHotelDiv= false;
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
		$scope.showAdvanceSearchRentHotelDiv= false;
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
		}else if($scope.showAdvanceSearchRentHotelDiv==true){
			return searchTypeConstant.ADVANCE_SEARCH_RENT_HOTEL;
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

});