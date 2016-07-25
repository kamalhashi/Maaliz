var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('PostAdJobController', function($scope, $state, auth,categoryFactory,
		categoryRange, $stateParams, mapFactory, fileUploaderFactory, productFactory,
		$window, $translate, categoryRange,flowFactory, $http,$timeout, bucketName,
		careerLevelEnglish, careerLevelSomali, employmentTypeEnglish, employmentTypeSomali,
		workExperienceEnglish, workExperienceSomali, educationLevelEnglish, educationLevelSomali,
		monthSalaryEnglish, monthSalarySomali, educationLevelEnglish, educationLevelSomali,
		monthSalaryEnglish, monthSalarySomali, cvRequiredSomali, cvRequiredEnglish,
		listedBySomali, listedByEnglish, companySizeEnglish, companySizeSomali,errorsConstant,
		hideCompanyNameSomali, hideCompanyNameEnglish) {

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
	
	(function init() {
		/*******************Shared information***************************/
		if($translate.use() === 'so_SO'){
			$scope.constantCareerLevel=careerLevelSomali;	 
			$scope.constantEmploymentType= employmentTypeSomali;
			$scope.constantWorkExperience = workExperienceSomali;
			$scope.constantEducationLevel = educationLevelSomali;
			$scope.constantMonthSalary = monthSalarySomali;
			$scope.constantCVRequired = cvRequiredSomali;
			$scope.constantListedBy = listedBySomali;
			$scope.constantCompanySize = companySizeSomali;
			$scope.constantHideCompanyName = hideCompanyNameSomali;
		} 
		else if($translate.use() === 'en_US'){
			$scope.constantCareerLevel= careerLevelEnglish;	 
			$scope.constantEmploymentType= employmentTypeEnglish;
			$scope.constantWorkExperience = workExperienceEnglish;
			$scope.constantEducationLevel = educationLevelEnglish;
			$scope.constantMonthSalary = monthSalaryEnglish;
			$scope.constantCVRequired = cvRequiredEnglish;
			$scope.constantListedBy = listedByEnglish;
			$scope.constantCompanySize = companySizeEnglish;
			$scope.constantHideCompanyName = hideCompanyNameEnglish;
		}

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
			$scope.ad= {type: 'JobEntity', productPriority: 0, productPrice:0};
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
			$scope.myPromise =  productFactory.saveProduct(fileUploaderFactory.getFiles(), $scope.ad,  $scope.location, $stateParams.categoryId,  auth.authenticatedUserId, $scope.telephone, $scope.logoImage)
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

			$scope.myPromise =  productFactory.updateProduct(fileUploaderFactory.getFiles(), $scope.ad, auth.authenticatedUserId, $scope.telephone, $scope.logoImage)
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
	
	function getImageFromAmazon(userId, bucket, fileName){

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
	}
	
	//get the product to be updated, this method is for updating only...
	function findProductByProductId(productId){
		productFactory.findProductWithoutUserByProductId($stateParams.productId).success(
				function(data) {
					$scope.ad= data;
					$scope.ad.productPrice=0;
					locationIdObject={locationId: $scope.ad.location.locationId};
				    
					if(data.logoPhoto)
						getImageFromAmazon(productId, bucketName.productLogo , data.logoPhoto);
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
	
	$scope.errors = [];
	$scope.validate = function (file) {
	  $scope.postAdError=false;
	  $scope.postAdErrorMessage='';
	  if (file.size > 20000) {
		 $scope.postAdError=true;
	     $scope.postAdErrorMessage=errorsConstant.ERROR01;
		 $window.scrollTo(0, angular.element(document.getElementById('div1')).offsetTop);  

	    return false;
	  }
	  return true;
	}
	
	//remove file 
	$scope.removeFile  = function (index){
		$scope.logoImage=null; 
	}

	//file handler method
	$scope.fileUploadedSuccessfully  = function(file)	{
		$scope.logoImage= file;
	}	

	$scope.addMarker = function(event) {
		$scope.ad.location=mapFactory.addMarker(event);
	};
	
	/********Date Picker *****************/
	
	$scope.dateOptions = {
		    startingDay: 2 
	};
	
	$scope.format='dd-MMMM-yyyy',
	
	$scope.popupDatePicker = {
		    opened: false
    };
	
	$scope.openDatePicker = function() {
	    $scope.popupDatePicker.opened = true;
	};


});

