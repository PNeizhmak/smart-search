(function() {
    'use strict';
    angular.module('smartSearchApp.services', ['smartSearchApp.constants'])
        .service('SocialService', ['$http', '$q', 'CONSTANTS', function ($http, $q, CONSTANTS) {

        this.search = function(userId, platform, apiMethod, value, params) {
            var deferred = $q.defer();
            params = params || '';

            $http({
                method: 'GET',
                url: this.getServiceUrl(userId, platform, apiMethod, value, params)
            }).then(function (data) {
                deferred.resolve(data.data.response);
            }, function (data) {
                deferred.reject(data);
            });

            return deferred.promise;
        };

        this.getServiceUrl = function(userId, platform, apiMethod, value, params) {
            return CONSTANTS.BASE_URL + platform + "/" + userId + "/" + apiMethod + "/" + value + params;
        };

            this.buildContacts = function (data) {
                var contacts = [];
                data.forEach(function (item) {
                    contacts.push({id: item.uid, firstName: item.first_name, lastName: item.last_name});
                });
                return contacts;
            }

    }]);
})();