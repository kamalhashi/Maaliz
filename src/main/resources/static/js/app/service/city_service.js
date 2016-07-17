var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('cityFactory', function($http, $q, $translate) {

	var urlBase = 'http://maaliz-uae-rest.cfapps.io/cities';
	var dataFactory = {};


	dataFactory.getCities = function() {
		return $http.get(urlBase + '/' + $translate.use(),{cache: true});
	};
	
	dataFactory.getCityName = function(cityId) {
		return $http.get(urlBase + '/' + cityId + '/' + $translate.use());
	};
	
	return dataFactory;

});