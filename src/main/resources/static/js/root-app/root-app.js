var rootApp = angular
		.module('rootApp', [ 'ngRoute', 'ngMessages', 'ngResource', 'cgBusy',
				'flow', 'ngMap', 'ng-currency', 'pascalprecht.translate',
				'ngCookies', 'angucomplete-alt', 'ui.router', 'slick',
				'xeditable', 'fancyboxplus', 'ui.bootstrap',
				'angular-loading-bar', 'ngAnimate', 'csrf-cross-domain',
				'ngFacebook' ]);

rootApp.config(function($stateProvider, $urlRouterProvider, $httpProvider,
		$locationProvider, $translateProvider, $sceDelegateProvider,
		$facebookProvider) {
	console.log('initialzing');
	$locationProvider.html5Mode(true);

	$translateProvider.useStaticFilesLoader({
		prefix : 'languages/',
		suffix : '.json'
	});
	$translateProvider.use('en_US');
	$translateProvider.useLocalStorage();

	// For any unmatched url, redirect /home
	$urlRouterProvider.otherwise("root");
	// Now set up the states

	$stateProvider.state('root', {
		url : '/root',
		controller : 'RootController',
		templateUrl : 'root.html',
	});
});

rootApp.run(function($cookieStore, $window, $rootScope) {
	var xaashiCookie = $cookieStore.get('xaashiDomain');
	console.log(xaashiCookie);
	if (xaashiCookie) {
		$window.location.href = xaashiCookie;
	}

});


rootApp.controller('LanguageController', ['$scope', '$translate' , '$state', function ($scope, $translate, $state) {
	  $scope.switchLanguage = function (key) {
	    $translate.use(key).then(function() {
	    	$state.reload();
	    });

	   
	  };
	  
	  $scope.getCurrentLanguage = function () {
		  return $translate.use();
	  };
	  
}]);
