(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
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
            return $http.post('/auth/register', user).then(handleSuccess, handleError('Error creating user'));
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
    }

})();