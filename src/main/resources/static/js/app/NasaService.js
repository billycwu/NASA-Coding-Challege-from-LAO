'use strict'

angular.module('nasa.services', []).factory('NasaService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
			service.getNasaPhotos = function(earthDates) {
				var url = CONSTANTS.getNasaPhotos + "?earthdate="+earthDates;
				return $http.get(url);
			}
			return service;
		} ]);