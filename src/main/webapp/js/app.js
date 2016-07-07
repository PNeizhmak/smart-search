(function() {
    'use strict';
    var app = angular.module('smartSearchApp',
        ['smartSearchApp.controllers', 'smartSearchApp.services', 'smartSearchApp.constants', 'ngRoute', 'ngCookies']);
    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/login', {
                templateUrl: 'templates/pages/login.html',
                controller: 'LoginController'
            })
            .when('/register', {
                templateUrl: 'templates/pages/register.html',
                controller: 'RegisterController'
            })
            .when('/main', {
                templateUrl: 'templates/pages/main.html',
                controller: 'SocialController'
            })
            .when('/details', {
                templateUrl: 'templates/pages/contactDetails.html',
                controller: 'ContactDetailsController'
            })
            .otherwise({
                redirectTo: '/main'
            });
    }]);

    app.run(['$rootScope', '$location', '$cookieStore', '$http',
        function ($rootScope, $location, $cookieStore, $http) {
            $rootScope.globals = $cookieStore.get('globals') || {};
            if ($rootScope.globals.currentUser) {
                $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
            }

            $rootScope.$on('$locationChangeStart', function (event, next, current) {
                // redirect to login page if not logged in and trying to access a restricted page
                var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
                var loggedIn = $rootScope.globals.currentUser;
                if (restrictedPage && !loggedIn) {
                    $location.path('/login');
                }
            });
        }]);
})();