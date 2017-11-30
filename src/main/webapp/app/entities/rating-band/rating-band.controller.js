(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingBandController', RatingBandController);

    RatingBandController.$inject = ['RatingBand'];

    function RatingBandController(RatingBand) {

        var vm = this;

        vm.ratingBands = [];

        loadAll();

        function loadAll() {
            RatingBand.query(function(result) {
                vm.ratingBands = result;
                vm.searchQuery = null;
            });
        }
    }
})();
