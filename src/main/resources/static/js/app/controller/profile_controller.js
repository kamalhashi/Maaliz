var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('AccountProfileController', function($scope, $location , $state, $stateParams,  $anchorScroll, $translate,
		profileFactory, auth, $window, careerLevelEnglish, careerLevelSomali, fileUploaderFactory, flowFactory , $http,
		bucketName, $timeout, promiseObj) {

	/*********Advance search variables**********************/
	$scope.fileCV;
	$scope.fileImage;
	$scope.linkCVDownload;
	$scope.bucketOtherImage= bucketName.otherImage;
	$scope.existingImageObjectZero= flowFactory.create({
		singleFile: true
	});

	$scope.existingImageObjectOne= flowFactory.create({
		singleFile: true
	});


	(function init() {
		if($translate.use() === 'so_SO'){
			$scope.constantCareerLevel=careerLevelSomali;	
		}
		else if($translate.use() === 'en_US'){
			$scope.constantCareerLevel=careerLevelEnglish;	
		}

		if(promiseObj){
			if(promiseObj.data){
				$scope.profile= promiseObj.data;
				$scope.profileExist=true;
				if(promiseObj.data.imageName)
					getImageFromAmazon(auth.authenticatedUserId, bucketName.profileImage , promiseObj.data.imageName);
				if(promiseObj.data.cvName)
					getImageFromAmazon(auth.authenticatedUserId, bucketName.profileCV , promiseObj.data.cvName);
			}else{
				$scope.profileExist=false;
			}
		}

	})();

	$scope.submit = function(validForm) {
		if (validForm) {
			if($scope.profileExist == true){
				$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop); 
				$scope.promiseSubmit= profileFactory.updateProfile($scope.profile,auth.authenticatedUserId, $scope.fileCV, $scope.fileImage)
				.success( function(result) {
					$state.go("success_profile");
					return;
				});
			}	
			else if($scope.profileExist == false){
				$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop); 
				$scope.promiseSubmit= profileFactory.createNewProfile($scope.profile,auth.authenticatedUserId, $scope.fileCV, $scope.fileImage)
				.success( function(result) {
					$state.go("success_profile");
					return;
				});

			}
		}
		console.log("form not valid");
	}



	/***********Helper function***************/


	//get profile that belongs to a user by userId
	function findProfileByUserId(userId){
		profileFactory.findProfileByUserId(userId).success(
				function(data) {
					if(data){
						$scope.profile= data;
						$scope.profileExist=true;
						if(data.imageName)
							getImageFromAmazon(userId, bucketName.profileImage , data.imageName);
						if(data.cvName)
							getImageFromAmazon(userId, bucketName.profileCV , data.cvName);
					}else{
						$scope.profileExist=false;
					}
				});
	}

	/******File upload*****/

	//file handler method
	$scope.fileCVUploadedSuccessfully  = function(file, index){
		$scope.fileCV = file;
	}	

	$scope.fileImageUploadedSuccessfully  = function(file, index){
		$scope.fileImage= file;
	}

	$scope.removeProfileImageFile  = function (){
		$scope.fileImage= null;
	}

	$scope.removeProfileCVFile  = function (){
		$scope.fileCV= null;
	}


	/*************************/
	function getImageFromAmazon(userId, bucket, fileName){

		switch (bucket) {
		case bucketName.profileImage:
			$scope.promiseExistingImageObjectZero = $http.get(bucket + userId + '/' + fileName , {responseType: "arraybuffer"}).
			success(function(data) {
				$timeout(function() {
					var  blob = new Blob( [ data ], { type: "image/jpeg" } );
					blob.name = fileName;
					$scope.existingImageObjectZero.addFile(blob);
				}, 0);

			}).
			error(function(data, status) {
				$scope.postAdError=true;
				$scope.postAdErrorMessage='Error in uploading images';
				$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
			});
			break;
		case bucketName.profileCV:
			$scope.promiseExistingImageObjectOne = $http.get(bucket + userId + '/' + fileName , {responseType: "arraybuffer"}).
			success(function(data) {
				$timeout(function() {
					var  blob = new Blob( [ data ], { type: "*" } );
					blob.name = fileName;
					$scope.existingImageObjectOne.addFile(blob);
					$scope.linkCVDownload=bucket + userId + '/' + fileName ;
				}, 0);

			}).
			error(function(data, status) {
				$scope.postAdError=true;
				$scope.postAdErrorMessage='Error in uploading images';
				$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
			});
			break;

		default:
			console.log("default case..");
		break;
		}
	}
});


