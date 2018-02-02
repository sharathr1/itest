"use strict";

angular.module("app", []).controller("home", function($http, $location) {
	var self = this;
	$http.get("/user").success(function(data) {
		self.userInfo = data;				
	}).error(function() {
		self.userInfo = "";
	});
	
	self.logout = function() {
      $http.post('logout', {}).success(function() {  

      }).error(function(data) {
        console.log("Logout failed")		        
      });
    };
});