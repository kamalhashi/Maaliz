var hashiApp = angular
		.module('hashiApp', [ 'ngRoute', 'ngMessages', 'ngResource', 'cgBusy',
				'flow', 'ngMap', 'ng-currency', 'pascalprecht.translate',
				'ngCookies', 'angucomplete-alt', 'ui.router', 'slick',
				'xeditable', 'fancyboxplus', 'ui.bootstrap',
				'angular-loading-bar', 'ngAnimate', 'csrf-cross-domain',
				'ngFacebook' ]);

hashiApp.config(function($stateProvider, $urlRouterProvider, $httpProvider,
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

hashiApp.run(function($cookieStore, $window, $rootScope) {
	var xaashiCookie = $cookieStore.get('xaashiDomain');
	console.log(xaashiCookie);
	if (xaashiCookie) {
		$window.location.href = xaashiCookie;
	}

});
