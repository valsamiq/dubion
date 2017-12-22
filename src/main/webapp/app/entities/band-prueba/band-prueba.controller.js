(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BandPruebaController', BandPruebaController);

    BandPruebaController.$inject = ['DataUtils', 'BandPrueba'];

    function BandPruebaController(DataUtils, BandPrueba) {

        var vm = this;

        vm.bandPruebas = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            BandPrueba.query(function(result) {
                vm.bandPruebas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
