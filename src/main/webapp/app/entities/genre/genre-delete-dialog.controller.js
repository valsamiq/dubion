(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('GenreDeleteController',GenreDeleteController);

    GenreDeleteController.$inject = ['$uibModalInstance', 'entity', 'Genre'];

    function GenreDeleteController($uibModalInstance, entity, Genre) {
        var vm = this;

        vm.genre = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Genre.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
