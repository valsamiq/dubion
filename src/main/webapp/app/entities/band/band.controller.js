(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BandController', BandController);

    BandController.$inject = ['DataUtils', 'Band'];

    function BandController(DataUtils, Band) {

        var vm = this;

        vm.bands = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Band.query(function(result) {
                vm.bands = result;
                vm.searchQuery = null;
            });
        }
    }
})();
