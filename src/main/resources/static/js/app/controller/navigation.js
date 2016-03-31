var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('navigation', function($scope, auth, $http, $state, categoryIdConstant, 
		productFactory, $rootScope) {
	//login functionality 
	$scope.credentials = {};
	$scope.loginMessage = "";
	$scope.loginError = false;
	var urlBase = 'http://xaashi-somalia-rest.cfapps.io/login';

	 /***ROOT SCOPE FOR COUNT PRODUCTS***/


	(function init() {
			 initializeCountProducts();
	})();

	$scope.authenticated = function() {
		return auth.authenticated;
	}

	$scope.authenticatedFirstanme = function() {
		return auth.authenticatedFirstname;
	}

	$scope.authenticatedUserId = function() {
		return auth.authenticatedUserId;
	}

	$scope.login = function(valid) {
		if (valid) {
			 $scope.myPromise = auth.authenticate($scope.credentials, function(authenticated) {
				if (authenticated) {
					$scope.loginError = false;
				}else {
					$scope.loginMessage = "There was a problem logging in. Please try again.";
					$scope.loginError = true;
				}
			})   
		}
	}
	
	$scope.socialLogin = function() {
		 $scope.myPromise = auth.socialAuthenticate(function(authenticated)  {
				if (authenticated) {
					$scope.loginError = false;
				}else {
					$scope.loginMessage = "There was a problem logging in. Please try again.";
					$scope.loginError = true;
				}

			})  
	}

	$scope.logout = function() {
		auth.clear();
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
			countProductsAvailableByCategoryId(categoryIdConstant.RENT_HOTEL_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.RENT_SHORT_MONTHLY_CATEGORY_ID,0);
			//Job accounting 
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_ACCOUNTING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_AUTOMOTIVE_ENGINEERING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_MEDIA_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_PUBLIC_RELATIONS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_BANKING_FINANCE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_MARKETING_ADVERTISING_CATEGORY_ID,0);

			countProductsAvailableByCategoryId(categoryIdConstant.JOB_AIRLINES_AVIATION_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_ART_ENTERTAINMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_INTERIOR_DESIGN_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_BEAUTY_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_BUSINESS_DEVELOPMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_BUSINESS_SUPPLIES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_CONSULTING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_CONSTRUCTION_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_CUSTOMER_SERVICE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_EDUCATION_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_ENGINEERING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_ENV_SERVICES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_EVENT_MANAGEMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_EXECUTIVE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_FASHION_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_FOOD_BEVERAGE_CATEGORY_ID,0);
			
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_GOV_ADMIN_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_GRAPHIC_DESIGN_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_HOSPITALITY_RESTAURANT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.JOB_HR_RECRUITMENT_CATEGORY_ID,0);
			
			//classified 
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_BABY_ITEMS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_BOOKS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_BUSINESS_INDUSTRIAL_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_CAMERA_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_CLOTHING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_COLLECTIBLES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_COMPUTER_NETWORKING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_DVD_MOVIES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_ELECTRONICS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_FURNITURE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_GAMING_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_HOME_APPLIANCES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_WOMEN_JEWELRY_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_WATCHES_CATEGORY_ID,0);

			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_LOST_FOUND_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_MISC_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_MOBILE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_TICKETS_VOUCHERS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_PETS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_SPORTS_EQUIPMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.CLASSIFIED_TOYS_CATEGORY_ID,0);
			//Community stuff
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_FREE_STUFF_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_ARTIST_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_CAR_LIFT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_CHARITIES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_CHILDCARE_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_CLASSES_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_ANNOUNCEMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_EDUCATION_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_FREELANCERS_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_ISLAM_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_BUSINESS_ADVERTISMENT_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_PHOTOGRAPHY_CATEGORY_ID,0);
			countProductsAvailableByCategoryId(categoryIdConstant.COMMUNITY_SERVICES_CATEGORY_ID,0);
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
						else if(categoryId == categoryIdConstant.RENT_HOTEL_CATEGORY_ID){
							$rootScope.countRentShortDaily=data;
							return;
						}
						else if(categoryId == categoryIdConstant.RENT_SHORT_MONTHLY_CATEGORY_ID){
							$rootScope.countRentShortMonthly=data;
							return;
						}
						//JOB TOP LEVEL
						else if(categoryId == categoryIdConstant.JOB_MEDIA_CATEGORY_ID){
							$rootScope.countJobMedia=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_PUBLIC_RELATIONS_CATEGORY_ID){
							$rootScope.countJobPublicRelations=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_ACCOUNTING_CATEGORY_ID){
							$rootScope.countJobAccounting=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_AUTOMOTIVE_ENGINEERING_CATEGORY_ID){
							$rootScope.countJobAutomotiveEngineering=data;
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
						else if(categoryId == categoryIdConstant.CLASSIFIED_DVD_MOVIES_CATEGORY_ID){
							$rootScope.countClassifiedDvdMovies=data;
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
						else if(categoryId == categoryIdConstant.COMMUNITY_LOST_FOUND_CATEGORY_ID){
							$rootScope.countCommunityLostFound=data;
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
						else if(categoryId == categoryIdConstant.COMMUNITY_ANNOUNCEMENT_CATEGORY_ID){
							$rootScope.countCommunityAnnouncement=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_EDUCATION_CATEGORY_ID){
							$rootScope.countCommunityEducation=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_FREELANCERS_CATEGORY_ID){
							$rootScope.countCommunityFreelancers=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_ISLAM_CATEGORY_ID){
							$rootScope.countCommunityIslam=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_BUSINESS_ADVERTISMENT_CATEGORY_ID){
							$rootScope.countCommunityBusinessAdvertisement=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_PHOTOGRAPHY_CATEGORY_ID){
							$rootScope.countCommunityPhotography=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_SERVICES_CATEGORY_ID){
							$rootScope.countCommunityServices=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_SPORTS_CATEGORY_ID){
							$rootScope.countCommunitySports=data;
							return;
						}
						else if(categoryId == categoryIdConstant.COMMUNITY_FREE_STUFF_CATEGORY_ID){
							$rootScope.countCommunityFreeStuff=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_AIRLINES_AVIATION_CATEGORY_ID){
							$rootScope.countJobAirlinesAviation=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_ART_ENTERTAINMENT_CATEGORY_ID){
							$rootScope.countJobArtEntertainment=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_INTERIOR_DESIGN_CATEGORY_ID){
							$rootScope.countJobInteriorDesign=data;
							return;
						}
						
						
						else if(categoryId == categoryIdConstant.JOB_BANKING_FINANCE_CATEGORY_ID){
							$rootScope.countJobBankingFinance=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_BEAUTY_CATEGORY_ID){
							$rootScope.countJobBeauty=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_BUSINESS_DEVELOPMENT_CATEGORY_ID){
							$rootScope.countJobBusinessDevelopment=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_BUSINESS_SUPPLIES_CATEGORY_ID){
							$rootScope.countJobBusinessSupplies=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_CONSTRUCTION_CATEGORY_ID){
							$rootScope.countJobConstruction=data;
							return;
						}
						else if(categoryId == categoryIdConstant.JOB_CONSULTING_CATEGORY_ID){
							$rootScope.countJobConsulting=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_CUSTOMER_SERVICE_CATEGORY_ID){
							$scope.countJobCustomerService=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_EDUCATION_CATEGORY_ID){
							$rootScope.countJobEducation=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_ENGINEERING_CATEGORY_ID){
							$rootScope.countJobEngineering=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_ENV_SERVICES_CATEGORY_ID){
							$rootScope.countJobEnvServices=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_EVENT_MANAGEMENT_CATEGORY_ID){
							$rootScope.countJobEventManagement=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_EXECUTIVE_CATEGORY_ID){
							$rootScope.countJobExecutive=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_FASHION_CATEGORY_ID){
							$rootScope.countJobFashion=data;
							return;
						}
					
						else if(categoryId == categoryIdConstant.JOB_FOOD_BEVERAGE_CATEGORY_ID){
							$rootScope.countJobFoodBeverage=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_GOV_ADMIN_CATEGORY_ID){
							$rootScope.countJobGovAdmin=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_GRAPHIC_DESIGN_CATEGORY_ID){
							$rootScope.countJobGraphicDesign=data;
							return;
						}
						
						else if(categoryId == categoryIdConstant.JOB_HOSPITALITY_RESTAURANT_CATEGORY_ID){
							$rootScope.countJobHospitalityRestaurant=data;
							return;
						}
							
						else if(categoryId == categoryIdConstant.JOB_HR_RECRUITMENT_CATEGORY_ID){
							$rootScope.countJobHRRecruitment=data;
							return;
						}
						
					});
		}


});