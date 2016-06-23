(function() {
    'use strict';
    var module = angular.module('smartSearchApp.controllers');
    module.directive('contactsList', ['$rootScope', '$location', function ($rootScope, $location) {
            return {
                link: function (scope, element, attrs) {

                },
                templateUrl: 'templates/directives/contactsList.html',
                restrict: 'E',
                controller: function ($scope, $element, $attrs) {
                    $scope.contacts = $scope.contacts || {};
                    $scope.showDetails = function (contact) {
                        $rootScope.contact = contact;
                        $location.path('/details').search({contactId: contact.id});
                    };
                }
            }
        }]);
})();