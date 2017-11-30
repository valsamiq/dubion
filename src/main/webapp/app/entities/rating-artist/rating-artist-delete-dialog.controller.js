(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingArtistDeleteController',RatingArtistDeleteController);

    RatingArtistDeleteController.$inject = ['$uibModalInstance', 'entity', 'RatingArtist'];

    function RatingArtistDeleteController($uibModalInstance, entity, RatingArtist) {
        var vm = this;

        vm.ratingArtist = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RatingArtist.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
