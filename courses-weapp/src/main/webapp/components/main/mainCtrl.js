'use strict';


angular.module('coursesApp.controller').controller('MainCtrl', ['$rootScope','$state',
    function ($rootScope, $state) {

        $state.go("main.login");
        $rootScope.$on('$stateChangeSuccess',
            function(event, toState, toParams, fromState, fromParams){
                console.log('from state=' + fromState.name + ' to state=' + toState.name)
            })

    }]);