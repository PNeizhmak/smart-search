(function() {
    'use strict';
    angular.module('smartSearchApp.services', []).service('SocialService', ['$http', '$q', function ($http, $q) {

        this.baseUrl = "http://localhost:8081/";

        this.search = function(platform, apiMethod, value) {
            var deferred = $q.defer();

            $http({
                method: 'GET',
                url: this.getServiceUrl(platform, apiMethod, value)
            }).then(function (data) {
                deferred.resolve(data);
            }, function (data) {
                deferred.reject(data);
            });

            return deferred.promise;
        };

        this.getServiceUrl = function(platform, apiMethod, value) {
            return this.baseUrl + platform + "/" + apiMethod + "/" + value;
        };

    }]);
})();