(function () {
    'use strict';

    angular.module('smartSearchApp.controllers')
        .controller('ContactDetailsController', ['$rootScope', '$scope', '$location', 'SocialService', 'UserService', 'CONSTANTS',
            function ($rootScope, $scope, $location, SocialService, UserService, CONSTANTS) {
                $scope.contactId = $location.search().contactId;

                //ad-hoc for twitter
                var nickname = $scope.contact.screenName;

                SocialService.search($rootScope.sessions[$rootScope.platform.id], $rootScope.platform.id, CONSTANTS.SEARCH_METHODS.BY_ID, $scope.contactId, nickname)
                    .then(function (data) {
                        $rootScope.dominantColor = '';
                        if ($rootScope.platform.id == CONSTANTS.PLATFORMS.VK.id) {
                            $scope.contactDetails = data[0];
                        } else if ($rootScope.platform.id == CONSTANTS.PLATFORMS.FB.id) {
                            $scope.contactDetails = data;
                            $scope.contactDetails.photo_big = data.picture.data.url;
                        } else if ($rootScope.platform.id == CONSTANTS.PLATFORMS.GOOGLE_PLUS.id) {
                            $scope.contactDetails = data;
                            $scope.contactDetails.photo_big = data.image.url.slice(0,-6);
                        } else if ($rootScope.platform.id == CONSTANTS.PLATFORMS.TWITTER.id) {
                            $scope.contactDetails = data;
                        }
                    }, function (data) {
                        console.log(data);
                    });

                $scope.checkDominantColor = function () {
                    var photoUrl = $scope.contactDetails.photo_big;
                    var color = UserService.checkDominantColor(photoUrl);
                }
            }]);
})();