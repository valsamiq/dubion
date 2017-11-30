(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingSongController', RatingSongController);

    RatingSongController.$inject = ['RatingSong'];

    function RatingSongController(RatingSong) {

        var vm = this;

        vm.ratingSongs = [];

        loadAll();

        function loadAll() {
            RatingSong.query(function(result) {
                vm.ratingSongs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
