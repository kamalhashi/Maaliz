var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('ForgotPasswordController', function($scope, $state, $translate, passwordFactory,
		$window, $state) {

	/*********Advance search variables**********************/

	$scope.showMessage=true;
	$scope.styleMessage=null;
	$scope.message=null;
	$scope.user={};
	


	(function init() {
		console.log("initialized");

	})();

	$scope.sendLostPasswordToken = function(isValidForm) {
		if(isValidForm){
			passwordFactory.sendLostPasswordToken($scope.user.email).success(
					function(data) {
						console.log("data returned" + data.message);	
						$state.go('success_forgot_password');
					}).error(function(error) {
						console.log("data returned" + error.message);				
						$scope.showMessage=true;
						$scope.styleMessage="alert alert-danger";
						$scope.message=error.message;
					});
		}else{
			return;
		}

	}

});


