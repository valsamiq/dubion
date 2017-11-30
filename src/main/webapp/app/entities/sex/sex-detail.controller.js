(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('SexDetailController', SexDetailController);

    SexDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Sex', 'UserExt'];

    function SexDetailController($scope, $rootScope, $stateParams, previousState, entity, Sex, UserExt) {
        var vm = this;

        vm.sex = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:sexUpdate', function(event, result) {
            vm.sex = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
