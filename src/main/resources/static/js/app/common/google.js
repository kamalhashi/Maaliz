var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('mapFactory', function(GeoCoder) {


	var location = {};
    location.init = function(){
    	return location = {latitude: 25.1949849,longtitude: 55.278414099999964,
    	country: 'United Arab Emirates', city: 'Dubai',  neighborhood: 'Downtown Dubai'};
   
    };
    
    location.placeChanged = function(place) {
	    splitAddress(place.address_components);   
		location.neighborhood=  place.name;
		location.country=  'United Arab Emirates';
	    location.latitude =place.geometry.location.lat();
		location.longtitude= place.geometry.location.lng();
		return location;
	}
    
	location.addMarker = function(event) {
		var loc = event.latLng;
		var isNeighborhoodFound= false;
		location={};
		location.latitude =loc.lat();
		location.longtitude= loc.lng();

		GeoCoder.geocode({location: loc}).then(function(results) {
            console.log(results[0]);
			for (var i = 0; i < results[0].address_components.length; i++) {
				if (results[0].address_components[i].types[0] == "route")
					location.routeName = results[0].address_components[i].long_name;
				
				if (results[0].address_components[i].types[0] == "neighborhood"){
					location.neighborhood= results[0].address_components[i].long_name;
					isNeighborhoodFound = true;
					console.log(results[0].address_components[i].long_name);
				}
				if ( (results[0].address_components[i].types[0] == "political" && !isNeighborhoodFound)){
					location.neighborhood=  results[0].address_components[i].long_name;
				}
				if (results[0].address_components[i].types[0] == "administrative_area_level_1")
					location.city= results[0].address_components[i].long_name;
				if (results[0].address_components[i].types[0] == "country")
					location.country= results[0].address_components[i].long_name;

			}
		});
		return location;
	};
	//split the address , autocomplete input returns complete address
	function splitAddress(address){
		location={};
		for (var i = 0; i < address.length; i++) {
			if ( address[i].types[0] == "route")		
				 location.routeName =  address[i].long_name;
		    if ( address[i].types[0] == "administrative_area_level_1")
				location.city=  address[i].long_name;
		}
		return;
	}

	return location;
});