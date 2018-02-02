(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('GammaDeleteController',GammaDeleteController);

    GammaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Gamma'];

    function GammaDeleteController($uibModalInstance, entity, Gamma) {
        var vm = this;

        vm.gamma = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Gamma.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
