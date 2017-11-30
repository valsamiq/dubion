(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingBandDeleteController',RatingBandDeleteController);

    RatingBandDeleteController.$inject = ['$uibModalInstance', 'entity', 'RatingBand'];

    function RatingBandDeleteController($uibModalInstance, entity, RatingBand) {
        var vm = this;

        vm.ratingBand = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RatingBand.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
