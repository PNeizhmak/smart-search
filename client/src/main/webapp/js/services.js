(function() {
    'use strict';
    angular.module('smartSearchApp.services', []).service('SocialService', ['$http', '$q', function ($http, $q) {

        this.baseUrl = "http://localhost:8081/";

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
            return this.baseUrl + platform + "/" + apiMethod + "/" + value + params;
        };

    }]);
})();