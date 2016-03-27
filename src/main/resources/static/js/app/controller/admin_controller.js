var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('AdminController', function($scope, $location , $state, $stateParams,  $anchorScroll, $translate,
		productFactory, auth, $window, bucketName, dispatcherAdminDetailsFactory) {

	/*********Advance search variables**********************/	
	$scope.productId;
	$scope.imageStatus;
	$scope.productPriority;
	$scope.styleMessage;
	$scope.message;
	$scope.showMessagePriorityForm;
	$scope.showMessageImageStatusForm;
	

	(function init() {

	})();

	$scope.updateImageStatusForProduct = function(isValid) {
		
		if (!isValid) {
			return;
		}
		$scope.showMessagePriorityForm=false;
		$scope.showMessageImageStatusForm=false;
		$scope.showMessageViewProductForm=false;
		$scope.showMessageDeleteProductForm=false;
		
		$scope.myPromise1=  productFactory.updateImageStatusForProduct($scope.productId, $scope.imageStatus).success(
				function(data) {
					$scope.message="Success message";
					$scope.styleMessage="alert alert-success";
					$scope.showMessageImageStatusForm=true;
				}).error(function(response, status) {
					
					if (status === 401){
						console.log(status);
						$scope.message="Unauthorized";
						$scope.styleMessage="alert alert-danger";
						$scope.showMessageImageStatusForm=true;
					}
					else{
						$scope.message=response.exception;
						$scope.styleMessage="alert alert-danger";
						$scope.showMessageImageStatusForm=true;
					}
				});


	};
	
	$scope.updatePriorityForProduct = function(isValid) {
		if (!isValid) {
			return;
		}
		$scope.showMessagePriorityForm=false;
		$scope.showMessageImageStatusForm=false;
		$scope.showMessageViewProductForm=false;
		$scope.showMessageDeleteProductForm=false;


		$scope.myPromise2=  productFactory.updatePriorityForProduct($scope.productId, $scope.productPriority).success(
				function(data) {
					$scope.message="Success message";
					$scope.styleMessage="alert alert-success";
					$scope.showMessagePriorityForm=true;
				}).error(function(response, status) {
					
					if (status === 401){
						console.log(status);
						$scope.message="Unauthorized";
						$scope.styleMessage="alert alert-danger";
						$scope.showMessagePriorityForm=true;
					}
					else{
						$scope.message=response.exception;
						$scope.showMessagePriorityForm=true;
						$scope.styleMessage="alert alert-danger";
					}
				});
	};
	
	
	$scope.viewProductDetails = function(isValid) {
		if (!isValid) {
			return;
		}
		$scope.showMessagePriorityForm=false;
		$scope.showMessageImageStatusForm=false;
		$scope.showMessageViewProductForm=false;
		$scope.showMessageDeleteProductForm=false;



		$scope.myPromise3=  productFactory.findProductWithUserByProductId($scope.productId).success(
				function(data) {
					$scope.message="Success message";
					$scope.styleMessage="alert alert-success";
					$scope.showMessageViewProductForm=true;
					dispatcherAdminDetailsFactory.forwardToDetailsPage(data.productId, data.categoriesEntity.categoryId ,data.categoriesEntity.lft, data.categoriesEntity.rgt);

				}).error(function(response, status) {
					
					if (status === 401){
						console.log(status);
						$scope.message="Unauthorized";
						$scope.styleMessage="alert alert-danger";
						$scope.showMessageViewProductForm=true;
					}
					else{
						$scope.message=response.exception;
						$scope.showMessageViewProductForm=true;
						$scope.styleMessage="alert alert-danger";
					}
				});
	};
	
	
	$scope.deleteProduct = function(isValid) {
		if (!isValid) {
			return;
		}
		$scope.showMessagePriorityForm=false;
		$scope.showMessageImageStatusForm=false;
		$scope.showMessageViewProductForm=false;
		$scope.showMessageDeleteProductForm=false;
        
		
		if (confirm("Are you sure you want to delete Ad ref:" + $scope.productId) == true) {
		
		  $scope.myPromise4= productFactory.deleteProduct($scope.productId).success(
				function(data) {
					$scope.message="Success message";
					$scope.styleMessage="alert alert-success";
					$scope.showMessageDeleteProductForm=true;

				}).error(function(response, status) {
					
					if (status === 401){
						console.log(status);
						$scope.message="Unauthorized";
						$scope.styleMessage="alert alert-danger";
						$scope.showMessageDeleteProductForm=true;
					}
					else{
						$scope.message=response.exception;
						$scope.showMessageDeleteProductForm=true;
						$scope.styleMessage="alert alert-danger";
					}
				});
		}
	}
});


