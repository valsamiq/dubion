(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingSongDeleteController',RatingSongDeleteController);

    RatingSongDeleteController.$inject = ['$uibModalInstance', 'entity', 'RatingSong'];

    function RatingSongDeleteController($uibModalInstance, entity, RatingSong) {
        var vm = this;

        vm.ratingSong = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RatingSong.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
