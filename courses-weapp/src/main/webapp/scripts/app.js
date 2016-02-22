'use strict';

angular.module('coursesApp.controller', []);

angular.module('coursesApp', [
    'ui.router',
    'coursesApp.controller'
])

.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('main', {
            url: '/',
            templateUrl: 'main.html'
        })
        .state('main.login', {
            templateUrl: 'login.html'
        })

}]);
