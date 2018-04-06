(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('PruebaDetailController', PruebaDetailController);

    PruebaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Prueba'];

    function PruebaDetailController($scope, $rootScope, $stateParams, previousState, entity, Prueba) {
        var vm = this;

        vm.prueba = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:pruebaUpdate', function(event, result) {
            vm.prueba = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
