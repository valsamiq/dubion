(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BandDeleteController',BandDeleteController);

    BandDeleteController.$inject = ['$uibModalInstance', 'entity', 'Band'];

    function BandDeleteController($uibModalInstance, entity, Band) {
        var vm = this;

        vm.band = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Band.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
