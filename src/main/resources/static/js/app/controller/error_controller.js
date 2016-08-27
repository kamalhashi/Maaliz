var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('ErrorController', function($scope, $state, auth,categoryFactory,
		categoryRange, $stateParams, mapFactory, fileUploaderFactory, productFactory,
		$window, $translate, categoryRange,flowFactory, bucketName, NgMap,
		sellerTypeEnglish, sellerTypeSomali, $http,$timeout,lengthSomali, lengthEnglish,
		warrantyEnglish, warrantySomali, usageEnglish, usageSomali) {
	
	
	
	(function init() {
		$scope.ad= {type: 'MotorCycles', productPriority: 0, imageStatus: 'IMAGE_PENDING'};
		$scope.ad.location= mapFactory.init();
	
	})(); 

	$scope.placeChanged = function() {
		/*
		$scope.place = this.getPlace();
	    console.log('location', $scope.place.geometry.location);
	    $scope.map.setCenter($scope.place.geometry.location);
	    
	   new google.maps.Marker({
            position: $scope.place.geometry.location,
            map: $scope.map,
            draggable: false,
            animation: google.maps.Animation.DROP
          });*/
		$scope.ad.location= mapFactory.placeChanged(this.getPlace());
	  }
	
	


	//when user submits 
	$scope.saveProduct = function(isValid) {
	
	}


	

	/*********File Uploading**********/
	//remove file 
	$scope.removeFile  = function (index){
		fileUploaderFactory.removeFile(index); 
	}

	//file handler method
	$scope.fileUploadedSuccessfully  = function(file, index)	{
		fileUploaderFactory.fileUploadedSuccessfully(file, index);
	}	

	$scope.addMarker = function(event) {
		$scope.ad.location=mapFactory.addMarker(event);
	};
	

});

