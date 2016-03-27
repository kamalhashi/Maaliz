hashiApp.directive('emailAvailable', function($timeout, $q, $http) {
	return {
		restrict : 'AE',
		require : 'ngModel',
		link : function(scope, elm, attr, model) {
			model.$asyncValidators.usernameExists = function(email) {
				return $http.get('http://xaashi-rest.cfapps.io/user/email/' + email).then(function(res) {
					+$timeout(function() {
						model.$setValidity('emailExists', !!res.data);
					}, 1000);
				});

			};
		}
	}
});

hashiApp.directive('compareTo', [ function() {
	return {
		require : "ngModel",
		scope : {
			otherModelValue : "=compareTo"
		},
		link : function(scope, element, attributes, ngModel) {
			ngModel.$validators.compareTo = function(modelValue) {
				return modelValue == scope.otherModelValue;
			};

			scope.$watch("otherModelValue", function() {
				ngModel.$validate();
			});
		}
	};
}]);



hashiApp.directive('flowImageResize', function($q) {
    return {
        'require': 'flowInit',
        'link': function(scope, element, attrs) {
        	var resize=false;        	
            scope.$flow.opts.preprocess = function (chunk) {
            	if(resize == true){
            		chunk.fileObj.promise.then(function () {
            			if (!chunk.fileObj.resized) {
            				chunk.fileObj.resized = true;
            				chunk.fileObj.retry();
            			}
            		});
            		if (chunk.fileObj.resized) {
            			chunk.preprocessFinished();
            		}
                 }else{
                	 chunk.preprocessFinished();
                 }
            };
        	
            scope.$flow.on('filesSubmitted', function (files) { 
            	resize=false;
                angular.forEach(files, function (file) {
                      if(file.size > 400000){
                    	  resize=true;
                    	  var nativeFile = file.file;// instance of File, same as here: https://github.com/flowjs/ng-flow/blob/master/src/directives/img.js#L13
                    	  file.file = null;// do not display it
                    
                    	  var deferred = $q.defer();
                    	  file.promise = deferred.promise;
                    
                    	  loadImage(
                    			  nativeFile, 
                    			  function (canvas) {
                    				  canvas.toBlob(function (blob) {
                    					  file.file = blob;
                    					  file.size = blob.size;
                    					  deferred.resolve();
                    					  scope.$digest();
                    				  });
                    			  },
                    			  {
                    				  canvas: true,
                    				  crop: true,
                    				  maxWidth: 400,
                    				  maxHeight: 400
                    			  }
                    	  );
                      }//end if file size
                	});
            })
        }
    };
});
          
    
hashiApp.directive('myFlowImg', [function() {
  return {
    'scope': false,
    'require': '^flowInit',
    'link': function(scope, element, attrs) {
      var file = attrs.myFlowImg;
      scope.$watch(file + '.file', function (file) {
        if (!file) {
          return ;
        }
        var fileReader = new FileReader();
        fileReader.readAsDataURL(file);
        fileReader.onload = function (event) {
          scope.$apply(function () {
            attrs.$set('src', event.target.result);
          });
        };
      });
    }
  };
}]);