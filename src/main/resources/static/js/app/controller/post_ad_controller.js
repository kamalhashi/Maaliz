var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('PostAdController', function($scope, $location, auth,
		$q , categoryFactory, categoryRange, $http, $state, dispatcherPostAdNextFactory,
		promiseRootCategories) {
	$scope.postAdMessage = "";
	$scope.postAdError = false;
	$scope.listSubCategoriesLevelOne = [];
	/** **For showing and not showing sub-categories*** BOOLEAN Staff*/

	$scope.showSubCategoryLevelOne = false;
	$scope.showSubCategoryLevelTwo = false;
	$scope.showSubCategoryLevelThree = false;

	/**boolean values for checking sub categories**/
	$scope.checkSubCategoriesLevelOne = [];
	$scope.checkSubCategoriesLevelTwo = [];
	$scope.checkSubCategoriesLevelThree = [];


	/**to show the button and other css classes when ther's no sub categories to select**/
	$scope.disableButton=true;

	/**store selected category**/
	$scope.selectedCategory=0;
	$scope.selectedCategoryLft=0;
	$scope.selectedCategoryRgt=0;
	
	/******
	 *  The following variable are need it , to change the href css background color 
	 *  when user selects href, the keep it the background active.
	 */
	$scope.selectedCategoryIdLevelOne=0;
	$scope.selectedCategoryIdLevelTwo=0;
	$scope.selectedCategoryIdLevelThree=0;
	$scope.selectedCategoryIdLevelFour=0;

	//initialize  
	(function init() {
		//load root categories
		$scope.categories= promiseRootCategories.data;
		//remove >> categories
		$scope.categories.splice(0,1);
	})();


	$scope.authenticatedUserId = function() {
		return auth.authenticatedUserId;
	}


	/**to get list of sub categories level one***/
	$scope.getSubCategoriesLevelOne = function(categoryId, categoryLft, categoryRgt) {
		//store the first level of category, for css background change 
		$scope.selectedCategoryIdLevelOne= categoryId;
		
		//reset other sub categories to false
		$scope.showSubCategoryLevelOne = false;
		$scope.showSubCategoryLevelTwo = false;
		$scope.showSubCategoryLevelThree = false;
		//empty list first
		$scope.checkSubCategoriesLevelOne=[];
		$scope.checkSubCategoriesLevelTwo=[];
		$scope.checkSubCategoriesLevelThree=[];
		//empty list sub categories
		$scope.listSubCategoriesLevelOne=[];
		$scope.listSubCategoriesLevelTwo = [];
		$scope.listSubCategoriesLevelThree = [];
		//for storing user selected category info
		$scope.selectedCategory= categoryId;
		$scope.selectedCategoryLft=categoryLft;
		$scope.selectedCategoryRgt=categoryRgt;
		//for showing ticking class 
		$scope.disableButton=true;
		//needed for $q.all to send loop asynchronouse 
		var promiseArray = [];

		$scope.myPromise = categoryFactory.getSubCategoriesByCategoryId(categoryId).success(
				function(categ) {
					if(categ.length > 0){
						categ.splice(0,1);
						$scope.listSubCategoriesLevelOne = categ;
						$scope.showSubCategoryLevelOne = true;
						for(var i=0; i < $scope.listSubCategoriesLevelOne.length; i++){
							promiseArray.push(categoryFactory.hasChildCategories($scope.listSubCategoriesLevelOne[i].categoryId));
						}
						$scope.myPromiseLevelOne =  $q.all(promiseArray)
						// Each element of dataArray corresponds to each result of http request.
						.then( function(dataArray) {
							for(var i=0; i < dataArray.length; i++)	
								$scope.checkSubCategoriesLevelOne.push(dataArray[i].data);			 
						});
					}

				});

	}


	$scope.getSubCategoriesLevelTwo = function(categoryId, categoryLft, categoryRgt) {
		//store the first level of category, for css background change 
		$scope.selectedCategoryIdLevelTwo= categoryId;
		$scope.disableButton=true;
		$scope.listSubCategoriesLevelTwo = [];
		$scope.listSubCategoriesLevelThree = [];
		$scope.checkSubCategoriesLevelTwo=[];
		$scope.checkSubCategoriesLevelThree=[];

		//for storing user selected category info
		$scope.selectedCategory= categoryId;
		$scope.selectedCategoryLft=categoryLft;
		$scope.selectedCategoryRgt=categoryRgt;
		//needed for $q.all to send loop asynchronouse 
		var promiseArray = [];

		$scope.myPromiseLevelTwo = categoryFactory.getSubCategoriesByCategoryId(categoryId).success(
				function(categ) {
					if(categ.length > 0){
						categ.splice(0,1);
						$scope.listSubCategoriesLevelTwo = categ;
						$scope.showSubCategoryLevelTwo = true;
						$scope.showSubCategoryLevelThree = false;
						$scope.disableButton=true;
						for(var i=0; i < $scope.listSubCategoriesLevelTwo.length; i++){
							promiseArray.push(categoryFactory.hasChildCategories($scope.listSubCategoriesLevelTwo[i].categoryId));
						}
						$scope.myPromiseLevelTwo = $q.all(promiseArray)
						// Each element of dataArray corresponds to each result of http request.
						.then( function(dataArray) {
							for(var i=0; i < dataArray.length; i++)	
								$scope.checkSubCategoriesLevelTwo.push(dataArray[i].data);			 
						});	
					}else{
						$scope.disableButton=false;
					}
				});

	}

	$scope.getSubCategoriesLevelThree = function(categoryId, categoryLft, categoryRgt) {		
		//store the first level of category, to keep the selected background active
		$scope.selectedCategoryIdLevelThree= categoryId;
		
		$scope.disableButton=true;
		$scope.listSubCategoriesLevelThree = [];

		//for storing user selected category info
		$scope.selectedCategory= categoryId;
		$scope.selectedCategoryLft=categoryLft;
		$scope.selectedCategoryRgt=categoryRgt;


		var promiseArray = [];

		$scope.myPromiseLevelThree = categoryFactory.getSubCategoriesByCategoryId(categoryId).success(
				function(categ) {
					if(categ.length > 0){	
						categ.splice(0,1);
						$scope.listSubCategoriesLevelThree = categ;
						$scope.showSubCategoryLevelThree = true;
						$scope.disableButton=true;
						for(var i=0; i < $scope.listSubCategoriesLevelThree.length; i++){
							promiseArray.push(categoryFactory.hasChildCategories($scope.listSubCategoriesLevelThree[i].categoryId));
						}
						$scope.myPromiseLevelThree = $q.all(promiseArray)
						// Each element of dataArray corresponds to each result of httpp request.
						.then( function(dataArray) {
							for(var i=0; i < dataArray.length; i++)	
								$scope.checkSubCategoriesLevelThree.push(dataArray[i].data);			 
						});	
					}else{
						$scope.disableButton=false;
					}
				});
	}

	$scope.getSubCategoriesLevelFour = function(categoryId) {
		//store the first level of category, to keep the selected background active
		$scope.selectedCategoryIdLevelFour= categoryId;
		$scope.selectedCategory= categoryId;

		$scope.disableButton=false;		
	}
	//when submit button is pressed
	$scope.onSubmit  = function(){
		//check the selected category is motor
		if($scope.selectedCategory > 0 ){
			forwardToPostAdNext($scope.selectedCategory, $scope.selectedCategoryLft, $scope.selectedCategoryRgt);
		}

	}
	
	function forwardToPostAdNext (categoryId ,categoryLft, categoryRgt){
		dispatcherPostAdNextFactory.forwardToPostAdNext(0, categoryId ,categoryLft, categoryRgt);
	}


});
