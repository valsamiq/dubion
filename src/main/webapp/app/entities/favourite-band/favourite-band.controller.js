(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('FavouriteBandController', FavouriteBandController);

    FavouriteBandController.$inject = ['FavouriteBand'];

    function FavouriteBandController(FavouriteBand) {

        var vm = this;

        vm.favouriteBands = [];

        loadAll();

        function loadAll() {
            FavouriteBand.query(function(result) {
                vm.favouriteBands = result;
                vm.searchQuery = null;
            });
        }
    }
})();
