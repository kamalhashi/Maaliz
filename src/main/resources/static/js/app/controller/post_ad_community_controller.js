var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('PostAdCommunityController', function($scope, $state, auth,categoryFactory,
		categoryRange, $stateParams, mapFactory, fileUploaderFactory, productFactory,
		$window, $translate, categoryRange,flowFactory, bucketName,
		sellerTypeEnglish, sellerTypeSomali, $http,$timeout,conditionSomali, conditionEnglish,
		ageEnglish, ageSomali) {


	//bucket name variable to show the images products in featured listing
	$scope.bucketName= bucketName;
	
	
	$scope.postAdErrorMessage= null;
	$scope.postAdError= false;
	$scope.listRootCategories = [];
	/*get the last object of root categories, which means this category 
	 *object we dealing with.
	 */
	$scope.categoryObject=[];
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
		/*******************Shared information***************************/
		fileUploaderFactory.initializeFileUploader();

		$scope.categoryId= $stateParams.categoryId;

		//showing root category names of a categoryId.
		getRootCategoriesByCategoryId($scope.categoryId);
		//get the registered user phone and 
		$scope.telephone=auth.authenticatedTelephone;

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
			$scope.ad= {type: 'Product', productPriority: 0, productPrice:0, imageStatus: 'IMAGE_PENDING'};
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
		else{
			//after saving go to the top of the page
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop); 
			//save the products and process to success page 
			$scope.location= $scope.ad.location;
			$scope.ad.productLanguage= $translate.proposedLanguage() || $translate.use();
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
		else{
			//after saving go to the top of the page
			$window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop); 
			//save the products and process to success page 				
			angular.extend($scope.ad.location, locationIdObject , $scope.ad.location);
			$scope.ad.productLanguage= $translate.proposedLanguage() || $translate.use();
			$scope.myPromise =  productFactory.updateProduct(fileUploaderFactory.getFiles(), $scope.ad, auth.authenticatedUserId, $scope.telephone)
			.success( function(result) {
				auth.authenticatedTelephone=$scope.telephone;
				$state.go("success_ad" , {productId: result, categoryId: $scope.categoryId, 
					categoryLft: $scope.categoryObject.lft, categoryRgt:$scope.categoryObject.rgt});
			});
		}
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
					$scope.ad.productPrice=0;
					locationIdObject={locationId: $scope.ad.location.locationId};
					for(var i=0; i < $scope.ad.images.length; i++){
						getImageFromAmazon(i, $scope.ad.images[i].imageIndex, $scope.ad);
					}
				});
	}
	//get root categories of a category. to show the user 
	function getRootCategoriesByCategoryId(categoryId) {
		categoryFactory.getRootCategoriesByCategoryId(categoryId).success(
				function(data) {
					$scope.listRootCategories= data;
					/*we need the last object of category, because we need categories.lft categories.rgt 
					 * for sucess_ad making less calls to the server
					 */
					$scope.categoryObject= $scope.listRootCategories[$scope.listRootCategories.length-1];
	
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

