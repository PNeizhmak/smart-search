(function () {
    'use strict';

    angular.module('smartSearchApp.services')
        .service('SocialService', ['SocialDataProviderFactory', '$http', '$q', 'CONSTANTS',
            function (SocialDataProviderFactory, $http, $q, CONSTANTS) {

        this.searchAll = function (sessions, value) {
            var requests = {};
            for (var property in sessions) {
                if (sessions.hasOwnProperty(property)) {
                    var platformId = property;
                    requests[platformId] = this.search(sessions[platformId], platformId, CONSTANTS.SEARCH_METHODS.BY_NAME, value, null);
                }
            }
            var deferred = $q.defer();
            $q.all(requests).then(function(results) {
                deferred.resolve(results);
            }, function () {
                deferred.reject(data);
            });
            return deferred.promise;
        };

        this.search = function (sessionInfo, platform, apiMethod, value, params) {
            var provider = SocialDataProviderFactory.getSocialDataProvider(platform);
            return provider.search(sessionInfo, platform, apiMethod, value, params);
        };

        this.buildContacts = function (platformId, data) {
            var provider = SocialDataProviderFactory.getSocialDataProvider(platformId);
            return provider.buildContacts(data);
        };

    }]);
})();