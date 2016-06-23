(function() {
    'use strict';
    var module = angular.module('smartSearchApp.controllers', ['smartSearchApp.services', 'smartSearchApp.constants', 'ngCookies']);
    module.controller('SocialController', ['$rootScope', '$scope', '$cookies', '$window', 'SocialService', 'CONSTANTS',
        function ($rootScope, $scope, $cookies, $window, SocialService, CONSTANTS) {

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
                $scope.performSearch();
            }


        };

        $scope.performSearch = function () {
            if ($scope.platform.id == CONSTANTS.PLATFORMS.VK.id) {
                if ($scope.searchParam == CONSTANTS.SEARCH_METHODS.BY_ID) {
                    VK.Api.call('users.get', {
                        user_ids: $scope.searchValue,
                        'fields': 'city,contacts,site,education,status,connections',
                        'name_case': 'Nom'
                    }, function (r) {
                        $scope.contacts = SocialService.buildContacts(r.response);
                        $scope.$apply();
                    });
                } else if ($scope.searchParam == CONSTANTS.SEARCH_METHODS.BY_NAME) {
                    VK.Api.call('users.search', {'q': $scope.searchValue}, function (r) {
                        $scope.contacts = SocialService.buildContacts(r.response);
                        $scope.$apply();
                    });
                }
            } else {
                SocialService.search($cookies.get(CONSTANTS.COOKIES.USER_ID), $scope.platform.id, $scope.searchParam, $scope.searchValue, params)
                    .then(function (data) {
                        $scope.contacts = SocialService.buildContacts(data);
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
                                /* Пользователь успешно авторизовался */
                                $rootScope.sessions[CONSTANTS.PLATFORMS.VK.id] = response.session;
                                if (response.settings) {
                                    /* Выбранные настройки доступа пользователя, если они были запрошены */
                                }
                                $scope.performSearch();
                            } else {
                                /* Пользователь нажал кнопку Отмена в окне авторизации */
                            }
                        });
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
                    /* Пользователь успешно авторизовался */
                    if (response.settings) {
                        /* Выбранные настройки доступа пользователя, если они были запрошены */
                    }
                } else {
                    /* Пользователь нажал кнопку Отмена в окне авторизации */
                }
            });
        };

    }]);

    module.controller('ContactDetailsController', ['$scope', '$location', 'SocialService', 'CONSTANTS',
        function ($scope, $location, SocialService, CONSTANTS) {
            $scope.contactId = $location.search().contactId;
        }]);

})();