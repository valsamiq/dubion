(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('InstrumentDetailController', InstrumentDetailController);

    InstrumentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Instrument'];

    function InstrumentDetailController($scope, $rootScope, $stateParams, previousState, entity, Instrument) {
        var vm = this;

        vm.instrument = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:instrumentUpdate', function(event, result) {
            vm.instrument = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
