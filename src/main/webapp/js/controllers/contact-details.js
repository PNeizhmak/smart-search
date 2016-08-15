(function () {
    'use strict';

    angular.module('smartSearchApp.controllers')
        .controller('ContactDetailsController', ['$rootScope', '$scope', '$location', 'SocialService', 'UserService', 'CONSTANTS',
            function ($rootScope, $scope, $location, SocialService, UserService, CONSTANTS) {
                $scope.contactId = $location.search().contactId;
                SocialService.search($rootScope.sessions[$rootScope.platform.id], $rootScope.platform.id, CONSTANTS.SEARCH_METHODS.BY_ID, $scope.contactId, null)
                    .then(function (data) {
                        $rootScope.dominantColor = '';
                        $scope.contactDetails = data[0];
                    }, function (data) {
                        console.log(data);
                    });

                $scope.checkDominantColor = function(){
                    var photoUrl = $scope.contactDetails.photo_big;
                    var color = UserService.checkDominantColor(photoUrl);
                }
            }]);
})();