var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('emailFactory', function($http) {

	var urlBase = 'http://xaashi-somalia-rest.cfapps.io/email';
	var dataFactory = {};
 
	dataFactory.sendReplyAdByEmail = function(emailVO, userId) {
		return $http.post(urlBase + '/reply/ad/user/' +  userId, emailVO);
	};
	
	return dataFactory;
});