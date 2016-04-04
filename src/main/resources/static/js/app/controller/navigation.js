var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('navigation', function($scope, auth, $http, $state) {
	//login functionality 
	$scope.credentials = {};
	$scope.loginMessage = "";
	$scope.loginError = false;
	var urlBase = 'http://xaashi-somalia-rest.cfapps.io/login';


	$scope.authenticated = function() {
		return auth.authenticated;
	}

	$scope.authenticatedFirstanme = function() {
		return auth.authenticatedFirstname;
	}

	$scope.authenticatedUserId = function() {
		return auth.authenticatedUserId;
	}

	$scope.login = function(valid) {
		if (valid) {
			 $scope.myPromise = auth.authenticate($scope.credentials, function(authenticated) {
				if (authenticated) {
					$scope.loginError = false;
				}else {
					$scope.loginMessage = "There was a problem logging in. Please try again.";
					$scope.loginError = true;
				}
			})   
		}
	}
	
	$scope.socialLogin = function() {
		 $scope.myPromise = auth.socialAuthenticate(function(authenticated)  {
				if (authenticated) {
					$scope.loginError = false;
				}else {
					$scope.loginMessage = "There was a problem logging in. Please try again.";
					$scope.loginError = true;
				}

			})  
	}

	$scope.logout = function() {
		auth.clear();
	}
		
});