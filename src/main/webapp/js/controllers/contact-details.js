(function () {
    'use strict';

    angular.module('smartSearchApp.controllers')
        .controller('ContactDetailsController', ['$scope', '$location', 'SocialService', 'CONSTANTS',
            function ($scope, $location, SocialService, CONSTANTS) {
                $scope.contactId = $location.search().contactId;
            }]);
})();