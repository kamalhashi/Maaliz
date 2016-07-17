var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('profileFactory', function($http, $q, $translate) {

	var urlBase = 'http://maaliz-uae-rest.cfapps.io/profile';
	var dataFactory = {};

	dataFactory.findProfileByUserId = function(userId) {
		console.log(userId);
		return $http.get(urlBase + '/user/' + userId);
	};


	dataFactory.createNewProfile = function( profile, userId, profileCV, profileImage) {
		return $http({ method: 'POST',
			url: urlBase, 
			params: {userId: userId},
			headers: {
				'Content-Type': undefined
			},
			data:  {profile: profile, profileCV: profileCV, profileImage: profileImage},
			transformRequest: function (data, headersGetter) {
				var formData = new FormData();
				formData.append("profile", angular.toJson(data.profile));
				if(profileCV)
					formData.append("profileCV", data.profileCV.file, data.profileCV.file.name);
				if(profileImage)
					formData.append("profileImage", data.profileImage.file, data.profileImage.file.name);
				return formData;
			}
		});
	};

	dataFactory.updateProfile = function( profile, userId, profileCV, profileImage) {
		return $http({ method: 'POST',
			url: urlBase + '/update', 
			params: {userId: userId},
			headers: {
				'Content-Type': undefined
			},
			data:  {profile: profile, profileCV: profileCV, profileImage: profileImage},
			transformRequest: function (data, headersGetter) {
				var formData = new FormData();
				formData.append("profile", angular.toJson(data.profile));
				if(profileCV)
					formData.append("profileCV", data.profileCV.file, data.profileCV.file.name);
				if(profileImage)
					if(profileImage.file)
						formData.append("profileImage", data.profileImage.file, data.profileImage.file.name);
				return formData;
			}
		});
	};
	return dataFactory;
});