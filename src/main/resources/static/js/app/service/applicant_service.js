var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('applicantFactory', function($http, $q, $translate) {

	var urlBase = 'http://maaliz-djibouti-rest.cfapps.io/applicants';
	var dataFactory = {};

	dataFactory.findApplicants = function(productId, pageIndex, sortColumn, sortDirection) {
		console.log(productId);
		return $http.get(urlBase + '/job/' + productId, {
			params: { pageIndex: pageIndex, sortColumn: sortColumn, sortDirection:sortDirection }
		});
	};
	
	dataFactory.hasUserPostedJobs = function(userId) {
		console.log(userId);
		return $http.get(urlBase + '/language/' + $translate.use() + '/user/' + userId);
	};
	
	dataFactory.applyJob = function( emailReplyJobVO, employerUserId, profileId, fileCV, jobSeekerUserId) {
		return $http({ method: 'POST',
			url: urlBase + '/reply/job', 
			params: {employerUserId: employerUserId, profileId: profileId, jobSeekerUserId: jobSeekerUserId},
			headers: {
				'Content-Type': undefined
			},
			data:  {emailReplyJobVO: emailReplyJobVO, fileCV: fileCV},
			transformRequest: function (data, headersGetter) {
				var formData = new FormData();
				formData.append("emailReplyJobVO", angular.toJson(data.emailReplyJobVO));
				if(fileCV)
					formData.append("fileCV", data.fileCV.file, data.fileCV.file.name);
				return formData;
			}
		});
	};
	return dataFactory;
});