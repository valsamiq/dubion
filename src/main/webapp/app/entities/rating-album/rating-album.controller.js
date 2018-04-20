(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingAlbumController', RatingAlbumController);

    RatingAlbumController.$inject = ['RatingAlbum'];

    function RatingAlbumController(RatingAlbum) {

        var vm = this;

        vm.ratingAlbums = [];
        vm.ratingAlbum = ratingAlbum;

        loadAll();
        ratingAlbum(2);

        function loadAll() {
            RatingAlbum.query(function(result) {
                vm.ratingAlbums = result;
                vm.searchQuery = null;
            });
        }
        var r;
        function ratingAlbum(id) {
            RatingAlbum.ratingByAlbum({id : 2}, function (data){

                    console.log(data);
                    r = data.rating;
                    console.log(r);

            }, function(data){

                console.log("error??");
                r = 0;

            });

        }
    }
})();
