(function () {
    'use strict';

    angular.module('smartSearchApp.services')
        .service('SocialService', ['SocialDataProviderFactory', '$http', '$q', 'CONSTANTS',
            function (SocialDataProviderFactory) {

        this.search = function (token, platform, apiMethod, value, params) {
            var provider = SocialDataProviderFactory.getSocialDataProvider(platform);
            return provider.search(token, platform, apiMethod, value, params);
        };

        this.buildContacts = function (platformId, data) {
            var provider = SocialDataProviderFactory.getSocialDataProvider(platformId);
            return provider.buildContacts(data);
        };

    }]);
})();