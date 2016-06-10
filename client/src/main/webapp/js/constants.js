(function () {
    'use strict';
    angular.module('smartSearchApp.constants', []).factory('CONSTANTS', function () {
        return {
            BASE_URL: 'http://localhost:8081/',
            PLATFORMS: {
                VK: 'vk',
                FB: 'fb',
                INSTAGRAM: 'instagram',
                GITHUB: 'github',
                TWITTER: 'twitter'
            },
            SEARCH_METHODS: {
                BY_NAME: 'searchByName',
                BY_ID: 'getUserInfo'
            }
        };
    });
})();
