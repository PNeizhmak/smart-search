(function () {
    'use strict';

    var module = angular.module('smartSearchApp.providers', ['smartSearchApp.constants']);
    module.service('SocialDataProviderFactory', ['$q', '$http', 'CONSTANTS', function ($q, $http, CONSTANTS) {

        function SocialDataProvider() {
            this.search = function (sessionInfo, platform, apiMethod, value, params) {
                var deferred = $q.defer();
                params = params || '';

                $http({
                    method: 'GET',
                    url: this.getServiceUrl(sessionInfo, platform, apiMethod, value, params)
                }).then(function (data) {
                    deferred.resolve(data.data.response);
                }, function (data) {
                    deferred.reject(data);
                });

                return deferred.promise;
            };
            this.getServiceUrl = function (sessionInfo, platform, apiMethod, value, params) {
                return CONSTANTS.BASE_URL + platform + "/" + apiMethod + "/" + value + params;
            };
            this.buildContacts = function (data) {
                return data;
            };
        };

        function VkSocialDataProvider() {
            this.search = function (sessionInfo, platform, apiMethod, value, params) {
                var deferred = $q.defer();
                $http({
                    method: 'GET',
                    url: this.getServiceUrl(sessionInfo, platform, apiMethod, value, params)
                }).then(function (data) {
                    deferred.resolve(data.data.response);
                }, function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };
            this.getServiceUrl = function (sessionInfo, platform, apiMethod, value, params) {
                if (apiMethod == CONSTANTS.SEARCH_METHODS.BY_ID) {
                    return CONSTANTS.BASE_URL + CONSTANTS.PLATFORMS.VK.id + "/" + apiMethod + "/" + value;
                } else if (apiMethod == CONSTANTS.SEARCH_METHODS.BY_NAME) {
                    return CONSTANTS.BASE_URL + CONSTANTS.PLATFORMS.VK.id + "/" + apiMethod + "/" + value + "?token=" + sessionInfo.sid;
                }
            };
            this.buildContacts = function (data) {
                var contacts = [];
                data.forEach(function (item) {
                    if (item.uid) {
                        contacts.push({id: item.uid, firstName: item.first_name, lastName: item.last_name, photo: item.photo, screenName: item.screen_name});
                    }
                });
                return contacts;
            };
        };

        function FbSocialDataProvider() {
            this.search = function (sessionInfo, platform, apiMethod, value, params) {
                var deferred = $q.defer();
                $http({
                    method: 'GET',
                    url: this.getServiceUrl(sessionInfo, platform, apiMethod, value, params)
                }).then(function (data) {
                    deferred.resolve(data.data);
                }, function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };
            this.getServiceUrl = function (sessionInfo, platform, apiMethod, value, params) {
                return CONSTANTS.BASE_URL + CONSTANTS.PLATFORMS.FB.id + "/" + apiMethod + "/" + value + "?token=" + sessionInfo.accessToken;
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

        function GoogleSocialDataProvider() {
            this.search = function (sessionInfo, platform, apiMethod, value, params) {
                var deferred = $q.defer();
                params = params || '';

                $http({
                    method: 'GET',
                    url: this.getServiceUrl(sessionInfo, platform, apiMethod, value, params)
                }).then(function (data) {
                    deferred.resolve(data.data);
                }, function (data) {
                    deferred.reject(data);
                });

                return deferred.promise;
            };
            this.getServiceUrl = function (sessionInfo, platform, apiMethod, value, params) {
                return CONSTANTS.BASE_URL + platform + "/" + apiMethod + "/" + value + params;
            };
            this.buildContacts = function (data) {
                var contacts = [];
                data.items.forEach(function (item) {
                    contacts.push({id: item.id, firstName: item.displayName, photo: item.image.url});
                });
                return contacts;
            };
        };

        function FSQRSocialDataProvider() {
            this.search = function (sessionInfo, platform, apiMethod, value, params) {
                var deferred = $q.defer();
                params = params || '';

                $http({
                    method: 'GET',
                    url: this.getServiceUrl(sessionInfo, platform, apiMethod, value, params)
                }).then(function (data) {
                    deferred.resolve(data.data.response);
                }, function (data) {
                    deferred.reject(data);
                });

                return deferred.promise;
            };
            this.getServiceUrl = function (sessionInfo, platform, apiMethod, value, params) {
                return CONSTANTS.BASE_URL + platform + "/" + apiMethod + "/" + value + params;
            };
            this.buildContacts = function (data) {
                var contacts = [];
                data.results.forEach(function (item) {
                    contacts.push({id: item.id, firstName: item.firstName, lastName: item.lastName});
                });
                return contacts;
            };
        };

        function TwitterSocialDataProvider() {
            this.search = function (sessionInfo, platform, apiMethod, value, nickname) {
                var deferred = $q.defer();
                nickname = nickname || '';

                $http({
                    method: 'GET',
                    url: this.getServiceUrl(sessionInfo, platform, apiMethod, value, nickname)
                }).then(function (data) {
                    deferred.resolve(data.data);
                }, function (data) {
                    deferred.reject(data);
                });

                return deferred.promise;
            };
            this.getServiceUrl = function (sessionInfo, platform, apiMethod, value, nickname) {
                var url;
                if (nickname == '') {
                    url = CONSTANTS.BASE_URL + platform + "/" + apiMethod + "/?name=" + value;
                } else {
                   url = CONSTANTS.BASE_URL + platform + "/" + apiMethod + "/?id=" + value + "&nickname=" + nickname;
                }

                return url;
            };
            this.buildContacts = function (data) {
                var contacts = [];
                data.forEach(function (item) {
                    contacts.push({id: item.id, firstName: item.name, photo: item.profile_image_url_https, screenName: item.screen_name});
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
                case CONSTANTS.PLATFORMS.GOOGLE_PLUS.id:
                    return new GoogleSocialDataProvider();
                case CONSTANTS.PLATFORMS.FORSQUARE.id:
                    return new FSQRSocialDataProvider();
                case CONSTANTS.PLATFORMS.TWITTER.id:
                    return new TwitterSocialDataProvider();
                default:
                    return new SocialDataProvider();
            }
        };

    }]);
})();