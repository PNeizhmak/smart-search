(function () {
    'use strict';
    var module = angular.module('smartSearchApp.controllers');
    module.directive('contactsList', ['$rootScope', '$location', 'PagerService', 'contactsService',
        function ($rootScope, $location, PagerService, contactsService) {
        return {
            templateUrl: 'templates/directives/contactsList.html',
            restrict: 'E',
            controller: function ($scope, $element, $attrs) {

                var vm = this;

                vm.displayedContacts = contactsService.model.displayedContacts || [];
                vm.contacts = contactsService.model.contacts || [];
                $scope.showDetails = function (contact) {
                    $rootScope.contact = contact;
                    contactsService.model.contact = contact;
                    $location.path('/details').search({contactId: contact.id});
                };

                vm.pager = {};
                vm.setPage = setPage;

                initController();

                function initController() {
                    // initialize to page 1
                    vm.setPage(1);
                }

                function setPage(page) {
                    vm.platform = $scope.platform;
                    contactsService.model.platform = vm.platform;
                    if (page < 1 || (page > vm.pager.totalPages && vm.pager.totalPages > 0)) {
                        return;
                    }

                    // get pager object from service
                    vm.pager = PagerService.GetPager(vm.contacts.length, page);

                    // get current page of items
                    if (vm.contacts && vm.contacts.length > 0) {
                        vm.displayedContacts = vm.contacts.slice(vm.pager.startIndex, vm.pager.endIndex + 1);
                        contactsService.model.displayedContacts = vm.displayedContacts;
                    }
                }
            },
            controllerAs: 'vm'
        }
    }]);
})();