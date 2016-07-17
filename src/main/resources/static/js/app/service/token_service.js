var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('tokenFactory', function($http) {

	var urlBase = 'http://maaliz-uae-rest.cfapps.io/verify';
	var dataFactory = {};
    
	dataFactory.registerToken = function (token) {
        return $http.put(urlBase + '/registerToken/' + token);
    };
	return dataFactory;
});