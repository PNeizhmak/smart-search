(function () {
    'use strict';

    angular.module('smartSearchApp.controllers')
        .controller('RegisterController', ['UserService', '$location', '$rootScope', 'MessageService', '$scope',
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
})();