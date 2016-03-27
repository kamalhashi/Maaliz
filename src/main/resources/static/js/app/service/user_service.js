var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('userFactory', function($http, $q, $translate) {

	var urlBase = 'http://xaashi-somalia-rest.cfapps.io/user';
	var dataFactory = {};

	dataFactory.findUserByUserId = function(userId) {
		return $http.get(urlBase + '/' + userId);
	};
	
	dataFactory.findUserByEmail = function(email) {
		return $http.get(urlBase + '/email/{email}' + email);
	};
	
	dataFactory.saveUser = function(user) {
		return $http.post(urlBase, user);
	};
	
	dataFactory.updateUserByColumnNameAndUserId = function(userId, columnName, data) {
		return $http.put(urlBase + '/' + columnName + '/' + userId + '/' + data);
	};
	
	dataFactory.isPasswordSame = function(userId, password) {
		return $http.get(urlBase + '/isPasswordSame/' + userId + '/' + password);
	};
	
	return dataFactory;

});