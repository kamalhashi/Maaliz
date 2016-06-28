var hashiApp = angular.module('hashiApp') // gets it


hashiApp.factory('dispatcherDetailsFactory', function($location , $state, 
		 categoryRange, productFactory, categoryName) {

	/*this function will determine whether the page is details_job or details_property,
	 * jobs can be applied and is different page details.
	 */

	var dataFactory = {};

	dataFactory.forwardToDetailsPage=  function (productId, categoryId ,categoryLft, categoryRgt){

		if(categoryId > 0 ){
			if(categoryLft >= categoryRange.MOTOR_MIN   && categoryRgt <= categoryRange.MOTOR_MAX)
			{
				//is selected category a car?
				if(categoryLft >= categoryRange.MOTOR_CAR_MIN   && categoryRgt <= categoryRange.MOTOR_CAR_MAX){
					$state.go("details",  { productId: productId, categoryId: categoryId, categoryType: categoryName.MOTOR_CAR });
					return;
				}
				//is selected category is auto accessories and parts 
				else if(categoryLft >= categoryRange.MOTOR_CAR_PARTS_MIN  && categoryRgt <= categoryRange.MOTOR_CAR_PARTS_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.MOTOR_CAR_PARTS});
					return;
				}
				//is selected category is auto accessories and parts 
				else if(categoryLft >= categoryRange.MOTOR_BOATS_MIN  && categoryRgt <= categoryRange.MOTOR_BOATS_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.MOTOR_BOATS});
					return;
				}
				else if(categoryLft >= categoryRange.MOTOR_HEAVY_VEHICLE_MIN  && categoryRgt <= categoryRange.MOTOR_HEAVY_VEHICLE_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.MOTOR_HEAVY_VEHICLE});
					return;
				}
				else if(categoryLft >= categoryRange.MOTOR_MOTORCYCLES_MIN  && categoryRgt <= categoryRange.MOTOR_MOTORCYCLES_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.MOTOR_MOTORCYCLES});
					return;
				}else{
					console.log('dispatcher_details_next not finding the root');
				}	
			}else if(categoryLft >= categoryRange.PROPERTY_SALE_MIN  && categoryRgt <= categoryRange.PROPERTY_SALE_MAX){
				if(categoryLft >= categoryRange.PROPERTY_SALE_RES_MIN   && categoryRgt <= categoryRange.PROPERTY_SALE_RES_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.PROPERTY_SALE_RES});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_SALE_COMM_MIN   && categoryRgt <= categoryRange.PROPERTY_SALE_COMM_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.PROPERTY_SALE_COMM});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_SALE_UNITS_MIN   && categoryRgt <= categoryRange.PROPERTY_SALE_UNITS_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.PROPERTY_SALE_UNITS});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_SALE_LAND_MIN   && categoryRgt <= categoryRange.PROPERTY_SALE_LAND_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.PROPERTY_SALE_LAND});
					return;
				}
				else{
					console.log('dispatcher_details_next not finding the root');
				}
			}else if(categoryLft >= categoryRange.PROPERTY_RENT_MIN  && categoryRgt <= categoryRange.PROPERTY_RENT_MAX){
				if(categoryLft >= categoryRange.PROPERTY_RENT_RES_MIN   && categoryRgt <= categoryRange.PROPERTY_RENT_RES_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.PROPERTY_RENT_RES});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_RENT_COMM_MIN   && categoryRgt <= categoryRange.PROPERTY_RENT_COMM_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.PROPERTY_RENT_COMM});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_RENT_ROOM_MIN   && categoryRgt <= categoryRange.PROPERTY_RENT_ROOM_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.PROPERTY_RENT_ROOM});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_RENT_SHORT_DAILY_MIN    && categoryRgt <= categoryRange.PROPERTY_RENT_SHORT_DAILY_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.PROPERTY_RENT_SHORT_DAILY});
					return;
				}
				else if(categoryLft >= categoryRange.PROPERTY_RENT_SHORT_MONTHLY_MIN   && categoryRgt <= categoryRange.PROPERTY_RENT_SHORT_MONTHLY_MAX){
					$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.PROPERTY_RENT_SHORT_MONTHLY});
					return;
				}
				else{
					console.log('dispatcher_details_next not finding the root RENT properties if. ');
				}
			}else if(categoryLft >= categoryRange.CLASSIFIED_MIN  && categoryRgt <= categoryRange.CLASSIFIED_MAX){
				$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.CLASSIFIED});
				return;
			}else if(categoryLft >= categoryRange.COMMUNITY_MIN  && categoryRgt <= categoryRange.COMMUNITY_MAX){
				$state.go("details", { productId: productId, categoryId: categoryId, categoryType: categoryName.COMMUNITY});
				return;
			}
			else if(categoryLft >= categoryRange.JOB_MIN  && categoryRgt <= categoryRange.JOB_MAX){
				$state.go("details_job", { productId: productId, categoryId: categoryId, categoryType: categoryName.JOB});
				return;
			}
			else{
				console.log('dispatcher_details_next not finding the root');
			}

		}
	}
	
	return dataFactory;

});
