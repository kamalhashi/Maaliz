var hashiApp = angular.module('hashiApp') // gets it


hashiApp.controller('ListingController', function($scope, $http, productFactory, $stateParams, categoryRange, bucketName,
		$window, cityFactory, categoryFactory, sellerTypeSearchSomali, sellerTypeSearchEnglish, usageEnglish, usageSomali, 
		searchTypeConstant, categoryIdConstant, $translate, noBedroomsApartmentEnglish, noBedroomsApartmentSomali,
		sellerTypeEnglish, sellerTypeSomali, coloursEnglish, coloursSomali, noBathroomsEnglish, noBathroomsSomali,
		warrantySomali, warrantyEnglish, ageEnglish, ageSomali, lengthEnglish, lengthSomali, conditionSomali, conditionEnglish,
		ownerTypeEnglish, ownerTypeSomali, furnishedSomali, furnishedEnglish, rentTimeEnglish, rentTimeSomali,
		computerBrand, computerHardDrive, dispatcherDetailsFactory, sortBySomali, sortByEnglish, numberOfTicketsEnglish, 
		numberOfTicketsSomali, employmentTypeEnglish, employmentTypeSomali, workExperienceEnglish, workExperienceSomali, 
		educationLevelEnglish, educationLevelSomali, monthSalaryEnglish, monthSalarySomali) {
	//ui bootstrap pagination
	$scope.currentPage=1;
	
	$scope.categoryRange= categoryRange;
	//setting default sorting to productPriority and feature
	$scope.orderProp='productPriority';
	$scope.sortingArray={sortColumn:'productPriority', sortDirection:'desc'};

	// selected id 
	$scope.selectedCityId=0;
	$scope.categoryLevelOneObject=[];
	$scope.categoryLevelTwoObject=[];
	$scope.categoryLevelThreeObject=[];
	$scope.categoryLevelFourObject=[];

	//these variables created for > issue.
	$scope.finalSelectedCategoryId=0;
	$scope.selectedCategoryIdLevelOne=2;
	$scope.selectedCategoryIdLevelTwo=0;
	$scope.selectedCategoryIdLevelThree=0;
	$scope.selectedCategoryIdLevelFour=0;



	$scope.showSubCategoryLevelOne = false;
	$scope.showSubCategoryLevelTwo = false;
	$scope.showSubCategoryLevelThree = false;
	$scope.showSubCategoryLevelFour = false;

	//search type, is't basic or advance or car, motors.
	$scope.searchType;
	$scope.searchText;

	//showAdvanceSearh divs,it could be car or house
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

	//this field belongs to initializeYears() , for car years drop down populate. need it in here
	$scope.years=[];
	$scope.priceFrom;
	$scope.priceTo;
	$scope.yearMin;
	$scope.yearMax;
	$scope.KMFrom;
	$scope.KMTo;
	$scope.bedroomMin;
	$scope.bedroomMax; 
	$scope.sellerType=0;
	
	//bucket name variable to show the images products in featured listing
    $scope.bucketName= bucketName;


	(function init() {
		if($translate.use() === 'so_SO'){
			$scope.listSellerTypes= sellerTypeSearchSomali; 
			$scope.constantNumberTickets= numberOfTicketsSomali; 
			//for car parts 
			$scope.sellerTypeConstant= sellerTypeSomali;
			$scope.sortByConstant= sortBySomali;
			$scope.colourConstant= coloursSomali;
			$scope.ageConstant= ageSomali;
			$scope.usageConstant= usageSomali;
			$scope.warrantyConstant= warrantySomali;
			$scope.lengthConstant= lengthSomali;
			$scope.constantNoBedrooms= noBedroomsApartmentSomali;
			$scope.constantNoBathrooms= noBathroomsSomali;
			$scope.constantOwnerType= ownerTypeSomali;
			$scope.constantRentTime= rentTimeSomali;
			$scope.constantFurnished= furnishedSomali;
			$scope.constantCondition= conditionSomali;
			$scope.constantComputerBrand= computerBrand;
			$scope.constantComputerHardDrive= computerHardDrive;
			$scope.constantEmploymentType= employmentTypeSomali;
			$scope.constantWorkExperience = workExperienceSomali;
			$scope.constantEducationLevel = educationLevelSomali;
			$scope.constantMonthSalary = monthSalarySomali;
		}
		if($translate.use() === 'en_US'){
			$scope.constantNumberTickets= numberOfTicketsEnglish; 
			$scope.listSellerTypes= sellerTypeSearchEnglish; 
			$scope.colourConstant= coloursEnglish;
			$scope.sortByConstant= sortByEnglish;
			$scope.sellerTypeConstant= sellerTypeEnglish;
			$scope.ageConstant= ageEnglish;
			$scope.usageConstant= usageEnglish;
			$scope.warrantyConstant= warrantyEnglish;
			$scope.lengthConstant= lengthEnglish;
			$scope.constantNoBedrooms= noBedroomsApartmentEnglish;
			$scope.constantNoBathrooms= noBathroomsEnglish;
			$scope.constantOwnerType= ownerTypeEnglish;
			$scope.constantRentTime= rentTimeEnglish;
			$scope.constantFurnished= furnishedEnglish;
			$scope.constantCondition= conditionEnglish;
			$scope.constantComputerBrand= computerBrand;
			$scope.constantComputerHardDrive= computerHardDrive;
			$scope.constantEmploymentType= employmentTypeEnglish;
			$scope.constantWorkExperience = workExperienceEnglish;
			$scope.constantEducationLevel = educationLevelEnglish;
			$scope.constantMonthSalary = monthSalaryEnglish;
		}
		/**********important first to set search type*************/
		setSearchType($stateParams.searchType); 
		//once search type is set initialize fields, get fields from parameters
		initializeSearchTypeFields($scope.searchType);
		//advance search will be executed if searchType not basicSearch
		if($scope.searchType === searchTypeConstant.ADVANCE_SEARCH 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_CAR
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_CAR_PARTS 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_BOATS 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_HEAVY_VEHICLE 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_MOTORCYCLE 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_SALE_RES 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_SALE_COMM 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_SALE_UNITS 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_SALE_LAND 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_RENT_RES 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_RENT_COMM 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_RENT_ROOM 
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_DAILY
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_MONTHLY
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_JOB
				|| $scope.searchType ===  searchTypeConstant.ADVANCE_SEARCH_COMMUNITY){
			
			
			//default parameters that's needed 
			$scope.selectedCityId=$stateParams.advSelectedCityId;
			$scope.finalSelectedCategoryId = $stateParams.advFinalSelectedCategoryId;
			$scope.selectedCategoryIdLevelOne=$stateParams.advSelectedCategoryIdLevelOne;
			$scope.selectedCategoryIdLevelTwo=$stateParams.advSelectedCategoryIdLevelTwo;
			$scope.selectedCategoryIdLevelThree=$stateParams.advSelectedCategoryIdLevelThree;
			$scope.selectedCategoryIdLevelFour=$stateParams.advSelectedCategoryIdLevelFour;
			$scope.searchText= $stateParams.advSearchText;

			initializeCities();
			
			//initialize root categories or top level one categories
			initializeSubCategoriesLevelOne();
			initializeSubCategoriesLevelTwo();
			initializeSubCategoriesLevelThree();
			initializeSubCategoriesLevelFour();

			//make http request to the server and use advance search parameters
			var searchObject = createSearchObject($scope.searchType);
			advanceSearch($scope.selectedCityId, $scope.finalSelectedCategoryId, $scope.searchText, 0, searchObject);
		}
		
		//basic search method will be faster than advance search. 
		else{	
			//initialize cities
			initializeCities();
			//initialize root categories or top level one categories
			initializeSubCategoriesLevelOne();

			//convert city id to city name by searching database, set the scope.city.name too		
			getCityNameByCityId($scope.selectedCityId);
			//basic search
			basicSearch($scope.selectedCityId, $scope.finalSelectedCategoryId, $scope.searchText, 0); 
			
		}
	})();
	//advance search function that will send http request to the server and pass the below parameters.
	function advanceSearch(selectedCityId, finalSelectedCategoryId, searchText, pageIndex, searchObject){
		$scope.myPromise= productFactory.advanceSearch(selectedCityId, finalSelectedCategoryId , searchText, pageIndex , $scope.sortingArray.sortColumn, $scope.sortingArray.sortDirection, searchObject).success(
				function(data) {
					$scope.ads= data;
				});
		productFactory.countProductsByDepth(finalSelectedCategoryId, selectedCityId).success(
				function(data) {
					$scope.countProductByDepth= data;
		});
		getCityNameByCityId($scope.selectedCityId);
	}


	$scope.search = function() {	
		var searchObject = createSearchObject($scope.searchType);
		advanceSearch($scope.selectedCityId, $scope.finalSelectedCategoryId, $scope.searchText, 0, searchObject);	
	}


	$scope.getSubCategoriesLevelTwo = function(category) {
		hideAdvanceSearchDiv(category.categoryId);

		//reset all values
		$scope.showSubCategoryLevelTwo = false;
		$scope.showSubCategoryLevelThree = false;
		$scope.showSubCategoryLevelFour = false;
		$scope.selectedCategoryIdLevelTwo=0;
		$scope.selectedCategoryIdLevelThree=0;
		$scope.selectedCategoryIdLevelFour=0;
		$scope.listSubCategoriesLevelTwo=[];
		$scope.listSubCategoriesLevelThree =[];
		$scope.listSubCategoriesLevelFour=[]; 

		$scope.finalSelectedCategoryId=category.categoryId;
		//store the selected category level one in case is needed ">"
		$scope.selectedCategoryIdLevelOne=category.categoryId;


		categoryFactory.getSubCategoriesByCategoryId(category.categoryId).success(
				function(categ) {
					if(categ.length > 0){
						$scope.listSubCategoriesLevelTwo = categ;
						$scope.categoryLevelTwoObject= $scope.listSubCategoriesLevelTwo[0];
						$scope.selectedCategoryIdLevelTwo=$scope.listSubCategoriesLevelTwo[0].categoryId;
						$scope.showSubCategoryLevelTwo= true;
					}
				});

	}


	/**to get list of sub categories level three**/

	$scope.getSubCategoriesLevelThree = function(category) {
		//for showing advanced search div
		$scope.showSubCategoryLevelThree = false;
		$scope.showSubCategoryLevelFour = false;


		$scope.selectedCategoryIdLevelThree=0;
		$scope.selectedCategoryIdLevelFour=0;
		$scope.listSubCategoriesLevelThree =[];
		$scope.listSubCategoriesLevelFour=[]; 

		//if category object is not null process request
		if(category){
			hideAdvanceSearchDiv(category.categoryId);
			showAdvanceSearchDiv(category.categoryId);
			if(!category.categoryName.match(">")){
				$scope.finalSelectedCategoryId=category.categoryId;
				$scope.selectedCategoryIdLevelThree=category.categoryId;
			}else{	
				$scope.finalSelectedCategoryId= $scope.selectedCategoryIdLevelOne;
				$scope.selectedCategoryIdLevelTwo=$scope.selectedCategoryIdLevelOne;
				setSearchTypeByCategoryId($scope.finalSelectedCategoryId);

			}


			categoryFactory.getSubCategoriesByCategoryId(category.categoryId).success(
					function(categ) {
						if(categ.length > 0){
							$scope.listSubCategoriesLevelThree = categ;
							$scope.categoryLevelThreeObject= $scope.listSubCategoriesLevelThree[0];
							//initialize selectedCategoyrIdLevel THree
							$scope.selectedCategoryIdLevelThree=$scope.listSubCategoriesLevelThree[0].categoryId;
							$scope.showSubCategoryLevelThree = true;
						}
					});//end of getSubCategories if
		}//end of category if

	}

	/**to get list of sub categories level three**/

	$scope.getSubCategoriesLevelFour = function(category) {
		$scope.showSubCategoryLevelFour = false;
		$scope.selectedCategoryIdLevelFour=0;
		$scope.listSubCategoriesLevelFour=[]; 
		//if category object is not null process request
		if(category){
			if(!category.categoryName.match(">")){
				$scope.finalSelectedCategoryId=category.categoryId;
				$scope.selectedCategoryIdLevelFour=category.categoryId;
			}else{	
				$scope.finalSelectedCategoryId= $scope.selectedCategoryIdLevelTwo;
				$scope.selectedCategoryIdLevelThree=$scope.selectedCategoryIdLevelTwo;
			}
			categoryFactory.getSubCategoriesByCategoryId(category.categoryId).success(
					function(categ) {
						if(categ.length > 0){
							$scope.listSubCategoriesLevelFour = categ;
							$scope.categoryLevelFourObject= $scope.listSubCategoriesLevelFour[0];
							$scope.selectedCategoryIdLevelFour=$scope.listSubCategoriesLevelFour[0].categoryId;
							$scope.showSubCategoryLevelFour = true;
						}

					}); // end of getSubCategories if
		}//end of category if

	}


	/**to get list of sub categories level four, basically no more categories but to set selected**/

	$scope.getSubCategoriesLevelFive = function(category) {

		if(category){
			if(!category.categoryName.match(">")){
				$scope.finalSelectedCategoryId=category.categoryId;
				$scope.selectedCategoryIdLevelFour=category.categoryId;
			}else{	
				$scope.finalSelectedCategoryId= $scope.advSelectedCategoryIdLevelThree;
				$scope.selectedCategoryIdLevelFour=$scope.advSelectedCategoryIdLevelThree;
			}
		}
	}

	//	total pages an array for ng-repeat
	$scope.getTotalPages = function(totalPages) {
		return new Array(totalPages);   
	}	

	//	when selection changes then order new sorting starting from  0 pages.
	$scope.resetSorting = function(){
		//change the sorting selection
		createSortingArray($scope.orderProp);
		if($stateParams.searchType=== searchTypeConstant.BASIC_SEARCH){			
			basicSearch($scope.selectedCityId, $scope.finalSelectedCategoryId, $scope.searchText, 0); 
			//$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  

		}else{
			var searchObject = createSearchObject($stateParams.searchType);
			advanceSearch($scope.selectedCityId, $scope.finalSelectedCategoryId, $scope.searchText, 0, searchObject);
			//go to the top of the page when next button is clicked
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  

		}

	}
	//	get next pages 
	$scope.getNextPageAds = function() {
		createSortingArray($scope.orderProp);
		//check the range of next the page or previues page, it should be within a range 

			//$scope.currentPage= pageIndex;
			//checking the type of the search is't basic
			if($stateParams.searchType=== searchTypeConstant.BASIC_SEARCH){			
				basicSearch($scope.selectedCityId, $scope.finalSelectedCategoryId, $scope.searchText, $scope.currentPage-1); 
				$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  

			}else{
				var searchObject = createSearchObject($stateParams.searchType);
				advanceSearch($scope.selectedCityId, $scope.finalSelectedCategoryId, $scope.searchText, $scope.currentPage-1, searchObject);
				//go to the top of the page when next button is clicked
				$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
			}
		
	}

	/*************Helper functions***************/


	//function for basic search
	function basicSearch(selectedCityId, finalSelectedCategoryId, searchText, pageIndex ){
		$scope.myPromise = productFactory.basicSearch(selectedCityId, finalSelectedCategoryId, searchText, pageIndex , $scope.sortingArray.sortColumn, $scope.sortingArray.sortDirection).success(
				function(data) {
					$scope.ads= data;
				});
		productFactory.countProductsByDepth(finalSelectedCategoryId, selectedCityId).success(
				function(data) {
					$scope.countProductByDepth= data;
		});
	}

	//initialize and populate cities
	function initializeCities(){
		cityFactory.getCities().success(
				function(data) {
					$scope.listCities= data;
				});
	}
	//function to get city name by searching category id
	function getCityNameByCityId(cityId){
		cityFactory.getCityName(cityId).success(
				function(data) {
					$scope.cityName=  data.cityName;
				});

	}
	//initialize top level category or root category
	function initializeSubCategoriesLevelOne(){
		categoryFactory.getRootCategories().success(
				function(data) {
					$scope.listSubCategoriesLevelOne= data;
					//for setting the default value of the select, should be object
					$scope.categoryLevelOneObject= getCategoryByCategoryId($scope.listSubCategoriesLevelOne, $scope.selectedCategoryIdLevelOne);

				});
	}
	//initialize second level of categories
	function initializeSubCategoriesLevelTwo(){
		$scope.showSubCategoryLevelTwo=false;	
		if($scope.selectedCategoryIdLevelTwo > 0){
			categoryFactory.getSubCategoriesByCategoryId($scope.selectedCategoryIdLevelOne).success(
					function(data) {
						$scope.listSubCategoriesLevelTwo= data;
						//for setting the default value of the select, should be object
						$scope.categoryLevelTwoObject= $scope.selectedCategoryIdLevelOne == $scope.selectedCategoryIdLevelTwo ? $scope.listSubCategoriesLevelTwo[0] : getCategoryByCategoryId($scope.listSubCategoriesLevelTwo, $scope.selectedCategoryIdLevelTwo);
						$scope.showSubCategoryLevelTwo=true;

					});
		}
	}
	//sub categories level three
	function initializeSubCategoriesLevelThree(){
		$scope.showSubCategoryLevelThree=false;	
		if($scope.selectedCategoryIdLevelThree > 0){
			categoryFactory.getSubCategoriesByCategoryId($scope.selectedCategoryIdLevelTwo).success(
					function(data) {
						$scope.listSubCategoriesLevelThree= data;
						//for setting the default value of the select, should be object
						$scope.categoryLevelThreeObject= $scope.selectedCategoryIdLevelTwo == $scope.selectedCategoryIdLevelThree ? $scope.listSubCategoriesLevelThree[0] : getCategoryByCategoryId($scope.listSubCategoriesLevelThree, $scope.selectedCategoryIdLevelThree);
						$scope.showSubCategoryLevelThree=true;
					});
		}
	}

	//sub categories level three
	function initializeSubCategoriesLevelFour(){
		$scope.showSubCategoryLevelFour=false;	
		if($scope.selectedCategoryIdLevelFour > 0){
			categoryFactory.getSubCategoriesByCategoryId($scope.selectedCategoryIdLevelThree).success(
					function(data) {
						$scope.listSubCategoriesLevelFour= data;
						//for setting the default value of the select, should be object
						$scope.categoryLevelFourObject= $scope.selectedCategoryIdLevelThree == $scope.selectedCategoryIdLevelFour ? $scope.listSubCategoriesLevelFour[0] : getCategoryByCategoryId($scope.listSubCategoriesLevelFour, $scope.selectedCategoryIdLevelFour);
						$scope.showSubCategoryLevelFour=true;

					});
		}
	}


	//a helper function that creates and sets search object.
	function createSearchObject(searchTypeParam){
		switch(searchTypeParam){
		case searchTypeConstant.ADVANCE_SEARCH:
			var searchObject = {type: searchTypeParam};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_CAR: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo,
				yearMin:$scope.yearMin, yearMax:$scope.yearMax, kmFrom:$scope.KMFrom, kmTo:$scope.KMTo,
				sellerType: $scope.sellerType};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_CAR_PARTS: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo,
				sellerType: $scope.sellerType};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_BOATS: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo,
				sellerType: $scope.sellerType};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_HEAVY_VEHICLE: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo,
				yearMin:$scope.yearMin, yearMax:$scope.yearMax, kmFrom:$scope.KMFrom, kmTo:$scope.KMTo,
				sellerType: $scope.sellerType};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_MOTORCYCLE: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo,
				sellerType: $scope.sellerType};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_RES: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo,
				bedroomMin: $scope.bedroomMin, bedroomMax: $scope.bedroomMax};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_COMM: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_UNITS: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_LAND: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_RES: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo,
				bedroomMin: $scope.bedroomMin, bedroomMax: $scope.bedroomMax};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_COMM: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_ROOM: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_DAILY:
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo,
				bedroomMin: $scope.bedroomMin, bedroomMax: $scope.bedroomMax};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_MONTHLY:
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo,
				bedroomMin: $scope.bedroomMin, bedroomMax: $scope.bedroomMax};
			return searchObject;
		case searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED: 
			var searchObject = {type: searchTypeParam  , priceFrom: $scope.priceFrom, priceTo: $scope.priceTo};
			return searchObject;
		default:
			console.log('Create Search Object : Default case.');
		}

	}

	//function helper for setting searchType
	function setSearchType(searchType){
		switch(searchType) {
		case searchTypeConstant.BASIC_SEARCH:
			$scope.searchType = searchTypeConstant.BASIC_SEARCH;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_JOB:
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_JOB;
			break;
		case searchTypeConstant.ADVANCE_SEARCH:	
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_CAR:
			$scope.showAdvanceSearchCarDiv=true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_CAR;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_CAR_PARTS:
			$scope.showAdvanceSearchCarPartsDiv=true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_CAR_PARTS;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_BOATS:
			$scope.showAdvanceSearchBoatsDiv=true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_BOATS;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_HEAVY_VEHICLE:
			$scope.showAdvanceSearchHeavyVehicleDiv=true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_HEAVY_VEHICLE;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_MOTORCYCLE:
			$scope.showAdvanceSearchMotorcycleDiv=true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_MOTORCYCLE;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_RES:
			$scope.showAdvanceSearchSaleResDiv= true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_SALE_RES;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_COMM:
			$scope.showAdvanceSearchSaleCommDiv= true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_SALE_COMM;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_UNITS:
			$scope.showAdvanceSearchSaleUnitsDiv= true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_SALE_UNITS;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_LAND:
			$scope.showAdvanceSearchSaleLandDiv= true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_SALE_LAND;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_RES:
			$scope.showAdvanceSearchRentResDiv= true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_RENT_RES;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_COMM:
			$scope.showAdvanceSearchRentCommDiv= true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_RENT_COMM;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_ROOM:
			$scope.showAdvanceSearchRentRoomDiv= true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_RENT_ROOM;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_DAILY:
			$scope.showAdvanceSearchRentShortDailyDiv= true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_DAILY;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_MONTHLY:
			$scope.showAdvanceSearchRentShortMonthlyDiv= true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_MONTHLY;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED:
			$scope.showAdvanceSearchClassifiedDiv= true;
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_COMMUNITY:
			$scope.searchType = searchTypeConstant.ADVANCE_SEARCH_COMMUNITY;
			break;
		default:
			console.log('Default case setSearchType');
		}
	}

	function setSearchTypeByCategoryId(categoryId){
		
		//iliminate categoryId=20 because it has = category ID 20
		if(categoryId == categoryIdConstant.MOTOR_CATEGORY_ID){
			//set the search type to car search 
			setSearchType(searchTypeConstant.ADVANCE_SEARCH);
			return;
		}
		else if(categoryId == categoryIdConstant.CAR_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_CAR);
			return;
		}else if(categoryId == categoryIdConstant.CAR_PARTS_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_CAR_PARTS);
			return;
		}else if(categoryId == categoryIdConstant.BOATS_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_BOATS);
			return;
		}else if(categoryId == categoryIdConstant.HEAVY_VEHICLE_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_HEAVY_VEHICLE);
			return;
		}else if(categoryId == categoryIdConstant.MOTORCYCLES_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_MOTORCYCLE);
			return;
		}else if(categoryId == categoryIdConstant.SALE_RES_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_SALE_RES);
			return;
		}else if(categoryId == categoryIdConstant.SALE_COMM_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_SALE_COMM);
			return;
		}else if(categoryId == categoryIdConstant.SALE_UNITS_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_SALE_UNITS);
			return;
		}else if(categoryId == categoryIdConstant.SALE_LAND_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_SALE_LAND);
			return;
		}else if(categoryId == categoryIdConstant.RENT_RES_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_RENT_RES);
			return;
		}
		else if(categoryId == categoryIdConstant.RENT_COMM_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_RENT_COMM);
			return;
		}
		else if(categoryId == categoryIdConstant.RENT_ROOM_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_RENT_ROOM);
			return;
		}
		else if(categoryId == categoryIdConstant.RENT_SHORT_DAILY_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_DAILY);
			return;
		}
		else if(categoryId == categoryIdConstant.RENT_SHORT_MONTHLY_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_MONTHLY);
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_BABY_ITEMS_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_BOOKS_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_BUSINESS_INDUSTRIAL_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_CAMERA_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_CLOTHING_CATEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_COLLECTIBLES_CATEGORY_IDEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
			return;
		}
		else if(categoryId == categoryIdConstant.CLASSIFIED_COMPUTER_NETWORKING_CATEGORY_IDEGORY_ID){
			setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
			return;
		}
		else{
			console.log('we should be here, please check this case. setSearchTypeByCategoryId()' );
		}
	}


	/*function helper for initializing fields from URL Parameters. 
	 * this function is called only once
	 */
	function initializeSearchTypeFields(searchType){
		switch(searchType) {
		case searchTypeConstant.BASIC_SEARCH:
			$scope.selectedCityId=$stateParams.basicSelectedCityId;
			$scope.finalSelectedCategoryId = $stateParams.basicSelectedCategoryId;
			$scope.selectedCategoryIdLevelOne=$stateParams.basicSelectedCategoryId;
			$scope.searchText= $stateParams.basicSearchText;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_CAR:
			initializeYears();
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			$scope.yearMin = $stateParams.advYearMin;
			$scope.yearMax = $stateParams.advYearMax;
			$scope.KMFrom =$stateParams.advKMFrom;
			$scope.KMTo = $stateParams.advKMTo;
			$scope.sellerType = $stateParams.advSellerType;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_CAR_PARTS:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			$scope.sellerType = $stateParams.advSellerType;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_BOATS:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			$scope.sellerType = $stateParams.advSellerType;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_HEAVY_VEHICLE:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			$scope.yearMin = $stateParams.advYearMin;
			$scope.yearMax = $stateParams.advYearMax;
			$scope.KMFrom =$stateParams.advKMFrom;
			$scope.KMTo = $stateParams.advKMTo;
			$scope.sellerType = $stateParams.advSellerType;
			initializeYears();
			break;
		case searchTypeConstant.ADVANCE_SEARCH_MOTORCYCLE:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			$scope.sellerType = $stateParams.advSellerType;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_RES:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			$scope.bedroomMax = $stateParams.advBedroomMax;
			$scope.bedroomMin = $stateParams.advBedroomMin;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_COMM:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_UNITS:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_SALE_LAND:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_RES:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			$scope.bedroomMax = $stateParams.advBedroomMax;
			$scope.bedroomMin = $stateParams.advBedroomMin;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_COMM:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_ROOM:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_DAILY:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			$scope.bedroomMax = $stateParams.advBedroomMax;
			$scope.bedroomMin = $stateParams.advBedroomMin;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_MONTHLY:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			$scope.bedroomMax = $stateParams.advBedroomMax;
			$scope.bedroomMin = $stateParams.advBedroomMin;
			break;
		case searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED:
			$scope.priceFrom= $stateParams.advPriceFrom;
			$scope.priceTo = $stateParams.advPriceTo;
			break;
		default:
			console.log('Default case setSearchType');
		}
	}


	/*this function will be called when user changes the sorting selection, so that we can make new request
	  based on the sorting selection */
	function createSortingArray( orderProp){
		switch(orderProp) {
		case '-created':
			$scope.sortingArray.sortColumn='created';
			$scope.sortingArray.sortDirection='desc';
			break;
		case 'created':
			$scope.sortingArray.sortColumn='created';
			$scope.sortingArray.sortDirection='asc';
			break;
		case 'productPrice':
			$scope.sortingArray.sortColumn='productPrice';
			$scope.sortingArray.sortDirection='asc';
			break;
		case '-productPrice':
			$scope.sortingArray.sortColumn='productPrice';
			$scope.sortingArray.sortDirection='desc';
			break;
		case 'productPriority':
			$scope.sortingArray.sortColumn='productPriority';
			$scope.sortingArray.sortDirection='desc';
			break;
		default:
			console.log('Default case.');
		}
	}
	//this function helps for setting the default value of the select optin
	function getCategoryByCategoryId(categories, categoryIdParam){

		for (var i = 0; i < categories.length; i++) {
			if (categories[i].categoryId == categoryIdParam) {
				return categories[i];
			}
		}
	}


	//for showing advance search Div, like price , kilo meters , home
	function showAdvanceSearchDiv(categoryId){
			//iliminate categoryId=20 because it has = category ID 20
			if(categoryId == categoryIdConstant.CAR_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_CAR);
				//FOR MOTOR CAR SEARCH
				initializeYears();
				$scope.showAdvanceSearchCarDiv= true;
				return;
			}else if(categoryId == categoryIdConstant.CAR_PARTS_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_CAR_PARTS);
				$scope.showAdvanceSearchCarPartsDiv= true;
				return;
			}else if(categoryId == categoryIdConstant.BOATS_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_BOATS);
				$scope.showAdvanceSearchBoatsDiv= true;
				return;
			}else if(categoryId == categoryIdConstant.HEAVY_VEHICLE_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_HEAVY_VEHICLE);
				$scope.showAdvanceSearchHeavyVehicleDiv= true;
				return;
			}else if(categoryId == categoryIdConstant.MOTORCYCLES_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_MOTORCYCLE);
				$scope.showAdvanceSearchMotorcycleDiv= true;
				return;
			}else if(categoryId == categoryIdConstant.SALE_RES_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_SALE_RES);
				$scope.showAdvanceSearchSaleResDiv= true;
				return;
			}else if(categoryId == categoryIdConstant.SALE_COMM_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_SALE_COMM);
				$scope.showAdvanceSearchSaleCommDiv= true;
				return;
			}else if(categoryId == categoryIdConstant.SALE_UNITS_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_SALE_UNITS);
				$scope.showAdvanceSearchSaleUnitsDiv= true;
				return;
			}else if(categoryId == categoryIdConstant.SALE_LAND_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_SALE_LAND);
				$scope.showAdvanceSearchSaleLandDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.RENT_RES_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_RENT_RES);
				$scope.showAdvanceSearchRentResDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.RENT_COMM_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_RENT_COMM);
				$scope.showAdvanceSearchRentCommDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.RENT_ROOM_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_RENT_ROOM);
				$scope.showAdvanceSearchRentRoomDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.RENT_SHORT_DAILY_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_DAILY);
				$scope.showAdvanceSearchRentShortDailyDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.RENT_SHORT_MONTHLY_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_RENT_SHORT_MONTHLY);
				$scope.showAdvanceSearchRentShortMonthlyDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.CLASSIFIED_BABY_ITEMS_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
				$scope.showAdvanceSearchClassifiedDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.CLASSIFIED_BOOKS_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
				$scope.showAdvanceSearchClassifiedDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.CLASSIFIED_BUSINESS_INDUSTRIAL_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
				$scope.showAdvanceSearchClassifiedDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.CLASSIFIED_CAMERA_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH);
				$scope.showAdvanceSearchClassifiedDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.CLASSIFIED_CLOTHING_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
				$scope.showAdvanceSearchClassifiedDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.CLASSIFIED_COLLECTIBLES_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
				$scope.showAdvanceSearchClassifiedDiv= true;
				return;
			}
			else if(categoryId == categoryIdConstant.CLASSIFIED_COMPUTER_NETWORKING_CATEGORY_ID){
				//set the search type to car search 
				setSearchType(searchTypeConstant.ADVANCE_SEARCH_CLASSIFIED);
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
				$scope.showAdvanceSearchRentShortDailyDiv= false;
				$scope.showAdvanceSearchRentShortMonthlyDiv= false;
				$scope.showAdvanceSearchClassifiedDiv= false;
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

	//initialize years needed in motor car 
	function initializeYears(){
		if($scope.years.length == 0){
			//generate years 
			var currentYear = new Date().getFullYear();
			var range = [];
			for(var i=1900; i <currentYear + 2;i++) {
				range.push(i);
			}
			$scope.years = range;
			$scope.yearMax= currentYear + 1;
		}
		return;
	}	
	
	
	
	$scope.forwardToDetailsPage = function (productId, categoryId ,categoryLft, categoryRgt){
		dispatcherDetailsFactory.forwardToDetailsPage(productId, categoryId ,categoryLft, categoryRgt);
	}

});