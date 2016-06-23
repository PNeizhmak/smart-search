(function () {
    'use strict';
    angular.module('smartSearchApp.constants', []).factory('CONSTANTS', function () {
        return {
            BASE_URL: 'http://localhost:8081/rest/',
            PLATFORMS: {
                VK: {
                    id: 'vk',
                    name: 'VK'
                },
                FB: {
                    id: 'fb',
                    name: 'Facebook'
                },
                INSTAGRAM: {
                    id: 'instagram',
                    name: 'Instagram'
                },
                GITHUB: {
                    id: 'github',
                    name: 'Github'
                },
                TWITTER: {
                    id: 'twitter',
                    name: 'Twitter'
                },
                GOOGLE_PLUS: {
                    id: 'googlePlus',
                    name: 'Google+'
                },
                FORSQUARE: {
                    id: 'forsquare',
                    name: 'Forsquare'
                }
            },
            SEARCH_METHODS: {
                BY_NAME: 'searchByName',
                BY_ID: 'getUserInfo'
            }
        };
    });
})();
