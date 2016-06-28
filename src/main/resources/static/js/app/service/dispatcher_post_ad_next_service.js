var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('dispatcherPostAdNextFactory', function($location , $state, 
		$stateParams, categoryRange, productFactory, categoryName) {

	var dataFactory = {};
	
	/*this function will determine whether the page is details_job or details_property,
	 * jobs can be applied and is different page details.
	 */
	dataFactory.forwardToPostAdNext= function (productId, categoryId ,categoryLft, categoryRgt){
		if(categoryId > 0 ){
			if(categoryLft >= categoryRange.MOTOR_MIN   && categoryRgt <= categoryRange.MOTOR_MAX)
			{
				//is selected category a car?
				if(categoryLft >= categoryRange.MOTOR_CAR_MIN   && categoryRgt <= categoryRange.MOTOR_CAR_MAX){
					$state.go("post_ad_car",  {categoryId: categoryId, productId:productId});
					return;
				}
				//is selected category is auto accessories and parts 
				else if(categoryLft >= categoryRange.MOTOR_CAR_PARTS_MIN  && categoryRgt <= categoryRange.MOTOR_CAR_PARTS_MAX){
					$state.go("post_ad_car_parts", {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.MOTOR_BOATS_MIN  && categoryRgt <= categoryRange.MOTOR_BOATS_MAX){
					$state.go("post_ad_boats", {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.MOTOR_HEAVY_VEHICLE_MIN  && categoryRgt <= categoryRange.MOTOR_HEAVY_VEHICLE_MAX){
					$state.go("post_ad_heavy_vehicle", {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.MOTOR_MOTORCYCLES_MIN  && categoryRgt <= categoryRange.MOTOR_MOTORCYCLES_MAX){
					$state.go("post_ad_motorcycle", {categoryId: categoryId, productId:productId});
					return;
				}
			}else if(categoryLft >= categoryRange.PROPERTY_SALE_MIN  && categoryRgt <= categoryRange.PROPERTY_SALE_MAX)
			{
				if(categoryLft >= categoryRange.PROPERTY_SALE_RES_MIN   && categoryRgt <= categoryRange.PROPERTY_SALE_RES_MAX){
					$state.go("post_ad_sale_res",  {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_SALE_COMM_MIN   && categoryRgt <= categoryRange.PROPERTY_SALE_COMM_MAX){
					$state.go("post_ad_sale_comm",  {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_SALE_UNITS_MIN   && categoryRgt <= categoryRange.PROPERTY_SALE_UNITS_MAX){
					$state.go("post_ad_sale_units",  {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_SALE_LAND_MIN   && categoryRgt <= categoryRange.PROPERTY_SALE_LAND_MAX){
					$state.go("post_ad_sale_land",  {categoryId: categoryId, productId:productId});
					return;
				}

			}else if(categoryLft >= categoryRange.PROPERTY_RENT_MIN  && categoryRgt <= categoryRange.PROPERTY_RENT_MAX)
			{

				if(categoryLft >= categoryRange.PROPERTY_RENT_RES_MIN   && categoryRgt <= categoryRange.PROPERTY_RENT_RES_MAX){
					$state.go("post_ad_rent_res",  {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_RENT_COMM_MIN   && categoryRgt <= categoryRange.PROPERTY_RENT_COMM_MAX){
					$state.go("post_ad_rent_comm",  {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_RENT_ROOM_MIN   && categoryRgt <= categoryRange.PROPERTY_RENT_ROOM_MAX){
					$state.go("post_ad_rent_room",  {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_RENT_SHORT_MONTHLY_MIN   && categoryRgt <= categoryRange.PROPERTY_RENT_SHORT_MONTHLY_MAX){
					$state.go("post_ad_rent_short_term_monthly",  {categoryId: categoryId, productId:productId});
					return;
				}
				
				else if(categoryLft >= categoryRange.PROPERTY_RENT_SHORT_DAILY_MIN   && categoryRgt <= categoryRange.PROPERTY_RENT_SHORT_DAILY_MAX ){
					$state.go("post_ad_rent_short_term_daily",  {categoryId: categoryId, productId:productId});
					return;
				}


			}else if(categoryLft >= categoryRange.CLASSIFIED_MIN  && categoryRgt <= categoryRange.CLASSIFIED_MAX)
			{
				//for camera stuff 
				if(categoryLft >= categoryRange.CLASSIFIED_CAMERA_MIN   && categoryRgt <= categoryRange.CLASSIFIED_CAMERA_MAX){
					$state.go("post_ad_classified_camera",  {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.CLASSIFIED_COMPUTER_MIN   && categoryRgt <= categoryRange.CLASSIFIED_COMPUTER_MAX){
					$state.go("post_ad_classified_computer",  {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.CLASSIFIED_PETS_MIN   && categoryRgt <= categoryRange.CLASSIFIED_PETS_MAX){
					$state.go("post_ad_classified_pets",  {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.CLASSIFIED_TICKETS_MIN   && categoryRgt <= categoryRange.CLASSIFIED_TICKETS_MAX){
					$state.go("post_ad_classified_tickets",  {categoryId: categoryId, productId:productId});
					return;
				}
				else if(categoryLft >= categoryRange.CLASSIFIED_BUSINESS_SALE_MIN   && categoryRgt <= categoryRange.CLASSIFIED_BUSINESS_SALE_MAX){
					$state.go("post_ad_classified_business_sale",  {categoryId: categoryId, productId:productId});
					return;
				}		
				else{
					$state.go("post_ad_classified",  {categoryId: categoryId, productId:productId});
				    return;
				}
			}else if(categoryLft >= categoryRange.COMMUNITY_MIN  && categoryRgt <= categoryRange.COMMUNITY_MAX)
			{
					$state.go("post_ad_community",  {categoryId: categoryId, productId:productId});
					return;
			
			}else if(categoryLft >= categoryRange.JOB_MIN  && categoryRgt <= categoryRange.JOB_MAX)
			{
				$state.go("post_ad_job",  {categoryId: categoryId, productId:productId});
			    return;
			}
			else{ 
				console.log("please check this case dispatcher_post_ad_next method()");
			}
		}
	}
	
	return dataFactory;

});
