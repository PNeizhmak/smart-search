(function() {
    'use strict';
    angular.module('smartSearchApp.controllers', ['smartSearchApp.services', 'smartSearchApp.constants'])
        .controller('SocialCtrl', ['$scope', 'SocialService', 'CONSTANTS', function ($scope, SocialService, CONSTANTS) {

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

            SocialService.search(platform, apiMethod, value, params).then(function(data) {
                $scope.result = JSON.stringify(data);
            }, function(data) {
                $scope.result = data.valueOf().responseText;
            });
        };

    }]);
})();