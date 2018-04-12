(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('SongController', SongController);

    SongController.$inject = ['Song'];

    function SongController(Song) {

        var vm = this;

        vm.songs = [];

        loadAll();

        function loadAll() {
            Song.get({id : 1}, function (data){
                console.log(data);
            })
            Song.query(function(result) {
                vm.songs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
