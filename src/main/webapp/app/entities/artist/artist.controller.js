(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('ArtistController', ArtistController);

    ArtistController.$inject = ['DataUtils', 'Artist'];

    function ArtistController(DataUtils, Artist) {

        var vm = this;

        vm.artists = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Artist.query(function(result) {
                vm.artists = result;
                vm.searchQuery = null;
            });
        }
    }
})();
