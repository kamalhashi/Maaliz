var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('mapFactory', function(GeoCoder) {


	var location = {};
    location.init = function(){
    	return location = {latitude: 25.19700999264123,longtitude: 55.27444839477539, city: 'Business Bay'
    		, country: 'United Arab Emirates', region: 'Dubai', routeName: 'Sheikh Mohammed bin Rashid Boulevard'};
   
    };
	location.addMarker = function(event) {

		var loc = event.latLng;
		location={};
		location.latitude =loc.lat();
		location.longtitude= loc.lng();

		GeoCoder.geocode({location: loc}).then(function(results) {

			for (var i = 0; i < results[0].address_components.length; i++) {
				if (results[0].address_components[i].types[0] == "route")
					location.routeName = results[0].address_components[i].long_name;
				if (results[0].address_components[i].types[2] == "sublocality_level_1"){
					location.city= results[0].address_components[i].long_name;
				}
				if (results[0].address_components[i].types[0] == "administrative_area_level_1")
					location.region= results[0].address_components[i].long_name;
				if (results[0].address_components[i].types[0] == "country")
					location.country= results[0].address_components[i].long_name;

			}
		});
		return location;
	};

	return location;
});