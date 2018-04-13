(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('AlbumController', AlbumController);

    AlbumController.$inject = ['DataUtils', 'Album'];

    function AlbumController(DataUtils, Album) {

        var vm = this;

        vm.albums = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;
        loadAll();



        function loadAll() {

            Album.get({id : 1}, function (data){
                console.log(data);
            })

            Album.query(function(result) {
                vm.albums = result;
                vm.searchQuery = null;
            });

        }

    }
})();
