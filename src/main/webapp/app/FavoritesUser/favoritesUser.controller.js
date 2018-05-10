(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('favoritesUserController', favoritesUserController);

    favoritesUserController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Album','$stateParams','FavouriteAlbum','RatingAlbum','Band'];

    function favoritesUserController ($scope, Principal, LoginService, $state, Album,$stateParams,FavouriteAlbum,RatingAlbum, Band) {

        var vm = this;
        console.log("favoritesUserController flag3333");


        vm.likeUpDown="r";
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.favouriteAlbum={};
        //vm.favouriteAlbum.liked=true;

        vm.albumId;
        vm.ratingAlbums = [];
        vm.ratingAlbum= {};

        vm.salbums=[];
        loadAll();

        function loadAll() {
            console.log("loadAll favoritesUsercontroller");

            FavouriteAlbum.favoriteByAlbumlike(function(result) {
                vm.salbums = result;
                vm.searchQuery = null;
                console.dir(vm.salbums);

            });
        }

        vm.likeDislike=function(){
            if(vm.likeUpDown=="s"){
                vm.favouriteAlbum.liked=false;
                vm.likeUpDown="r";
            }else{
                vm.favouriteAlbum.liked=true;
                vm.likeUpDown="s";
            }

            save();
            function save () {
                vm.isSaving = true;

                if(vm.favouriteAlbum.id){
                    // console.log("RA_ID: "+vm.favouriteAlbum.id);
                    vm.favouriteAlbum.album=vm.albumActual;
                    FavouriteAlbum.update(vm.favouriteAlbum, onSaveSuccess, onSaveError);
                } else {
                    vm.favouriteAlbum.album=vm.albumActual;
                    FavouriteAlbum.save(vm.favouriteAlbum, onSaveSuccess, onSaveError);
                }
            }
            function onSaveSuccess (result) {
                // console.log("SUCCESSS");
                $scope.$emit('dubionApp:favouriteAlbumUpdate', result);
                vm.isSaving = false;
            }

            function onSaveError () {
                // console.log("EERROR");
                vm.isSaving = false;
            }

        }




        function getFavoriteAlbum(id) {
            FavouriteAlbum.favoriteByAlbum({id : id}, function (data){

                vm.favouriteAlbum = data;
                if(vm.favouriteAlbum.liked){
                    vm.likeUpDown="s";
                }else{
                    vm.likeUpDown="r";
                }


            }, function(data){


            });

        }


        $('#input-1').on('rating:change', function(event, value, caption) {
            save();
            function save () {


                vm.isSaving = true;

                if(vm.ratingAlbum.id){

                    vm.ratingAlbum.album=vm.albumActual;
                    vm.ratingAlbum.rating=value;
                    RatingAlbum.update(vm.ratingAlbum, onSaveSuccess, onSaveError);
                } else {

                    vm.ratingAlbum={album:null,user:null,rating:null,date:null,id:null}

                    vm.ratingAlbum.album=vm.albumActual;
                    vm.ratingAlbum.rating=value;
                    RatingAlbum.save(vm.ratingAlbum, onSaveSuccess, onSaveError);
                }
            }

            function onSaveSuccess (result) {

                $scope.$emit('dubionApp:ratingAlbumUpdate', result);
                vm.isSaving = false;
            }

            function onSaveError () {
                vm.isSaving = false;
            }
        });




        function getRatingAlbum(id) {
            RatingAlbum.ratingByAlbum({id : id}, function (data){

                vm.ratingAlbum = data;
                $("#input-1").val(vm.ratingAlbum.rating);
                // with plugin options
                $("#input-1").rating({min:1, max:10, step:2, size:'xs'});

            }, function(data){

                // console.log("error??");
                vm.ratingAlbum= 0;
                $("#input-1").rating({min:1, max:10, step:2, size:'xs'});

            });

        }

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
