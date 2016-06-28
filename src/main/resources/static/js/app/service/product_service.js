var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('productFactory', function($http, $q, $translate) {

	var urlBase = 'http://maaliz-angola-rest.cfapps.io/products';
	var dataFactory = {};

	//update image status
	dataFactory.updateImageStatusForProduct= function(productId,  imageStatus) {
		return $http.put(urlBase + '/admin/product/' + productId + '/imageStatus/' + imageStatus);
	};
	
	//update productPriority
	dataFactory.updatePriorityForProduct= function(productId,  productPriority) {
		return $http.put(urlBase + '/admin/product/' + productId + '/productPriority/' + productPriority);
	};

	//details
	dataFactory.findProductWithUserByProductId= function(productId) {
		return $http.get(urlBase + '/product/details/' + productId);
	};
    //edit product page
	dataFactory.findProductWithoutUserByProductId= function(productId) {
		return $http.get(urlBase + '/product/edit/' + productId);
	};
	
	dataFactory.findProductsByUserId= function(userId) {
		return $http.get(urlBase + '/user/' + userId);
	};
	//get featured products 
	dataFactory.getFeaturedProducts= function() {
		return $http.get(urlBase + '/featured',{cache: true});
	};
	
	//delete featured products 
	dataFactory.deleteProduct= function(productId) {
		return $http.delete(urlBase + '/'+ productId);
	};

	dataFactory.countProductsByCategoryId= function(categoryId, cityId) {
		return $http.get(urlBase + '/count/' + categoryId + '/' + cityId,{cache: true});
	};
	
	dataFactory.countProductsByDepth= function(categoryId, cityId) {
		return $http.get(urlBase + '/count/byDepth/' + categoryId + '/' + cityId);
	};

	dataFactory.advanceSearch= function(cityId, categoryId, simpleSearchText, pageIndex, sortColumn, sortDirection, searchObject) {
		return $http.post(urlBase + '/advanceSearch', searchObject,  {
			params: {cityId: cityId ,  categoryId: categoryId, searchText: simpleSearchText, pageIndex: pageIndex, 
				sortColumn: sortColumn, sortDirection:sortDirection}
		});
	};

	dataFactory.basicSearch= function(cityId, categoryId, simpleSearchText, pageIndex, sortColumn, sortDirection) {
		return $http.get(urlBase , {
			params: {cityId: cityId ,  categoryId: categoryId, searchText: simpleSearchText, pageIndex: pageIndex, sortColumn: sortColumn, sortDirection:sortDirection }
		});
	};



	//save a product 
	dataFactory.saveProduct = function(files, product, location,  categoryId , userId, telephone, logoImage) {
		if(files.length > 0){
			product.coverPhoto= 0 + '.' + files[0].file.name.split('.').pop();
		}
		//check if the logoImage is not null set the name of the product  
		if(logoImage){
		   if(logoImage.file){
			 product.logoPhoto=  'logo.' + logoImage.file.name.split('.').pop();
		    }else{
		    	product.logoPhoto= null;
		    }
		}else{ // if the logoImage is null then set the name of the product to null
			product.logoPhoto= null;
		}

		return $http({ method: 'POST',
			url: urlBase + '/' + categoryId + '/' + userId,
			params: {telephone: telephone},

			headers: {
				'Content-Type': undefined
			},
			data:  {product: product, files: files, location: location, logoImage: logoImage},

			transformRequest: function (data, headersGetter) {
				var imageNames=[];
				var formData = new FormData();
				formData.append("product", angular.toJson(data.product));
				formData.append("location", angular.toJson(data.location));

				//this is for the logo Image
				if(data.logoImage){
					if(data.logoImage.file.name){
						formData.append("logoImage", data.logoImage.file, 'logo.' + data.logoImage.file.name.split('.').pop());					
					}
				}
				for (var i = 0; i < data.files.length; i++) {
					if(data.files[i].file.name){
						//add each file to the form data and iteratively name them
						formData.append("file", data.files[i].file.file, i + '.' + files[i].file.name.split('.').pop());
						imageNames.push({imageName: i + '.' + files[i].file.name.split('.').pop() , imageIndex: data.files[i].imageIndex});
					}
				}
				formData.append("imageNames", angular.toJson(imageNames));

				return formData;
			}
		});

	};	



	/*update a product
	 * parameters for userId and telephone for user object, when dealing with userObject we should'nt fetch
	 * the object for security reasons, we should only fetch parameters that we need it.
	 */ 
	dataFactory.updateProduct = function(files, product, userId, telephone, logoImage ) {
		if(files.length > 0){
			product.coverPhoto=0 + '.' + files[0].file.name.split('.').pop();
		}
		//check if the logoImage is not null set the name of the product  
		if(logoImage){
		   if(logoImage.file){
			 product.logoPhoto=  'logo.' + logoImage.file.name.split('.').pop();
		    }else{
		    	product.logoPhoto= null;
		    }
		}else{ // if the logoImage is null then set the name of the product to null
			product.logoPhoto= null;
		}
		
		return $http({ method: 'POST',
			url: urlBase + '/update',
			params: {telephone: telephone, userId:userId},
			headers: {
				'Content-Type': undefined
			},
			data:  {product: product, files: files , logoImage: logoImage},

			transformRequest: function (data, headersGetter) {
				var imageNames=[];
				var formData = new FormData();
				var count=0;
				
				//this is for the logo Image
				if(data.logoImage){
					if(data.logoImage.file.name){
						formData.append("logoImage", data.logoImage.file, 'logo.' + data.logoImage.file.name.split('.').pop());					
					}
				}

				for (var i = 0; i < data.files.length; i++) {					
					if(data.files[i].file.name){
						//add each file to the form data and iteratively name them
						formData.append("file", data.files[i].file.file, i + '.' + files[i].file.name.split('.').pop());
						/*the if is to update the object in  database and to create json object, 	
						 * if the image is already in product.images, means existing image then replace it 
						 */						
						if(data.product.images[count]){
							imageNames.push({imageId:  data.product.images[count].imageId, imageName: i + '.' + files[i].file.name.split('.').pop() , imageIndex: data.files[i].imageIndex });
							count++;
						}
						//the image is new so create new entry in json object. to the -> database 
						else{
							imageNames.push({imageName: i + '.' + files[i].file.name.split('.').pop(), imageIndex: data.files[i].imageIndex});
						}
					}


				}
				data.product.images=imageNames;
				formData.append("product", angular.toJson(data.product));
				return formData;
			}
		});

	};	


	return dataFactory;

});