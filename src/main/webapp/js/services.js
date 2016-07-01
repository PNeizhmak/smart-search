(function () {
    'use strict';
    var module = angular.module('smartSearchApp.services', ['smartSearchApp.constants', 'ngCookies']);

        module.service('SocialService', ['$http', '$q', 'CONSTANTS', function ($http, $q, CONSTANTS) {

            this.search = function (userId, platform, apiMethod, value, params) {
                var deferred = $q.defer();
                params = params || '';

                $http({
                    method: 'GET',
                    url: this.getServiceUrl(userId, platform, apiMethod, value, params)
                }).then(function (data) {
                    deferred.resolve(data.data.response);
                }, function (data) {
                    deferred.reject(data);
                });

                return deferred.promise;
            };

            this.searchVK = function (token, apiMethod, value) {
                var deferred = $q.defer();
                $http({
                    method: 'GET',
                    url: this.buildVkURL(token, apiMethod, value)
                }).then(function (data) {
                    deferred.resolve(data.data.response);
                }, function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };

            this.searchFB = function (token, apiMethod, value) {
                var deferred = $q.defer();
                $http({
                    method: 'GET',
                    url: this.buildFbURL(token, apiMethod, value)
                }).then(function (data) {
                    deferred.resolve(data.data);
                }, function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };

            this.buildFbURL = function (token, apiMethod, value) {
                return CONSTANTS.BASE_URL + CONSTANTS.PLATFORMS.FB.id + "/" + apiMethod + "/" + value + "?token=" + token;
            };

            this.buildVkURL = function (token, apiMethod, value) {
                if (apiMethod == CONSTANTS.SEARCH_METHODS.BY_ID) {
                    return CONSTANTS.BASE_URL + CONSTANTS.PLATFORMS.VK.id + "/" + apiMethod + "/" + value;
                } else if (apiMethod == CONSTANTS.SEARCH_METHODS.BY_NAME) {
                    return CONSTANTS.BASE_URL + CONSTANTS.PLATFORMS.VK.id + "/" + apiMethod + "/" + value + "?token=" + token;
                }
            };

            this.getServiceUrl = function (userId, platform, apiMethod, value, params) {
                return CONSTANTS.BASE_URL + platform + "/" + userId + "/" + apiMethod + "/" + value + params;
            };

            this.buildContacts = function (platformId, data) {
                if (platformId == CONSTANTS.PLATFORMS.VK.id) {
                    return this.buildContactsVK(data);
                } else if (platformId == CONSTANTS.PLATFORMS.FB.id) {
                    return this.buildContactsFB(data);
                } else if (platformId == CONSTANTS.PLATFORMS.FORSQUARE.id) {
                    return this.buildContactsFSQR(data);
                } else {
                    return data;
                }
            };

            this.buildContactsFSQR = function (data) {
                var contacts = [];
                data.results.forEach(function (item) {
                    contacts.push({id: item.id, firstName: item.firstName, lastName: item.lastName});
                });
                return contacts;
            };

            this.buildContactsVK = function (data) {
                var contacts = [];
                data.forEach(function (item) {
                    contacts.push({id: item.uid, firstName: item.first_name, lastName: item.last_name});
                });
                return contacts;
            };

            this.buildContactsFB = function (data) {
                if (data.data) {
                    var contacts = [];
                    data.data.forEach(function (item) {
                        contacts.push({id: item.id, firstName: item.name});
                    });
                    return contacts;
                } else {
                    return [{id: data.id, firstName: data.name}];
                }
            }

        }]);

        module.service('AuthenticationService', ['Base64', '$http', '$cookieStore', '$rootScope', '$timeout',
            function (Base64, $http, $cookieStore, $rootScope, $timeout) {

                this.login = function (username, password, callback) {
                    $timeout(function () {
                        var response = {success: username === 'test' && password === 'test'};
                        if (!response.success) {
                            response.message = 'Username or password is incorrect';
                        }
                        callback(response);
                    }, 1000);
                };

                this.setCredentials = function (username, password) {
                    var authdata = Base64.encode(username + ':' + password);

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