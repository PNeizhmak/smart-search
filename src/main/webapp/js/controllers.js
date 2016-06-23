(function() {
    'use strict';
    var module = angular.module('smartSearchApp.controllers', ['smartSearchApp.services', 'smartSearchApp.constants']);
    module.controller('SocialController', ['$rootScope', '$scope', '$window', 'SocialService', 'CONSTANTS',
        function ($rootScope, $scope, $window, SocialService, CONSTANTS) {

        $rootScope.sessions = $rootScope.sessions || {};
        $scope.result = '';
        $scope.searchParam = 'getUserInfo';
        $scope.searchValue= '';
        $scope.platforms = CONSTANTS.PLATFORMS;
        $scope.platform = CONSTANTS.PLATFORMS.VK;

        $scope.submit = function($event) {
            $scope.contacts = null;

            var params;
            if ($scope.platform.id == CONSTANTS.PLATFORMS.TWITTER.id) {
                if ($scope.searchValueNickname != undefined) {
                    var twitterExtraParam1 = "'nickname':'" + nicknameValue + "'";
                    var twitterExtraParam2 =  "'anotherOneParam':'parse_more_than_one_param'";
                    params = ";params=" + twitterExtraParam1 + ";params=" + twitterExtraParam2;
                }
            }

            if (!$scope.authorize($scope.platform.id)) {
                return;
            } else {
                $scope.performSearch(params);
            }


        };

        $scope.performSearch = function (params) {
            if ($scope.platform.id == CONSTANTS.PLATFORMS.VK.id) {
                if ($scope.searchParam == CONSTANTS.SEARCH_METHODS.BY_ID) {
                    VK.Api.call('users.get', {
                        user_ids: $scope.searchValue,
                        'fields': 'city,contacts,site,education,status,connections',
                        'name_case': 'Nom'
                    }, function (r) {
                        $scope.contacts = SocialService.buildContacts($scope.platform.id, r.response);
                        $scope.$apply();
                    });
                } else if ($scope.searchParam == CONSTANTS.SEARCH_METHODS.BY_NAME) {
                    VK.Api.call('users.search', {'q': $scope.searchValue}, function (r) {
                        $scope.contacts = SocialService.buildContacts($scope.platform.id, r.response);
                        $scope.$apply();
                    });
                }
            } else if ($scope.platform.id == CONSTANTS.PLATFORMS.FB.id) {
                if ($scope.searchParam == CONSTANTS.SEARCH_METHODS.BY_ID) {
                    FB.api($scope.searchValue, {
                        'fields': 'id,name,picture'
                    }, function (r) {
                        $scope.contacts = [{id: r.id, firstName: r.name}];
                        $scope.$apply();
                    });
                } else if ($scope.searchParam == CONSTANTS.SEARCH_METHODS.BY_NAME) {
                    FB.api('/search', {'q': $scope.searchValue, 'type': 'user'}, function (r) {
                        $scope.contacts = SocialService.buildContacts($scope.platform.id, r.data);
                        $scope.$apply();
                    });
                }
            } else {
                SocialService.search(null, $scope.platform.id, $scope.searchParam, $scope.searchValue, params)
                    .then(function (data) {
                        $scope.contacts = SocialService.buildContacts($scope.platform.id, data);
                    }, function (data) {
                        console.log(data);
                    });
            }
        };

        $scope.authorize = function (platformId) {
            if (!$rootScope.sessions || !$rootScope.sessions[platformId]) {
                if (platformId == CONSTANTS.PLATFORMS.VK.id) {
                    if (!window.confirm('You are not logged in to VK. Log in?')) {
                        return false;
                    } else {
                        VK.Auth.login(function(response) {
                            if (response.session) {
                                $rootScope.sessions[CONSTANTS.PLATFORMS.VK.id] = response.session;
                                if (response.settings) {

                                }
                                $scope.performSearch();
                            } else {

                            }
                        });
                    }
                } else if (platformId == CONSTANTS.PLATFORMS.FB.id) {
                    if (!window.confirm('You are not logged in to Facebook. Log in?')) {
                        return false;
                    } else {
                        FB.login(function(response) {
                            if (response.authResponse) {
                                $rootScope.sessions[CONSTANTS.PLATFORMS.FB.id] = response.authResponse;
                                // FB.api('/me', function(response) {
                                //     console.log('Good to see you, ' + response.name + '.');
                                // });
                                $scope.performSearch();
                            } else {
                                console.log('User cancelled login or did not fully authorize.');
                            }
                        }, {scope: 'email,user_likes'});
                    }
                } else {
                    return true;
                }
            } else {
                return true;
            }
        };

        $scope.getToken = function ($event) {
            $event.preventDefault();
            VK.Auth.login(function(response) {
                if (response.session) {
                    if (response.settings) {

                    }
                } else {

                }
            });
        };

    }]);

    module.controller('ContactDetailsController', ['$scope', '$location', 'SocialService', 'CONSTANTS',
        function ($scope, $location, SocialService, CONSTANTS) {
            $scope.contactId = $location.search().contactId;
        }]);

})();