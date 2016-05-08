var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('DetailsController', function($scope, $location , $state, $stateParams,  $anchorScroll, $translate, bucketName,
		categoryRange, productFactory, categoryFactory, cityFactory, profileFactory, flowFactory,auth, categoryName, ageSomali, ageEnglish,
		lengthEnglish, lengthSomali, horsePowerEnglish, horsePowerSomali, noCylindersEnglish, noCylindersSomali, 
		bodyTypeCarSomali, bodyTypeCarEnglish, sellerTypeEnglish, sellerTypeSomali,coloursEnglish, coloursSomali,
		fuelTypeCarEnglish, fuelTypeCarSomali, transmissionCarEnglish , transmissionCarSomali, $sce,
		noBedroomsApartmentEnglish, noBedroomsApartmentSomali,ownerTypeEnglish, ownerTypeSomali, conditionSomali, conditionEnglish,
		noBathroomsEnglish, noBathroomsSomali, furnishedEnglish, furnishedSomali, rentTimeEnglish, rentTimeSomali,
		cameraBrand, warrantyEnglish, warrantySomali, computerBrand, computerHardDrive, computerProcessor, computerMemory,
		careerLevelEnglish, careerLevelSomali, employmentTypeEnglish, employmentTypeSomali, $http, $timeout,
		workExperienceEnglish, workExperienceSomali, educationLevelEnglish, educationLevelSomali,  $window,
		monthSalaryEnglish, monthSalarySomali, cvRequiredSomali, cvRequiredEnglish, emailFactory, applicantFactory,
		listedBySomali, listedByEnglish, companySizeEnglish, companySizeSomali, searchTypeConstant,
		hideCompanyNameSomali, hideCompanyNameEnglish, categoryIdConstant, sellerTypeSearchSomali, sellerTypeSearchEnglish,
		promiseCities, promiseCategories, promiseListRootCategories, promiseProduct) {

	/****Job Search CV  stuff ****/

	$scope.fileCV;
	$scope.linkCVDownload;
	$scope.existingImageObjectZero= flowFactory.create({
		singleFile: true
	});

	/*********Advance search variables**********************/
	$scope.replyMessage;
	$scope.showReplyMessage=false;
	//advanced search
	$scope.advSelectedCityId=0;
	$scope.advTextSearch=null;
	$scope.categoryRange= categoryRange;

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
	$scope.images= [];
	//job STUFF
	$scope.profileId=0;
	$scope.replyJob={};

	(function init() {
		
		//get the product
		//findProductWithUserByProductId($stateParams.productId);
        $scope.product= promiseProduct.data;
        isProductHasImages( $scope.product);

		//initialize root categories left div search
		$scope.listCities= promiseCities.data;
		
		//initializeCategoriesLevelOne();
		$scope.categories= promiseCategories.data;
		$scope.advCategoryLevelOneObject= $scope.categories[0];
		// load data, init scope, etc. 
		//getRootCategoriesByCategoryId($stateParams.categoryId);
		$scope.listRootCategories= promiseListRootCategories.data;
		
		
		/*if the category Type is job then load user profile and 
		 * create object flow for the file , only if the user has profile
		 */


		/*this will help determine which category we dealing with,
		 * based on the category type we will set relevant parameters 
		 */
		if(auth){
			if(auth.authenticated){
				$scope.reply={name: auth.authenticatedFirstname, email: auth.authenticatedEmail , telephone: auth.authenticatedTelephone};
			}

		}

		$scope.cameraBrandConstant= cameraBrand;
		$scope.computerBrandConstant= computerBrand;
		$scope.computerHardDriveConstant= computerHardDrive;
		$scope.computerProcessorConstant=computerProcessor;
		$scope.computerMemoryConstant= computerMemory;
		$scope.brandConstant= cameraBrand;

		if($translate.use() === 'so_SO'){
			$scope.warrantyConstant= warrantySomali;
			$scope.ageConstant= ageSomali; 
			$scope.conditionConstant= conditionSomali; 
			$scope.constantCareerLevel=careerLevelSomali;	 
			$scope.constantEmploymentType= employmentTypeSomali;
			$scope.constantWorkExperience = workExperienceSomali;
			$scope.constantEducationLevel = educationLevelSomali;
			$scope.constantMonthSalary = monthSalarySomali;
			$scope.constantCVRequired = cvRequiredSomali;
			$scope.constantListedBy = listedBySomali;
			$scope.constantCompanySize = companySizeSomali;
			$scope.constantHideCompanyName = hideCompanyNameSomali;
			$scope.listSellerTypes= sellerTypeSearchSomali; 

			$scope.sellerTypeConstant= sellerTypeSomali; 
			$scope.fuelTypeCarConstant= fuelTypeCarSomali; 
			$scope.horsePowerConstant= horsePowerSomali;
			$scope.noCylindersConstant= noCylindersSomali;

			$scope.transmissionCarConstant= transmissionCarSomali;
			$scope.coloursCarConstant=coloursSomali;
			$scope.bodyTypeCarConstant= bodyTypeCarSomali; 
			$scope.ageItemConstant= ageEnglish; 
			$scope.lengthBoatsConstant= lengthSomali;

			//SALE RESIDENTIAL
			$scope.constantNoBedrooms= noBedroomsApartmentSomali;
			$scope.constantNoBathrooms= noBathroomsSomali;
			$scope.constantOwnerType= ownerTypeSomali; 

			//RENT PROPERTIES 
			$scope.constantFurnished= furnishedSomali; 
			$scope.constantRentTime=  rentTimeSomali; 

		}
		else if($translate.use() === 'en_US'){
			$scope.listSellerTypes= sellerTypeSearchEnglish; 
			$scope.warrantyConstant= warrantyEnglish;
			$scope.ageConstant= ageEnglish; 
			$scope.conditionConstant= conditionEnglish;
			$scope.constantCareerLevel= careerLevelEnglish;	 
			$scope.constantEmploymentType= employmentTypeEnglish;
			$scope.constantWorkExperience = workExperienceEnglish;
			$scope.constantEducationLevel = educationLevelEnglish;
			$scope.constantMonthSalary = monthSalaryEnglish;
			$scope.constantCVRequired = cvRequiredEnglish;
			$scope.constantListedBy = listedByEnglish;
			$scope.constantCompanySize = companySizeEnglish;
			$scope.constantHideCompanyName = hideCompanyNameEnglish;

			$scope.sellerTypeConstant= sellerTypeEnglish; 
			$scope.fuelTypeCarConstant= fuelTypeCarEnglish; 
			$scope.horsePowerConstant= horsePowerEnglish;
			$scope.noCylindersConstant= noCylindersEnglish;

			$scope.transmissionCarConstant= transmissionCarEnglish;
			$scope.coloursCarConstant=coloursEnglish;
			$scope.bodyTypeCarConstant= bodyTypeCarEnglish; 
			$scope.ageItemConstant= ageEnglish; 
			$scope.lengthBoatsConstant= lengthEnglish;

			//SALE RESIDENTIAL
			$scope.constantNoBedrooms= noBedroomsApartmentEnglish;
			$scope.constantNoBathrooms= noBathroomsEnglish;
			$scope.constantOwnerType= ownerTypeEnglish; 

			$scope.constantFurnished= furnishedEnglish; 
			$scope.constantRentTime=  rentTimeEnglish; 

		}

		if($stateParams.categoryType== categoryName.JOB){
			if(auth){
				if(auth.authenticated){
					findProfileByUserId(auth.authenticatedUserId);
				}
			}
		}

	})();

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


	$scope.sendReplyAdByEmail = function(replyAdForm) {
		$scope.postAdCarError=false;
		$scope.postAdCarErrorMessage='';
		if (replyAdForm) {
			if($scope.showReplyMessage==false){
				$scope.replyAd.type= 'EmailReplyAdVO';
				$scope.replyAd.mailType= 'REPLY_AD';
				$scope.replyAd.productId= $scope.product.productId;
				$scope.replyAd.productTitle= $scope.product.productTitle;

				$scope.myPromise=emailFactory.sendReplyAdByEmail($scope.replyAd, $scope.product.user.userId).success(
						function(result) {
							$scope.replyMessage="Success!. Your email has been sent";
							$scope.showReplyMessage=true;
							$scope.styleMessage="alert alert-success";
							$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
						});
				return;
			}else{
				$scope.replyMessage="Sorry your email has been sent already.!"; 
				$scope.styleMessage="alert alert-danger";
				$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
				return;
			}
		}
		return;
	}


	$scope.sendReplyJobByEmail = function() {
		if(auth){
			if(auth.authenticated){
				if($scope.showReplyMessage==false){
					$scope.replyJob.type= 'EmailReplyJobVO';
					$scope.replyJob.mailType= 'REPLY_JOB';
					$scope.replyJob.productId= $scope.product.productId;
					$scope.replyJob.productTitle= $scope.product.productTitle;
					/*******/
					$scope.myPromise=applicantFactory.applyJob($scope.replyJob, $scope.product.user.userId, $scope.profileId, $scope.fileCV, auth.authenticatedUserId).success(
							function(result) {
								$scope.replyMessage="Success !.  You have applied Successfully!";
								$scope.showReplyMessage=true;
								$scope.styleMessage="alert alert-success";
								$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
							}).error(function(error) {
								$scope.showReplyMessage=true;
								$scope.replyMessage=error.message;
								$scope.styleMessage="alert alert-danger";
								$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop); 	
							});
					return;
				}else{
					$scope.replyMessage="Sorry. Your have already applied.";
					$scope.styleMessage="alert alert-danger";
					$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
					return;
				}
			}else{
				$scope.showReplyMessage=true;
				$scope.replyMessage="Sorry. To apply a job first you need to login.";
				$scope.styleMessage="alert alert-danger";
				$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
				return;
			}
		}
		return;
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
			advBedroomMax: $scope.bedroomMax
		} );

	}

	$scope.toTrustedHTML = function( html ){
		return $sce.trustAsHtml( html );
	}
	//for scrolling #reply 
	$scope.scrollTo = function(id) {
		$location.hash(id);
		$anchorScroll();
	}


	/***********Helper function***************/

	//get root categories of a category id
	function getRootCategoriesByCategoryId(categoryId) {
		categoryFactory.getRootCategoriesByCategoryId(categoryId).success(
				function(data) {
					$scope.listRootCategories= data
				});
	}
	//get product by product id
	function findProductWithUserByProductId(productId){
		productFactory.findProductWithUserByProductId($stateParams.productId).success(
				function(data) {
					$scope.product= data;
					if($scope.product.images){
						loadImagesFromAmazon($scope.product);
					}else{

					}
				});
	}
	
	function isProductHasImages(product){
		if($scope.product.images){
			loadImagesFromAmazon($scope.product);
		}

	}


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


	//this function helps for setting the default value of the select optin
	function getCategoryByCategoryId(categories, categoryIdParam){

		for (var i = 0; i < categories.length; i++) {
			if (categories[i].categoryId == categoryIdParam) {
				return categories[i];
			}
		}
	}

	//initialize years 
	function initializeYears(){
		//generate years 
		var currentYear = new Date().getFullYear();
		var range = [];
		$scope.years=[];
		for(var i=1900; i <currentYear + 2;i++) {
			range.push(i);
		}
		$scope.years = range;
		$scope.yearMax= currentYear + 1;
		return;
	}

	//for showing advance search Div, like price , kilo meters , home
	function showAdvanceSearchDiv(categoryId){
		//iliminate categoryId=20 because it has = category ID
		if(categoryId == categoryIdConstant.CAR_CATEGORY_ID){
			initializeYears();
			$scope.showAdvanceSearchCarDiv= true;
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
			return;
		}
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


	//get profile that belongs to a user by userId
	function findProfileByUserId(userId){
		profileFactory.findProfileByUserId(userId).success(
				function(data) {
					if(data){
						$scope.profile= data;
						$scope.profileExist=true;
						if(data.profileId)
							$scope.profileId= $scope.profile.profileId;
						if(data.coverLetter)
							$scope.replyJob.coverLetter = data.coverLetter;
						if(data.cvName)
							getImageFromAmazon(userId, bucketName.profileCV , data.cvName);
					}
				});
	}


	function getImageFromAmazon(userId, bucket, fileName){
		$scope.myPromise = $http.get(bucket + userId + '/' + fileName , {responseType: "arraybuffer"}).
		success(function(data) {
			$timeout(function() {
				var  blob = new Blob( [ data ], { type: "*" } );
				blob.name = fileName;
				$scope.existingImageObjectZero.addFile(blob);
				$scope.linkCVDownload=bucket + userId + '/' + fileName ;
			}, 0);

		}).
		error(function(data, status) {
			$scope.postAdError=true;
			$scope.postAdErrorMessage='Error in uploading images';
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
		});
	}


	/******File upload*****/

	//file handler method
	$scope.fileCVUploadedSuccessfully  = function(file, index){
		$scope.fileCV = file;
	}	

	$scope.removeProfileCVFile  = function (){
		$scope.fileCV= null;
	}
	
	
	/*
	 * load images  from amazon s3 storage, this function is to update product images
	 */
	function loadImagesFromAmazon(product){		
		for (var i = 0; i < product.images.length; i++) {
				$scope.images[i] = bucketName.productImages + product.productId + '/' + product.images[i].imageName;
		}
	      $scope.mainImageUrl = $scope.images[0];
	}
	
	$scope.setImage = function(imageUrl) {
	      $scope.mainImageUrl = imageUrl;
	};
});


