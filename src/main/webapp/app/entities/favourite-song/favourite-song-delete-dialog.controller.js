(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('FavouriteSongDeleteController',FavouriteSongDeleteController);

    FavouriteSongDeleteController.$inject = ['$uibModalInstance', 'entity', 'FavouriteSong'];

    function FavouriteSongDeleteController($uibModalInstance, entity, FavouriteSong) {
        var vm = this;

        vm.favouriteSong = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            FavouriteSong.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
