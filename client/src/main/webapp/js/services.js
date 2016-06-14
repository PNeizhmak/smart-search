(function() {
    'use strict';
    angular.module('smartSearchApp.services', ['smartSearchApp.constants'])
        .service('SocialService', ['$http', '$q', '$window', 'CONSTANTS', function ($http, $q, $window, CONSTANTS) {

        this.search = function(userId, platform, apiMethod, value, params) {
            var deferred = $q.defer();
            params = params || '';

            $http({
                method: 'GET',
                url: this.getServiceUrl(userId, platform, apiMethod, value, params)
            }).then(function (data) {
                deferred.resolve(data);
            }, function (data) {
                deferred.reject(data);
            });

            return deferred.promise;
        };

        this.getServiceUrl = function(userId, platform, apiMethod, value, params) {
            return CONSTANTS.BASE_URL + platform + "/" + userId + "/" + apiMethod + "/" + value + params;
        };

    }]);
})();