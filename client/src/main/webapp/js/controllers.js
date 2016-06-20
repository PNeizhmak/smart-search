(function() {
    'use strict';
    angular.module('smartSearchApp.controllers', ['smartSearchApp.services', 'smartSearchApp.constants', 'ngCookies'])
        .controller('SocialCtrl', ['$scope', '$cookies', '$window', 'SocialService', 'CONSTANTS', function ($scope, $cookies, $window, SocialService, CONSTANTS) {

        $scope.result = '';
        $scope.searchParam = 'getUserInfo';
        $scope.searchValue= '';
        $scope.platform = CONSTANTS.PLATFORMS.VK;

        $scope.submit = function($event) {
            var platform = angular.element( document.querySelector( 'select#Platform' ) ).val();
            var apiMethod = angular.element( document.querySelector( 'select#Search-param' ) ).val();
            var value = angular.element( document.querySelector( 'input#search-value' ) ).val();

            var params;
            if ($scope.platform == CONSTANTS.PLATFORMS.TWITTER) {
                var nicknameValue = angular.element( document.querySelector( 'input#search-value-nickname' ) ).val();
                if (nicknameValue != undefined) {
                    var twitterExtraParam1 = "'nickname':'" + nicknameValue + "'";
                    var twitterExtraParam2 =  "'anotherOneParam':'parse_more_than_one_param'";
                    params = ";params=" + twitterExtraParam1 + ";params=" + twitterExtraParam2;
                }
            }

            SocialService.search($cookies.get("user_id"), platform, apiMethod, value, params).then(function(data) {
                $scope.result = JSON.stringify(data);
            }, function(data) {
                $scope.result = data.valueOf().responseText;
            });
        };

        $scope.getToken = function ($event) {
            $event.preventDefault();
            $window.open("https://oauth.vk.com/authorize?client_id=5087523&redirect_uri=http://localhost:8081/rest/vk/setAccessToken&scope=offline&display=page&v=5.52&response_type=code");
        };

        var that = this;
        $window.addEventListener('message', function(event) {
            $cookies.put("user_id", that.getCookieFromStr("user_id", event.data));
        });

        this.getCookieFromStr = function (cookieName, str) {
            var matches = str.match(new RegExp(
                "(?:^|; )" + cookieName.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
            ));
            return matches ? decodeURIComponent(matches[1]) : undefined;
        };

    }]);

})();