(function () {
    'use strict';

    angular.module('smartSearchApp.controllers')
        .controller('ContactDetailsController', ['$rootScope', '$scope', '$location', 'SocialService', 'UserService', 'CONSTANTS',
            function ($rootScope, $scope, $location, SocialService, UserService, CONSTANTS) {
                $scope.contactId = $location.search().contactId;
                
                var token = $rootScope.sessions[$rootScope.platform.id] != null ? $rootScope.sessions[$rootScope.platform.id].sid : null;
                SocialService.search(token, $rootScope.platform.id, CONSTANTS.SEARCH_METHODS.BY_ID, $scope.contactId, null)
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