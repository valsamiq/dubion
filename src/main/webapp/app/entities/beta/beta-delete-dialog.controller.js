(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BetaDeleteController',BetaDeleteController);

    BetaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Beta'];

    function BetaDeleteController($uibModalInstance, entity, Beta) {
        var vm = this;

        vm.beta = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Beta.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
