var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('LanguageController', ['$scope', '$translate' , '$state', function ($scope, $translate, $state) {
	  $scope.switchLanguage = function (key) {
	    $translate.use(key).then(function() {
	    	$state.reload();
	    });

	   
	  };
	  
	  $scope.getCurrentLanguage = function () {
		  return $translate.use();
	  };
	  
}]);