(function() {
    'use strict';
    var module = angular.module('smartSearchApp.controllers', ['smartSearchApp.services', 'smartSearchApp.constants']);

    module.controller('LoginController', ['$scope', '$rootScope', '$location', 'AuthenticationService',
            function ($scope, $rootScope, $location, AuthenticationService) {
                AuthenticationService.clearCredentials();

                $scope.login = function () {
                    $scope.dataLoading = true;
                    $scope.error = '';
                    AuthenticationService.login($scope.username, $scope.password).then(function(data) {
                        $scope.dataLoading = false;
                        if (data.success) {
                            AuthenticationService.setCredentials($scope.username, $scope.password);
                            $rootScope.username = $scope.username;
                            $location.path('/main');
                        } else {
                            $scope.error = data.error;
                        }
                    }, function (data) {
                        $scope.dataLoading = false;
                        $scope.error = 'Server error.';
                        console.log(data);
                    });
                };
            }]);

    module.controller('RegisterController', ['UserService', '$location', '$rootScope', 'MessageService', '$scope',
        function RegisterController(UserService, $location, $rootScope, MessageService, $scope) {
            $scope.user = null;
            $scope.register = function () {
                $scope.dataLoading = true;
                UserService.createUser($scope.user)
                    .then(function (response) {
                        if (response.success) {
                            MessageService.successMsg('Registration successful', true);
                            $location.path('/login');
                        } else {
                            MessageService.errorMsg(response.message);
                            $scope.dataLoading = false;
                        }
                    });
            };
        }]);

    module.controller('SocialController', ['$location', '$rootScope', '$scope', '$window', 'SocialService', 'AuthenticationService', 'UserService', 'CONSTANTS',
        function ($location, $rootScope, $scope, $window, SocialService, AuthenticationService, UserService, CONSTANTS) {

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
            }
            
            $scope.performSearch(params);

        };

        $scope.performSearch = function (params) {
            if ($scope.platform.id == CONSTANTS.PLATFORMS.VK.id) {
                var token = $rootScope.sessions[CONSTANTS.PLATFORMS.VK.id].sid;
                SocialService.searchVK(token, $scope.searchParam, $scope.searchValue)
                    .then(function (data) {
                        $scope.contacts = SocialService.buildContacts($scope.platform.id, data);
                    }, function (data) {
                        console.log(data);
                    });
            } else if ($scope.platform.id == CONSTANTS.PLATFORMS.FB.id) {
                var token = $rootScope.sessions[CONSTANTS.PLATFORMS.FB.id].accessToken;
                SocialService.searchFB(token, $scope.searchParam, $scope.searchValue)
                    .then(function (data) {
                        $scope.contacts = SocialService.buildContacts($scope.platform.id, data);
                    }, function (data) {
                        console.log(data);
                    });
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
                        FB.login(function(response) {
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

        $scope.logout = function() {
            AuthenticationService.clearCredentials();
            $location.path('/login');
        }

    }]);

    module.controller('ContactDetailsController', ['$scope', '$location', 'SocialService', 'CONSTANTS',
        function ($scope, $location, SocialService, CONSTANTS) {
            $scope.contactId = $location.search().contactId;
        }]);

})();