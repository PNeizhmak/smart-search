(function () {
    'use strict';

    angular.module('smartSearchApp.controllers')
        .controller('SocialController', ['$location', '$rootScope', '$scope', '$window', 'SocialService', 'AuthenticationService', 'UserService', 'CONSTANTS',
            function ($location, $rootScope, $scope, $window, SocialService, AuthenticationService, UserService, CONSTANTS) {

                $rootScope.sessions = $rootScope.sessions || {};
                $scope.result = '';
                $scope.searchParam = 'getUserInfo';
                $scope.searchValue = '';
                $scope.platforms = CONSTANTS.PLATFORMS;
                $scope.platform = CONSTANTS.PLATFORMS.VK;

                $scope.submit = function ($event) {
                    $scope.contacts = null;

                    var params;
                    if ($scope.platform.id == CONSTANTS.PLATFORMS.TWITTER.id) {
                        if ($scope.searchValueNickname != undefined) {
                            var twitterExtraParam1 = "'nickname':'" + nicknameValue + "'";
                            var twitterExtraParam2 = "'anotherOneParam':'parse_more_than_one_param'";
                            params = ";params=" + twitterExtraParam1 + ";params=" + twitterExtraParam2;
                        }
                    }

                    if (!$scope.authorize($scope.platform.id)) {
                        return;
                    }

                    $scope.performSearch(params);

                };

                $scope.performSearch = function (params) {
                    $rootScope.platform = $scope.platform;
                    SocialService.search($rootScope.sessions[$scope.platform.id], $scope.platform.id, $scope.searchParam, $scope.searchValue, params)
                        .then(function (data) {
                            $scope.contacts = SocialService.buildContacts($scope.platform.id, data);
                            $scope.vm.contacts = $scope.contacts;
                            $scope.vm.setPage(1);
                        }, function (data) {
                            console.log(data);
                        });
                };

                $scope.authorize = function (platformId) {
                    if (!$rootScope.sessions || !$rootScope.sessions[platformId]) {
                        if (platformId == CONSTANTS.PLATFORMS.VK.id) {
                            if (!window.confirm('You are not logged in to VK. Log in?')) {
                                return false;
                            } else {
                                VK.Auth.login(function (response) {
                                    if (response.session) {
                                        $rootScope.sessions[CONSTANTS.PLATFORMS.VK.id] = response.session;

                                        var vkId = response.session.mid;
                                        UserService.storeUserSocialIds('vk', vkId);

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
                                FB.login(function (response) {
                                    if (response.authResponse) {
                                        $rootScope.sessions[CONSTANTS.PLATFORMS.FB.id] = response.authResponse;
                                        // FB.api('/me', function(response) {
                                        //     console.log('Good to see you, ' + response.name + '.');
                                        // });

                                        var fbId = response.authResponse.userID;
                                        UserService.storeUserSocialIds('fb', fbId);

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

                $scope.isActive = function (platform) {
                    return $rootScope.sessions[CONSTANTS.PLATFORMS[platform].id] ? 'active' : 'inactive';
                };

                $scope.logout = function () {
                    AuthenticationService.clearCredentials();
                    $location.path('/login');
                }

            }]);
})();