'use strict'

var demoApp = angular.module('nasa', [ 'ui.bootstrap', 'nasa.controllers', 'nasa.services' ]);
demoApp.constant("CONSTANTS", {
	getRequiredEarthDatesUrl : "/requiredearthdates",
	getNasaPhotos : "/nasathingy/photos"
});