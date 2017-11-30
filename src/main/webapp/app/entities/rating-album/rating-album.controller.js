(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingAlbumController', RatingAlbumController);

    RatingAlbumController.$inject = ['RatingAlbum'];

    function RatingAlbumController(RatingAlbum) {

        var vm = this;

        vm.ratingAlbums = [];

        loadAll();

        function loadAll() {
            RatingAlbum.query(function(result) {
                vm.ratingAlbums = result;
                vm.searchQuery = null;
            });
        }
    }
})();
