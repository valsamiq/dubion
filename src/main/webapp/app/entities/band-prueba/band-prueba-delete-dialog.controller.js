(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BandPruebaDeleteController',BandPruebaDeleteController);

    BandPruebaDeleteController.$inject = ['$uibModalInstance', 'entity', 'BandPrueba'];

    function BandPruebaDeleteController($uibModalInstance, entity, BandPrueba) {
        var vm = this;

        vm.bandPrueba = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            BandPrueba.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
