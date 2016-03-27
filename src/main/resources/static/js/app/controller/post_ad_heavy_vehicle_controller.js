var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('PostAdHeavyVehicleController', function($scope, $state, auth,categoryFactory,
		productFactory, categoryRange, $stateParams, mapFactory, fileUploaderFactory, $window, 
		coloursEnglish, coloursSomali, $translate, categoryRange, bodyTypeCarSomali, flowFactory, bucketName,
		$http, $timeout,horsePowerEnglish, horsePowerSomali, noCylindersEnglish,noCylindersSomali,
		bodyTypeCarEnglish ,fuelTypeCarEnglish, fuelTypeCarSomali, transmissionCarEnglish , transmissionCarSomali, 
		sellerTypeEnglish, sellerTypeSomali) {


	//bucket name variable to show the images products in featured listing
	$scope.bucketName= bucketName;

	
	$scope.postAdErrorMessage= null;
	$scope.postAdError= false;
	$scope.listRootCategories = [];
	/*get the last object of root categories, which means this category 
	 *object we dealing with.
	 */
	$scope.categoryObject=[];	
	//selected category from post_ad, passed in url as parameter
	$scope.categoryId;
	//for the year select option, year made
	var range = [];
	$scope.years=[];

	//for editing page, when product wanted to be updated 
	$scope.productId;
	var locationIdObject=null;
	$scope.S3_URL = bucketName.productImages;
	$scope.existingImageObjectZero= flowFactory.create({
		singleFile: true
	});
	$scope.existingImageObjectOne= flowFactory.create({
		singleFile: true
	});
	$scope.existingImageObjectTwo= flowFactory.create({
		singleFile: true
	});
	$scope.existingImageObjectThree= flowFactory.create({
		singleFile: true
	});
	$scope.existingImageObjectFour= flowFactory.create({
		singleFile: true
	});
	$scope.existingImageObjectFive= flowFactory.create({
		singleFile: true
	});
	$scope.existingImageObjectSix= flowFactory.create({
		singleFile: true
	});




	(function init() {
		fileUploaderFactory.initializeFileUploader();

		if($translate.use() === 'so_SO'){
			$scope.constantColours= coloursSomali;
			$scope.constantBodyType= bodyTypeCarSomali;
			$scope.constantFuelType= fuelTypeCarSomali;
			$scope.constantTransmission = transmissionCarSomali;
			$scope.constantSellerType= sellerTypeSomali;
			$scope.constantNoCylinders=noCylindersSomali;
			$scope.constantHorsePower =horsePowerSomali;


		} 
		if($translate.use() === 'en_US'){
			$scope.constantColours= coloursEnglish;
			$scope.constantBodyType= bodyTypeCarEnglish;
			$scope.constantFuelType= fuelTypeCarEnglish;
			$scope.constantTransmission = transmissionCarEnglish;
			$scope.constantSellerType= sellerTypeEnglish;
			$scope.constantNoCylinders =noCylindersEnglish;
			$scope.constantHorsePower =horsePowerEnglish;
		}
		
		$scope.categoryId= $stateParams.categoryId;
		//showing root category names of a categoryId.
		getRootCategoriesByCategoryId($scope.categoryId);
		//get the registered user phone and 
		$scope.telephone=auth.authenticatedTelephone;

		//to generate years from 1900 - to current year for the select option
		var currentYear = new Date().getFullYear();
		for(var i=1900; i <currentYear + 2;i++) {
			range.push(i);
		}
		$scope.years = range;


		/*********only specific for the editing product*******************/
		//for edit page product, first laod the productId
		$scope.productId=$stateParams.productId;
		if($scope.productId > 0){
			findProductByProductId($scope.productId );
			$scope.updateButton=true;
		}
		/********only specific for the new product**********/
		else{
			//this is important for JSON back-end server
			$scope.ad= {type: 'MotorHeavyVehicle', productPriority: 0, imageStatus: 'IMAGE_PENDING'};
			//the map to be the last thing to be initialized
			$scope.ad.location= mapFactory.init();
		}

	})();
	

	//when user submits 
	$scope.saveProduct = function(isValid) {
		$scope.postAdError=false;
		$scope.postAdErrorMessage='';
		if (!isValid) {
			$scope.postAdError=true;
			$scope.postAdErrorMessage='There are incomplete required fields. Please complete them';
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
			return;
		}
		if(typeof $scope.ad.productLanguage === 'undefined' || $scope.ad.productLanguage==false){
			$scope.postAdError=true;
			$scope.postAdErrorMessage='Tick language for your ad.';
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop); 
			return;
		}
		else{
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop); 
			/*for saving and editing is diffrent because now locations is sent to the server as location object, 
			 * why because of the multipart 
			 */
			$scope.location= $scope.ad.location;

			$scope.myPromise =  productFactory.saveProduct(fileUploaderFactory.getFiles(), $scope.ad,  $scope.location, $stateParams.categoryId,  auth.authenticatedUserId, $scope.telephone)
			.success( function(result) {
				$state.go("success_ad" , {productId: result, categoryId: $scope.categoryId, 
					categoryLft: $scope.categoryObject.lft, categoryRgt:$scope.categoryObject.rgt});
			});


		}

	}

	//when user submits 
	$scope.updateProduct = function(isValid) {
		$scope.postAdError=false;
		$scope.postAdErrorMessage='';
		if (!isValid) {
			$scope.postAdError=true;
			$scope.postAdErrorMessage='There are incomplete required fields. Please complete them';
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
			return;
		}
		if(typeof $scope.ad.productLanguage === 'undefined'){
			$scope.postAdError=true;
			$scope.postAdErrorMessage='Tick language for your ad.';
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  

		}
		else{
			//after saving go to the top of the page
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);
			angular.extend($scope.ad.location, locationIdObject , $scope.ad.location);
			//save the products and process to success page 				
			$scope.myPromise =  productFactory.updateProduct(fileUploaderFactory.getFiles(), $scope.ad, auth.authenticatedUserId, $scope.telephone)
			.success( function(result) {
				auth.authenticatedTelephone=$scope.telephone;
				$state.go("success_ad" , {productId: result, categoryId: $scope.categoryId, 
					categoryLft: $scope.categoryObject.lft, categoryRgt:$scope.categoryObject.rgt});
			});


		}

	}


	//get root categories of selected category
	function getRootCategoriesByCategoryId(categoryId) {
		categoryFactory.getRootCategoriesByCategoryId(categoryId).success(
				function(data) {
					$scope.listRootCategories= data;
					/*we need the last object of category, because we need categories.lft categories.rgt 
					 * for sucess_ad making less calls to the server
					 */
					$scope.categoryObject= $scope.listRootCategories[$scope.listRootCategories.length-1] ;
				});
	}
	
	/*
	 * get the images from amazon s3 storage, this function is to update product images
	 */
	function getImageFromAmazon(productIndex, imageIndex, product){
		for (var i = 0; i < product.images.length; i++) {
			if( product.images[i].imageIndex== 0){
				var tempZero= product.images[i].imageName;
				$scope.promiseExistingImageObjectZero = $http.get($scope.S3_URL + product.productId + '/' + product.images[i].imageName , {responseType: "arraybuffer"}).
				success(function(data) {
					$timeout(function() {
						var  blob = new Blob( [ data ], { type: "image/jpeg" } );
						blob.name = tempZero;
						$scope.existingImageObjectZero.addFile(blob);
					}, 0);

				}).
				error(function(data, status) {
					$scope.postAdError=true;
					$scope.postAdErrorMessage='Error in uploading images';
					$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
				});
			}
			else if( product.images[i].imageIndex==1 ){
				var tempOne= product.images[i].imageName;
				$scope.promiseExistingImageObjectOne = $http.get($scope.S3_URL + product.productId + '/' + product.images[i].imageName , {responseType: "arraybuffer"}).
				success(function(data) {
					$timeout(function() {
						var  blob = new Blob( [ data ], { type: "image/jpeg" } );
						blob.name = tempOne;
						$scope.existingImageObjectOne.addFile(blob);
					}, 0);

				}).
				error(function(data, status) {
					$scope.postAdError=true;
					$scope.postAdErrorMessage='Error in uploading images';
					$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
				});
			}

			else if( product.images[i].imageIndex== 2){
				var tempTwo= product.images[i].imageName;
				$scope.promiseExistingImageObjectTwo = $http.get($scope.S3_URL + product.productId + '/' + product.images[i].imageName , {responseType: "arraybuffer"}).
				success(function(data) {
					$timeout(function() {
						var  blob = new Blob( [ data ], { type: "image/jpeg" } );
						blob.name = tempTwo;
						$scope.existingImageObjectTwo.addFile(blob);
					}, 0);

				}).
				error(function(data, status) {
					$scope.postAdError=true;
					$scope.postAdErrorMessage='Error in uploading images';
					$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
				});
			}
			else if( product.images[i].imageIndex== 3){
				var tempThree= product.images[i].imageName;
				$scope.promiseExistingImageObjectThree = $http.get($scope.S3_URL + product.productId + '/' + product.images[i].imageName , {responseType: "arraybuffer"}).
				success(function(data) {
					$timeout(function() {
						var  blob = new Blob( [ data ], { type: "image/jpeg" } );
						blob.name = tempThree;
						$scope.existingImageObjectThree.addFile(blob);
					}, 0);

				}).
				error(function(data, status) {
					$scope.postAdError=true;
					$scope.postAdErrorMessage='Error in uploading images';
					$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
				});
			}
			else if( product.images[i].imageIndex== 4){
				var tempFour= product.images[i].imageName;
				$scope.promiseExistingImageObjectFour = $http.get($scope.S3_URL + product.productId + '/' + product.images[i].imageName , {responseType: "arraybuffer"}).
				success(function(data) {
					$timeout(function() {
						var  blob = new Blob( [ data ], { type: "image/jpeg" } );
						blob.name = tempFour;
						$scope.existingImageObjectFour.addFile(blob);
					}, 0);

				}).
				error(function(data, status) {
					$scope.postAdError=true;
					$scope.postAdErrorMessage='Error in uploading images';
					$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
				});
			}
			else if( product.images[i].imageIndex== 5){
				var tempFive= product.images[i].imageName;
				$scope.promiseExistingImageObjectFive = $http.get($scope.S3_URL + product.productId + '/' + product.images[i].imageName , {responseType: "arraybuffer"}).
				success(function(data) {
					$timeout(function() {
						var  blob = new Blob( [ data ], { type: "image/jpeg" } );
						blob.name = tempFive;
						$scope.existingImageObjectFive.addFile(blob);
					}, 0);

				}).
				error(function(data, status) {
					$scope.postAdError=true;
					$scope.postAdErrorMessage='Error in uploading images';
					$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
				});
			}
			else if( product.images[i].imageIndex== 6){
				var tempSix= product.images[i].imageName;
				$scope.promiseExistingImageObjectSix = $http.get($scope.S3_URL + product.productId + '/' + product.images[i].imageName , {responseType: "arraybuffer"}).
				success(function(data) {
					$timeout(function() {
						var  blob = new Blob( [ data ], { type: "image/jpeg" } );
						blob.name = tempSix;
						$scope.existingImageObjectSix.addFile(blob);
					}, 0);

				}).
				error(function(data, status) {
					$scope.postAdError=true;
					$scope.postAdErrorMessage='Error in uploading images';
					$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  
				});
			}
		}

	}
	//get the product to be updated, this method is for updating only...
	function findProductByProductId(productId){
		productFactory.findProductWithoutUserByProductId($stateParams.productId).success(
				function(data) {
					$scope.ad= data;
					locationIdObject={locationId: $scope.ad.location.locationId};
					for(var i=0; i < $scope.ad.images.length; i++){
						getImageFromAmazon(i, $scope.ad.images[i].imageIndex, $scope.ad);
					}
				});
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
