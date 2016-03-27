var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('passwordFactory', function($http) {

	var urlBase = 'http://xaashi-somalia-rest.cfapps.io/password';
	var dataFactory = {};

	dataFactory.sendLostPasswordToken = function(email) {
		return $http.put(urlBase + '/lostPassword/email/' + email);
	};
	
	dataFactory.resetPassword = function (token , passwordRequest) {
		return $http.put( urlBase + '/resetPassword/token/' + token, passwordRequest);
				 
	};
	return dataFactory;

});