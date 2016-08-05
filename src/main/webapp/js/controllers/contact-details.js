(function () {
    'use strict';

    angular.module('smartSearchApp.controllers')
        .controller('ContactDetailsController', ['$rootScope', '$scope', '$location', 'SocialService', 'CONSTANTS',
            function ($rootScope, $scope, $location, SocialService, CONSTANTS) {
                $scope.contactId = $location.search().contactId;
                
                var token = $rootScope.sessions[$rootScope.platform.id] != null ? $rootScope.sessions[$rootScope.platform.id].sid : null;
                SocialService.search(token, $rootScope.platform.id, CONSTANTS.SEARCH_METHODS.BY_ID, $scope.contactId, null)
                    .then(function (data) {
                        $scope.contactDetails = data;
                    }, function (data) {
                        console.log(data);
                    });
            }]);
})();