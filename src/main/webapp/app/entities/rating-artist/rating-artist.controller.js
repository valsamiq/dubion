(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingArtistController', RatingArtistController);

    RatingArtistController.$inject = ['RatingArtist'];

    function RatingArtistController(RatingArtist) {

        var vm = this;

        vm.ratingArtists = [];

        loadAll();

        function loadAll() {
            RatingArtist.query(function(result) {
                vm.ratingArtists = result;
                vm.searchQuery = null;
            });
        }
    }
})();
