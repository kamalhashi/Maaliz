var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('AccountSettingsController', function($scope, $location , $state, $stateParams,  $anchorScroll, $translate,
		userFactory, auth, $window, $q, promiseObj) {

	/*********Advance search variables**********************/

	(function init() {

		if(promiseObj){
			if(promiseObj.data){
				$scope.user = promiseObj.data;
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
});


