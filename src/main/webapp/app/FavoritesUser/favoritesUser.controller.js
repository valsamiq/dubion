(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('favoritesUserController', favoritesUserController);

    favoritesUserController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Album','$stateParams','FavouriteAlbum','RatingAlbum','Band','FavouriteBand'];

    function favoritesUserController ($scope, Principal, LoginService, $state, Album,$stateParams,FavouriteAlbum,RatingAlbum, Band, FavouriteBand) {

        var vm = this;

        vm.likeUpDown="r";
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.favouriteAlbum={};
        vm.favouriteBand={};
        //vm.favouriteAlbum.liked=true;

        vm.albumId;
        vm.bandId;
        // vm.ratingAlbums = [];
        // vm.ratingAlbum= {};
        vm.salbums=[];
        vm.sbands=[];
        loadAll();

        function loadAll() {
            FavouriteBand.favoriteByBandlike(function(result) {
                vm.sbands = result;
                vm.searchQuery = null;
                // console.dir(vm.sbands);

            });

            FavouriteAlbum.favoriteByAlbumlike(function(result) {
                vm.salbums = result;
                vm.searchQuery = null;
                // console.dir(vm.salbums);

            });
        }

        // vm.likeDislike=function(){
        //     if(vm.likeUpDown=="s"){
        //         vm.favouriteAlbum.liked=false;
        //         vm.likeUpDown="r";
        //     }else{
        //         vm.favouriteAlbum.liked=true;
        //         vm.likeUpDown="s";
        //     }
        //
        //     save();
        //     function save () {
        //         vm.isSaving = true;
        //
        //         if(vm.favouriteAlbum.id){
        //             // console.log("RA_ID: "+vm.favouriteAlbum.id);
        //             vm.favouriteAlbum.album=vm.albumActual;
        //             FavouriteAlbum.update(vm.favouriteAlbum, onSaveSuccess, onSaveError);
        //         } else {
        //             vm.favouriteAlbum.album=vm.albumActual;
        //             FavouriteAlbum.save(vm.favouriteAlbum, onSaveSuccess, onSaveError);
        //         }
        //     }
        //     function onSaveSuccess (result) {
        //         // console.log("SUCCESSS");
        //         $scope.$emit('dubionApp:favouriteAlbumUpdate', result);
        //         vm.isSaving = false;
        //     }
        //
        //     function onSaveError () {
        //         // console.log("EERROR");
        //         vm.isSaving = false;
        //     }
        //
        // }
        // function getFavoriteAlbum(id) {
        //     FavouriteAlbum.favoriteByAlbum({id : id}, function (data){
        //
        //         vm.favouriteAlbum = data;
        //         if(vm.favouriteAlbum.liked){
        //             vm.likeUpDown="s";
        //         }else{
        //             vm.likeUpDown="r";
        //         }
        //
        //
        //     }, function(data){
        //
        //
        //     });
        //
        // }



        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });


        getAccount();


        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;

            });
        }
        function register () {
            $state.go('register');
        }


    }
})();
