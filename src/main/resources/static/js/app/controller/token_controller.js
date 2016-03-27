var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('RegisterTokenController', function($scope, $state,
		tokenFactory, $stateParams) {
	$scope.tokenMessage="";
	$scope.tokenError=false;
	
	console.log('$stateParams' + $stateParams.token);
	
	tokenFactory.registerToken($stateParams.token).success(function(response) {
		$scope.tokenError=false;
    	}).error(function(error) {
		$scope.tokenError=true;
		$scope.tokenMessage= error.message;		
	});

});
