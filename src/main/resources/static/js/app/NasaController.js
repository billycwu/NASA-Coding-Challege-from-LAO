'use strict'

var module = angular.module('nasa.controllers', []);
module.controller("NasaController", [ "$scope", "NasaService",
		function($scope, UserService) {

			$scope.userDto = {
				userId : null,
				userName : null,
				skillDtos : []
			};
			$scope.skills = [];
			
			NasaService.getUserById(1).then(function(value) {
				console.log(value.data);
			}, function(reason) {
				console.log("error occured");
			}, function(value) {
				console.log("no callback");
			});

		} 
]);