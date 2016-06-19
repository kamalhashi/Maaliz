var hashiApp = angular.module('hashiApp') // gets it

hashiApp
.factory(
		'auth',
		function($rootScope, $http, $state, $facebook, $q) {
			var urlBase = 'http://maaliz-djibouti-rest.cfapps.io/user';

			var auth = {

					authenticated : false,
					authenticatedFirstname : null,
					authenticatedTelephone: null,
					authenticatedEmail: null,
					authenticatedUserId : null,
					loginPath : 'login',
					logoutPath : 'logout',
					homePath : '/',
					registerPath : 'register',
					listingPath: 'listing/*',
					initializePath: true,

					authenticate : function(credentials, callback) {
					    var def = $q.defer();
						var headers = credentials ? {authorization : "Basic "
					        + btoa(credentials.username + ":" + credentials.password)
					    } : {};

						$http
						.get(urlBase + '/user',  {headers : headers})
						.success(
								function(data) {
									if (data.name) {
										auth.authenticated = true;
										auth.authenticatedFirstname = data.principal.firstname;
										auth.authenticatedEmail = data.principal.username;
										auth.authenticatedUserId = data.principal.userId;
										auth.authenticatedTelephone= data.principal.telephone;
										$state.go(auth.path == auth.loginPath ? auth.homePath : auth.path);
										def.resolve(data);
									} else {
										auth.authenticated = false;
									}
									callback && callback(auth.authenticated);
									def.resolve(data);
								}).error(function() {
									auth.authenticated = false;
									callback && callback(false);
				                    def.reject("Failed to authenticate");
								});
			            return def.promise;
					},
					
					socialAuthenticate : function(callback){
					  var def = $q.defer();
					  $facebook.getLoginStatus().then(
						      function(response){
						    	  if ( response.status === 'connected' ) { 
						    		     $http.get(urlBase + '/loginSocial/' + 
												$facebook.getAuthResponse().accessToken )
												.success(function(data, status, headers, config) {
										            // this callback will be called asynchronously
										            // when the response is available
											auth.authenticated = true;
											auth.authenticatedFirstname = data.firstname;
											auth.authenticatedEmail = data.username;
											auth.authenticatedUserId = data.userId;
											auth.authenticatedTelephone= data.telephone;
											$state.go(auth.path == auth.loginPath ? auth.homePath : auth.path);
											callback && callback(auth.authenticated);
											def.resolve(data);
										})
										
						    	  }else{
						    		  $facebook.login().then(function() {
									    $facebook.api("/me").then( 
									      function(response) {
									    	  $http.get(urlBase + '/loginSocial/' + 
														$facebook.getAuthResponse().accessToken )
														.success(function(data, status, headers, config) {
												            // this callback will be called asynchronously
												            // when the response is available
													auth.authenticated = true;
													auth.authenticatedFirstname = data.firstname;
													auth.authenticatedEmail = data.username;
													auth.authenticatedUserId = data.userId;
													auth.authenticatedTelephone= data.telephone;
													$state.go(auth.path == auth.loginPath ? auth.homePath : auth.path);
													callback && callback(auth.authenticated);
													def.resolve(data);
												})
									    	  
									      }, function(err){
								    		  console.log('error cancel button');
								    		  def.reject("Cancel button pressed");
									      })
						    		  }
						    		  , function(err){
							    		  console.log('error facebook login');
							    		  def.reject("error Facebook Login");

								      })
						    	  }
						    		
						      }, function(err){
					    		  console.log('error');
						      }
					  );
					  return def.promise;					  
					},
					  
					clear : function() {
						auth.authenticated = false;
						auth.initializePath=false;
						auth.path=auth.loginPath;
						$http.jsonp('http://maaliz-djibouti-rest.cfapps.io/' + auth.logoutPath);
						$state.go(auth.homePath);
					},

					init : function(homePath, loginPath, logoutPath) {
						auth.homePath = homePath;
						auth.loginPath = loginPath;
						auth.logoutPath = logoutPath;
						auth.path= loginPath;
						
						

						$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
							if (auth.initializePath === true){
								auth.initializePath = false;
								if(!auth.authenticated){
									event.preventDefault();
									var deferred = $q.defer();
									 $http.get(urlBase + '/user').success(function (data) {
		                            	   if (data.name) {
												auth.authenticated = true;
												auth.authenticatedFirstname = data.principal.firstname;
												auth.authenticatedEmail = data.principal.username;
												auth.authenticatedUserId = data.principal.userId;
												auth.authenticatedTelephone= data.principal.telephone;
												$state.go(toState.name, toParams);
												deferred.resolve(data);
		                                   } else {
												auth.authenticated = false;
												if (toState.name === 'post_ad' || toState.name === 'applicants'
													|| toState.name === 'admin'){
													auth.path=toState.name;
													if(!auth.authenticated){
														deferred.resolve(data);
														$state.go('login');

													}
													else{ //first load auth.checkPath==true
														auth.path=toState.name;
														deferred.resolve(data);
														$state.go(toState.name, toParams);

													}
												}else{ //first load auth.checkPath==true
													deferred.resolve(data);
													$state.go(toState.name, toParams);

												}
		                                   }
		                               });
									 return deferred.promise;
								}
						 }	
							
							if (toState.name === 'post_ad' || toState.name === 'applicants'
								|| toState.name === 'admin'){
								if(!auth.authenticated){
									event.preventDefault();
									auth.path=toState.name;
									$state.go('login');

								}
							}

		        })
			 }
		   }
		  return auth;

		});