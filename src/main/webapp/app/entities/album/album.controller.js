(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('AlbumController', AlbumController);

    AlbumController.$inject = ['Album'];

    function AlbumController(Album) {

        var vm = this;

        vm.albums = [];
        // vm.openFile = DataUtils.openFile;
        // vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Album.query(function(result) {
                vm.albums = result;
                vm.searchQuery = null;
            });

        }

    }
})();
