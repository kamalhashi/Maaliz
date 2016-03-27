var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('RegistrationController', function($scope, $location,
		userFactory) {
	console.log('RegisterController');
	$scope.regMessage = "";
	$scope.regError = false;

	user = {
		email : "",
		confirmEmail : "",
		password : "",
		confirmPassword : ""
	};
	$scope.submit = function(isValid) {

		if (isValid) {

			userFactory.saveUser($scope.user)
			.success(function() {
				console.log('success get');
			}).error(function(error) {
				$scope.status = 'Unable to load customer data: ';
			});

			$scope.regMessage = "Submitted " + user.email;
			$location.path('/success_registration');
		} else {
			$scope.regError = true;
			$scope.regMessage = "There are still invalid fields below";
			return;
		}

	}
});
