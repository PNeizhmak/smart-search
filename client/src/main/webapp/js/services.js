(function() {
    'use strict';
    angular.module('smartSearchApp.services', ['smartSearchApp.constants'])
        .service('SocialService', ['$http', '$q', 'CONSTANTS', function ($http, $q, CONSTANTS) {

        this.search = function(platform, apiMethod, value, params) {
            var deferred = $q.defer();
            params = params || '';

            $http({
                method: 'GET',
                url: this.getServiceUrl(platform, apiMethod, value, params)
            }).then(function (data) {
                deferred.resolve(data);
            }, function (data) {
                deferred.reject(data);
            });

            return deferred.promise;
        };

        this.getServiceUrl = function(platform, apiMethod, value, params) {
            return CONSTANTS.BASE_URL + platform + "/" + apiMethod + "/" + value + params;
        };

    }]);
})();