(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('ArtistController', ArtistController);

    ArtistController.$inject = ['Artist'];

    function ArtistController(Artist) {

        var vm = this;

        vm.artists = [];

        loadAll();

        function loadAll() {

            Artist.get({id : 1}, function (data){
                console.log(data);
            })
            Artist.query(function(result) {
                vm.artists = result;
                vm.searchQuery = null;
            });
        }
    }
})();
