(function () {
    'use strict';

    angular.module('smartSearchApp.controllers')
        .controller('LoginController', ['$scope', '$rootScope', '$location', 'AuthenticationService',
            function ($scope, $rootScope, $location, AuthenticationService) {
                AuthenticationService.clearCredentials();

                $scope.login = function () {
                    $scope.dataLoading = true;
                    $scope.error = '';
                    AuthenticationService.login($scope.username, $scope.password).then(function (data) {
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
})();