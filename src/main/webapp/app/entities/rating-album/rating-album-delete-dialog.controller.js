(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingAlbumDeleteController',RatingAlbumDeleteController);

    RatingAlbumDeleteController.$inject = ['$uibModalInstance', 'entity', 'RatingAlbum'];

    function RatingAlbumDeleteController($uibModalInstance, entity, RatingAlbum) {
        var vm = this;

        vm.ratingAlbum = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RatingAlbum.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
