var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('ApplicantController', function($scope , $translate,
		applicantFactory, auth, promiseObj, bucketName) {

	/*********Advance search variables**********************/
	$scope.bucketName= bucketName;
	$scope.oneAtATime = true;
	$scope.selectedProductId;
	$scope.currentPage = 1;
	$scope.sortType = 'created';
	$scope.sortDirection  = 'asc';
	$scope.sortReverse  = false;

	(function init() {
		if(promiseObj){
			if(promiseObj.data){
				$scope.products = promiseObj.data;
			}
		}
	
	})();
	
	$scope.getNextPage= function() {
		findApplicants($scope.selectedProductId);
	}

	$scope.getApplicantsDetail = function(productId, isOpen) {
		$scope.selectedProductId= productId;
		if(!isOpen){
			findApplicants(productId);
		}
	};
	
	$scope.setSorting = function(sortType, sortDirection){		
		console.log(sortType);
		if (sortDirection=='asc') {
			$scope.sortReverse  = true;
			$scope.sortDirection='desc';
			$scope.sortType= sortType;
			
		}else{
			$scope.sortReverse  = false;
			$scope.sortDirection='asc';
			$scope.sortType= sortType;
		}
		findApplicants($scope.selectedProductId);
	}

	/***********Helper function***************/

	//get product by product id
	function findApplicants(productId){
		console.log($scope.sortType);
		console.log($scope.sortDirection);
		console.log($scope.currentPage);

		$scope.myPromise = applicantFactory.findApplicants(productId, $scope.currentPage-1 , $scope.sortType , $scope.sortDirection).success(
				function(data) {
					$scope.applicants= data;
				});
	}


	//get product by product id
	function hasUserPostedJobs(userId){
		applicantFactory.hasUserPostedJobs(userId).success(
				function(data) {
						$scope.products= data;
				});
	}	
});



