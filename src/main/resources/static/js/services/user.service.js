// Invoke 'strict' JavaScript mode
'use strict';

angular.module('user.service', []).
  factory('userService', function($http) {

    var userAPI = {};

    userAPI.getUserInfo = function() {
      return $http({method: 'GET', url: '/user'});
    }

    return userAPI;
  });