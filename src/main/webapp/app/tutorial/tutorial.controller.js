(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('tutorialController', tutorialController);

    tutorialController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function tutorialController ($scope, Principal, LoginService, $state) {
        var vm = this;

    }
})();
