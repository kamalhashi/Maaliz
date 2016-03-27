var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('AccountSettingsController', function($scope, $location , $state, $stateParams,  $anchorScroll, $translate,
		userFactory, auth, $window, $q) {

	/*********Advance search variables**********************/

	(function init() {




		if(auth){
			if(auth.authenticated){
				findUserByUserId(auth.authenticatedUserId);
			}
		}	

	})();


	$scope.updateUser= function(data, userId, columnName ) {	
		userFactory.updateUserByColumnNameAndUserId(userId, columnName, data);
	}

	//just make sure the field is not empty 
	$scope.checkInput = function(data, id) {
		if (!data) {
			return "This field is required";
		}
	};
	
	//for the password change 
	$scope.confirmPassword = function(confirmPassword, newPassword,  id) {
		if (!confirmPassword) {
			return "This field is required";
		}
		else if (newPassword != confirmPassword){
			return "Password does not match";

		}
	}
	//check oldPassword with user entered password
	$scope.checkOldPassword = function(data, id) {
	    var d = $q.defer();

		if (!data) {
			return "This field is required!";
		} else{
			userFactory.isPasswordSame(auth.authenticatedUserId, data).success(
					function(data) {
						if(data){
							d.resolve();
						}else{
					        d.resolve("Wrong password")
						}
					});
		}
		return d.promise;
	};

	/***********Helper function***************/

	//get product by product id
	function findUserByUserId(userId){
		userFactory.findUserByUserId(userId).success(
				function(data) {
					$scope.user= data;
				});
	}

});


