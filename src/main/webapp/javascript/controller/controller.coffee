define ['angular'], ->
  'use strict'
  angular.module('controllers', [])

#define ['controller'], ->
#  angular.module('controllers').controller 'Controller', ($scope) ->
#    $scope.name = 'baby'
#
#    $scope.awesomeThings = [
#      'HTML5 Boilerplate',
#      'AngularJS!!!',
#      'RequireJS',
#      'Foundation',
#      'Karma'
#    ]
#
#  angular.module('controller').controller 'SignupController', ($scope) ->
#    $scope.create = (user) ->
#      console.log user
#
#    $scope.user =
#      email: "example@hello.com"
#      password: "123456"
#
#  angular.module('controller').controller 'SigninController', ($scope) ->
#    $scope.post = (user) ->
#      console.log user