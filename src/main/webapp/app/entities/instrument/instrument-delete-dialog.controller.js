(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('InstrumentDeleteController',InstrumentDeleteController);

    InstrumentDeleteController.$inject = ['$uibModalInstance', 'entity', 'Instrument'];

    function InstrumentDeleteController($uibModalInstance, entity, Instrument) {
        var vm = this;

        vm.instrument = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Instrument.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
