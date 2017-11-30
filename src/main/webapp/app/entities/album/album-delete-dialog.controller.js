(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('AlbumDeleteController',AlbumDeleteController);

    AlbumDeleteController.$inject = ['$uibModalInstance', 'entity', 'Album'];

    function AlbumDeleteController($uibModalInstance, entity, Album) {
        var vm = this;

        vm.album = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Album.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
