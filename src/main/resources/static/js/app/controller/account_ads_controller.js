var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('AccountAdsController', function($scope, $location , $state, $stateParams,  $anchorScroll, $translate,
		 productFactory, auth, $window, promiseObj, dispatcherPostAdNextFactory) {

	/*********Advance search variables**********************/
	
	(function init() {
		if(promiseObj){
			if(promiseObj.data){
				$scope.products = promiseObj.data;
			}
		}	
		
	})();
	
	
	$scope.deleteProduct = function(productId, index ) {	
		if (confirm("Are you sure you want to delete Ad ref:" + productId) == true) {
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
		
		$scope.myPromise= productFactory.deleteProduct(productId).success(
				function(data) {
					$scope.products.splice(index, 1);
				});
		}
		
		 
	}
	
	
	$scope.forwardToPostAdNext= function (productId, categoryId ,categoryLft, categoryRgt){
		dispatcherPostAdNextFactory.forwardToPostAdNext(productId, categoryId ,categoryLft, categoryRgt);
	}
	
});


