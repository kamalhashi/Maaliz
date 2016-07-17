var hashiApp = angular.module('hashiApp') // gets it

hashiApp.controller('LanguageController', function ($scope, $translate, $state) {
	  $scope.switchLanguage = function (key) {
	    $translate.use(key).then(function() {
	    	$state.reload();
	    });
	    
	    //language has changed, therefore if its arabic add bootstrap-rtl	  
		var currentLang = $translate.proposedLanguage() || $translate.use();
	    if( currentLang == 'so_SO'){
	            angular.element('head').append('<link id="bootstrap-rtl" href="css/bootstrap-rtl.min.css" rel="stylesheet">');
		}
	    else{
	    	var stylesheet = document.getElementById('bootstrap-rtl');
	    	if(stylesheet){
	    	   stylesheet.parentNode.removeChild(stylesheet);
	    	}
	    }
	    
	  };
	  
	  $scope.getCurrentLanguage = function () {
		  return $translate.use();
	  };
	  
});