(function () {
    'use strict';

    angular.module('smartSearchApp.services')
        .service('UserService', ['$rootScope', '$http',
            function UserService($rootScope, $http) {
                var service = {};

                service.createUser = createUser;
                service.deleteUser = deleteUser;

                service.storeUserSocialIds = storeUserSocialIds;
                service.checkDominantColor = checkDominantColor;

                //methods for admin application
                service.getAll = getAll;
                service.getById = getById;
                service.getByUsername = getByUsername;

                return service;

                function createUser(user) {
                    var data = {
                        username: encodeURIComponent(user.username),
                        password: encodeURIComponent(user.password),
                        email: encodeURIComponent(user.email)
                    };

                    return $http.post(
                        '/rest/auth/register',
                        data
                    ).then(handleSuccess, handleError('Error creating user'));
                }

                function deleteUser(id) {
                }

                function storeUserSocialIds(socialNetwork, id) {
                    var data = "?socialNetwork=" + encodeURIComponent(socialNetwork) + "&socialNetworkUserId=" + encodeURIComponent(id);

                    return $http.post(
                        '/rest/user-social/storeSocialIds' + data
                    ).then(handleSuccess, handleError('Error creating user'));
                }

                function checkDominantColor(photoUrl) {
                    var data = "?photoUrl=" + encodeURIComponent(photoUrl);

                    return $http.post(
                        '/rest/user-social/checkDominantColor' + data
                    )
                        .success(function(data){
                            $rootScope.dominantColor = data;
                        })
                        .then(handleSuccess, handleError('Error creating user'));
                }

                function getAll() {
                }

                function getById(id) {
                }

                function getByUsername(username) {
                }

                function handleSuccess(res) {
                    return res.data;
                }

                function handleError(error) {
                    return function () {
                        return {success: false, message: error};
                    };
                }
            }]);

})();