(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('FavouriteSongController', FavouriteSongController);

    FavouriteSongController.$inject = ['FavouriteSong'];

    function FavouriteSongController(FavouriteSong) {

        var vm = this;

        vm.favouriteSongs = [];

        loadAll();

        function loadAll() {
            FavouriteSong.query(function(result) {
                vm.favouriteSongs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
