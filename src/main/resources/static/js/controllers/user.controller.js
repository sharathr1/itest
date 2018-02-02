// Invoke 'strict' JavaScript mode
'use strict';

angular.module('user.controller', []).
 controller('userController', function($scope, userService) {
	$scope.userInfo = null;
	
	$scope.logout = function() {
		userService.logout().success(function(){});		
	};
	 
 	userService.getUserInfo().then(function(data) {
		$scope.userInfo = data.data;				
	});	 	
 });