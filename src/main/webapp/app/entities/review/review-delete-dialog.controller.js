(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('ReviewDeleteController',ReviewDeleteController);

    ReviewDeleteController.$inject = ['$uibModalInstance', 'entity', 'Review'];

    function ReviewDeleteController($uibModalInstance, entity, Review) {
        var vm = this;

        vm.review = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Review.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
