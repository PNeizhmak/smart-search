(function () {
    'use strict';

    angular.module('smartSearchApp.services')
        .service('UserService', ['$http',
            function UserService($http) {
                var service = {};

                service.createUser = createUser;
                service.deleteUser = deleteUser;

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