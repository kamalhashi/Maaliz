var hashiApp = angular.module('hashiApp') // gets it

hashiApp.factory('mapFactory', function(GeoCoder) {


	var location = {};
    location.init = function(){
    	return location = {latitude:5.1212591,longtitude:41.6289209, city: 'Muqdisho'
    		, country: 'Somalia', region: 'Banaadir', routeName: 'Jidka Makkah Almukarramah'};
   
    };
	location.addMarker = function(event) {

		var loc = event.latLng;
		location.latitude =loc.lat();
		location.longtitude= loc.lng();

		GeoCoder.geocode({location: loc}).then(function(results) {

			console.log('the Tower');
			for (var i = 0; i < results[0].address_components.length; i++) {
				if (results[0].address_components[i].types[0] == "route")
					location.routeName = results[0].address_components[i].long_name;
				if (results[0].address_components[i].types[0] == "locality")
					location.city= results[0].address_components[i].long_name;
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