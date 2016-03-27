var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('SuccessAdController', function($scope, $state, $stateParams, 
		categoryFactory, dispatcherDetailsFactory, dispatcherPostAdNextFactory ) {

	

    $scope.categoryId;

	(function init() {
		$scope.categoryId= $stateParams.categoryId;
		$scope.categoryLft= $stateParams.categoryLft;
		$scope.categoryRgt= $stateParams.categoryRgt;
		$scope.productId= $stateParams.productId;
		
	})();
	
	$scope.forwardToDetailsPage = function (productId, categoryId ,categoryLft, categoryRgt){
		dispatcherDetailsFactory.forwardToDetailsPage(productId, categoryId ,categoryLft, categoryRgt);
	}
	
	$scope.forwardToPostAdNext= function (productId, categoryId ,categoryLft, categoryRgt){
		dispatcherPostAdNextFactory.forwardToPostAdNext(productId, categoryId ,categoryLft, categoryRgt);
	}
	
	

});


