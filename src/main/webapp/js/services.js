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

        this.buildContacts = function (platformId, data) {
            if (platformId == CONSTANTS.PLATFORMS.VK.id) {
                return this.buildContactsVK(data);
            } else if (platformId == CONSTANTS.PLATFORMS.FB.id) {
                return this.buildContactsFB(data);
            } else if (platformId == CONSTANTS.PLATFORMS.FORSQUARE.id) {
                return this.buildContactsFSQR(data);
            } else {
                return data;
            }
        };

        this.buildContactsFSQR = function (data) {
            var contacts = [];
            data.results.forEach(function (item) {
                contacts.push({id: item.id, firstName: item.firstName, lastName: item.lastName});
            });
            return contacts;
        };

        this.buildContactsVK = function (data) {
            var contacts = [];
            data.forEach(function (item) {
                contacts.push({id: item.uid, firstName: item.first_name, lastName: item.last_name});
            });
            return contacts;
        };

        this.buildContactsFB = function (data) {
            var contacts = [];
            data.forEach(function (item) {
                contacts.push({id: item.id, firstName: item.name});
            });
            return contacts;
        }

    }]);
})();