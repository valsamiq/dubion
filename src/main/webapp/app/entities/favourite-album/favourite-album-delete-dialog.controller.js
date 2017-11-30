(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('FavouriteAlbumDeleteController',FavouriteAlbumDeleteController);

    FavouriteAlbumDeleteController.$inject = ['$uibModalInstance', 'entity', 'FavouriteAlbum'];

    function FavouriteAlbumDeleteController($uibModalInstance, entity, FavouriteAlbum) {
        var vm = this;

        vm.favouriteAlbum = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            FavouriteAlbum.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
