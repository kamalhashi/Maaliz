var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('ResetPasswordController', function($scope, $state, $translate, passwordFactory, $window , $stateParams) {

	/*********Advance search variables**********************/

	$scope.showMessage=true;
	$scope.styleMessage=null;
	$scope.message=null;
	$scope.user={};
	


	(function init() {
		$scope.token = $stateParams.token;
		console.log("initialized:"+  $stateParams.token);
		

	})();

	$scope.resetPassword = function(isValidForm) {
		if(isValidForm){
			if($scope.user.password == $scope.user.confirmPassword){
				console.log("password matching..");
				passwordFactory.resetPassword($scope.token, $scope.user).success(
						function(data) {
							if(data==true){
								console.log("data returned" + data);
								$state.go("success_reset_password");
								return;
							}
						}).error(function(error) {
							console.log("data returned" + error.message);				
							$scope.showMessage=true;
							$scope.styleMessage="alert alert-danger";
							$scope.message=error.message;
						});

			}
			
		}else{
			return;
		}

	}

});


