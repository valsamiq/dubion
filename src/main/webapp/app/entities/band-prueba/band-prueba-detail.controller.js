(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BandPruebaDetailController', BandPruebaDetailController);

    BandPruebaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'BandPrueba'];

    function BandPruebaDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, BandPrueba) {
        var vm = this;

        vm.bandPrueba = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('dubionApp:bandPruebaUpdate', function(event, result) {
            vm.bandPrueba = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
