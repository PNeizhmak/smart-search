(function() {
    'use strict';
    var app = angular.module('smartSearchApp',
        ['smartSearchApp.controllers', 'smartSearchApp.services', 'smartSearchApp.constants', 'ngRoute', 'ngCookies']);
    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'templates/pages/login.html',
                controller: 'LoginController'
            })
            .when('/details', {
                templateUrl: 'templates/pages/contactDetails.html',
                controller: 'ContactDetailsController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);

    app.run(['$rootScope', '$location', '$cookieStore', '$http',
        function ($rootScope, $location, $cookieStore, $http) {
            $rootScope.globals = $cookieStore.get('globals') || {};
            if ($rootScope.globals.currentUser) {
                $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
            }

            $rootScope.$on('$locationChangeStart', function (event, next, current) {
                // redirect to login page if not logged in
                if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                    $location.path('/login');
                }
            });
        }]);
})();