var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('categoryFactory', function($http, $q, $translate) {

	var urlBase = 'http://xaashi-rest.cfapps.io/categories';
	var dataFactory = {};

    
	dataFactory.getSubCategoriesByCategoryId = function(categoryId) {
		return $http.get(urlBase + '/' + categoryId + '/categories/' + $translate.use(),{cache: true});
	};

	dataFactory.hasChildCategories = function(categoryId) {
		return $http.get(urlBase + '/' + categoryId + '/hasChildCategories' + '/' + $translate.use());
	};
	
	dataFactory.getRootCategories = function() {
		return $http.get(urlBase + '/root/categories/' + $translate.use(),{cache: true});
	};
	
	dataFactory.getCategoryByCategoryId = function(categoryId) {
		return $http.get(urlBase + '/category/' + categoryId + '/' + $translate.use());
	};
	
	dataFactory.getRootCategoriesByCategoryId = function(categoryId) {
		return $http.get(urlBase  + '/root/' + categoryId + '/categories' + '/' + $translate.use());
	};
	
	return dataFactory;

});