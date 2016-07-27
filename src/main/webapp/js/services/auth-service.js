(function () {
    'use strict';

    angular.module('smartSearchApp.services')
        .service('AuthenticationService', ['$q', 'CONSTANTS', '$base64', '$http', '$cookieStore', '$rootScope',
        function ($q, CONSTANTS, $base64, $http, $cookieStore, $rootScope) {

            this.login = function (username, password) {
                var deferred = $q.defer();

                var data = {
                    username: encodeURIComponent(username),
                    password: encodeURIComponent(password)
                };

                $http.post(
                    '/rest/auth/login', data)
                    .success(function (response) {
                        deferred.resolve(response);
                    })
                    .error(function (response) {
                        deferred.reject(response.data);
                    });

                return deferred.promise;
            };

            this.setCredentials = function (username, password) {
                var authdata = $base64.encode(username + ':' + password);

                $rootScope.globals = {
                    currentUser: {
                        username: username,
                        authdata: authdata
                    }
                };

                $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata;
                $cookieStore.put('globals', $rootScope.globals);
            };

            this.clearCredentials = function () {
                $rootScope.globals = {};
                $cookieStore.remove('globals');
                $http.defaults.headers.common.Authorization = 'Basic ';
            };
        }]);
})();