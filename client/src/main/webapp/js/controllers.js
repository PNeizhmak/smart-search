(function() {
    'use strict';
    angular.module('smartSearchApp.controllers', ['smartSearchApp.services']).controller('SocialCtrl', ['$scope', 'SocialService', function ($scope, SocialService) {

        $scope.result = '';
        $scope.searchParam = '';
        $scope.searchValue= '';
        $scope.platform = '';

        $scope.submit = function($event) {
            var platform = angular.element( document.querySelector( 'select#Platform' ) ).val();
            var apiMethod = angular.element( document.querySelector( 'select#Search-param' ) ).val();
            var value = angular.element( document.querySelector( 'input#search-value' ) ).val();
            SocialService.search(platform, apiMethod, value).then(function(data) {
                $scope.result = JSON.stringify(data);
            }, function(data) {
                $scope.result = data.valueOf().responseText;
            });
        };

    }]);
})();