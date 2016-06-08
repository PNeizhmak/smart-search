(function() {
    'use strict';
    angular.module('smartSearchApp.controllers', []).controller('firstCtrl', ['$scope', '$http', function ($scope, $http) {

        var baseUrl = "http://localhost:8081/";

        $scope.result = '';
        $scope.searchParam = '';
        $scope.searchValue= '';
        $scope.platform = '';

        $scope.submit = function($event) {
            $http({
                method: 'GET',
                url: getServiceUrl(baseUrl)
            }).then(function (data) {
                $scope.result = JSON.stringify(data);
                console.log(data);
            }, function (data) {
                $scope.result = data.valueOf().responseText;
                console.log(data.valueOf());
            });
        };

        var getServiceUrl = function (baseUrl) {
            var platform = angular.element( document.querySelector( 'select#Platform' ) ).val();
            var apiMethod = angular.element( document.querySelector( 'select#Search-param' ) ).val();
            var value = angular.element( document.querySelector( 'input#search-value' ) ).val();
            return baseUrl + platform + "/" + apiMethod + "/" + value;
        };
    }]);
})();