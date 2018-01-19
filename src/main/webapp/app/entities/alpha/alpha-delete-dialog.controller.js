(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('AlphaDeleteController',AlphaDeleteController);

    AlphaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Alpha'];

    function AlphaDeleteController($uibModalInstance, entity, Alpha) {
        var vm = this;

        vm.alpha = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Alpha.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
