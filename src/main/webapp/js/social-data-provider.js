(function () {
    'use strict';

    var module = angular.module('smartSearchApp.providers', ['smartSearchApp.constants']);
    module.service('SocialDataProviderFactory', ['$q', '$http', 'CONSTANTS', function ($q, $http, CONSTANTS) {

        function SocialDataProvider() {
            this.search = function (userId, token, platform, apiMethod, value, params) {
                var deferred = $q.defer();
                params = params || '';

                $http({
                    method: 'GET',
                    url: this.getServiceUrl(userId, token, platform, apiMethod, value, params)
                }).then(function (data) {
                    deferred.resolve(data.data.response);
                }, function (data) {
                    deferred.reject(data);
                });

                return deferred.promise;
            };
            this.getServiceUrl = function (userId, token, platform, apiMethod, value, params) {
                return CONSTANTS.BASE_URL + platform + "/" + userId + "/" + apiMethod + "/" + value + params;
            };
            this.buildContacts = function (data) {
                return data;
            };
        };

        function VkSocialDataProvider() {
            this.search = function (userId, token, platform, apiMethod, value, params) {
                var deferred = $q.defer();
                $http({
                    method: 'GET',
                    url: this.getServiceUrl(userId, token, platform, apiMethod, value, params)
                }).then(function (data) {
                    deferred.resolve(data.data.response);
                }, function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };
            this.getServiceUrl = function (userId, token, platform, apiMethod, value, params) {
                if (apiMethod == CONSTANTS.SEARCH_METHODS.BY_ID) {
                    return CONSTANTS.BASE_URL + CONSTANTS.PLATFORMS.VK.id + "/" + apiMethod + "/" + value;
                } else if (apiMethod == CONSTANTS.SEARCH_METHODS.BY_NAME) {
                    return CONSTANTS.BASE_URL + CONSTANTS.PLATFORMS.VK.id + "/" + apiMethod + "/" + value + "?token=" + token;
                }
            };
            this.buildContacts = function (data) {
                var contacts = [];
                data.forEach(function (item) {
                    contacts.push({id: item.uid, firstName: item.first_name, lastName: item.last_name, photo: item.photo, screenName: item.screen_name});
                });
                return contacts;
            };
        };

        function FbSocialDataProvider() {
            this.search = function (userId, token, platform, apiMethod, value, params) {
                var deferred = $q.defer();
                $http({
                    method: 'GET',
                    url: this.getServiceUrl(userId, token, platform, apiMethod, value, params)
                }).then(function (data) {
                    deferred.resolve(data.data);
                }, function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };
            this.getServiceUrl = function (userId, token, platform, apiMethod, value, params) {
                return CONSTANTS.BASE_URL + CONSTANTS.PLATFORMS.FB.id + "/" + apiMethod + "/" + value + "?token=" + token;
            };
            this.buildContacts = function (data) {
                if (data.data) {
                    var contacts = [];
                    data.data.forEach(function (item) {
                        contacts.push({id: item.id, firstName: item.name});
                    });
                    return contacts;
                } else {
                    return [{id: data.id, firstName: data.name}];
                }
            };
        };

        function FSQRSocialDataProvider() {
            this.search = function (userId, token, platform, apiMethod, value, params) {
                var deferred = $q.defer();
                params = params || '';

                $http({
                    method: 'GET',
                    url: this.getServiceUrl(userId, token, platform, apiMethod, value, params)
                }).then(function (data) {
                    deferred.resolve(data.data.response);
                }, function (data) {
                    deferred.reject(data);
                });

                return deferred.promise;
            };
            this.getServiceUrl = function (userId, token, platform, apiMethod, value, params) {
                return CONSTANTS.BASE_URL + platform + "/" + userId + "/" + apiMethod + "/" + value + params;
            };
            this.buildContacts = function (data) {
                var contacts = [];
                data.results.forEach(function (item) {
                    contacts.push({id: item.id, firstName: item.firstName, lastName: item.lastName});
                });
                return contacts;
            };
        };

        this.getSocialDataProvider = function (platform) {
            switch (platform) {
                case CONSTANTS.PLATFORMS.VK.id:
                    return new VkSocialDataProvider();
                case CONSTANTS.PLATFORMS.FB.id:
                    return new FbSocialDataProvider();
                case CONSTANTS.PLATFORMS.FB.id:
                    return new FSQRSocialDataProvider();
                default:
                    return new SocialDataProvider();
            }
        };

    }]);
})();