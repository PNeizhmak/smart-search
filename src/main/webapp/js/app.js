(function() {
    'use strict';
    var app = angular.module('smartSearchApp',
        ['smartSearchApp.controllers', 'smartSearchApp.services', 'smartSearchApp.constants', 'ngRoute']);
    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'templates/pages/main.html',
                controller: 'SocialController'
            })
            .when('/details/:contactId', {
                templateUrl: 'templates/pages/contactDetails.html',
                controller: 'ContactDetailsController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);
})();