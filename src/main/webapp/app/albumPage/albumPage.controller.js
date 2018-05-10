(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('albumPageController', albumPageController);

    albumPageController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Album','$stateParams','FavouriteAlbum','RatingAlbum'];

    function albumPageController ($scope, Principal, LoginService, $state, Album,$stateParams,FavouriteAlbum,RatingAlbum) {

        var vm = this;
        vm.songByName = songByName;
        vm.albumActual;

        vm.likeUpDown="r";
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.favouriteAlbum={};
        vm.contador=0;
        //vm.favouriteAlbum.liked=true;

        vm.albumId;
        vm.ratingAlbums = [];
        vm.ratingAlbum= {};

        Album.get({id : $stateParams.id}, function(data) {

            vm.albumActual = data;
            getRatingAlbum(data.id);
            getFavoriteAlbum(data.id);

            vm.albumName = vm.albumActual.name;
            vm.albumId = vm.albumActual.id;
            songByName();
            vm.imatgeAlbum = '<img  src="data:image/jpg;base64, '+vm.albumActual.photo+'" />';
            // $scope.apply();

        });

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
                    vm.favouriteAlbum.album=vm.albumActual;
                    FavouriteAlbum.update(vm.favouriteAlbum, onSaveSuccess, onSaveError);
                } else {
                    vm.favouriteAlbum.album=vm.albumActual;
                    FavouriteAlbum.save(vm.favouriteAlbum, onSaveSuccess, onSaveError);
                }
            }
            function onSaveSuccess (result) {
                $scope.$emit('dubionApp:favouriteAlbumUpdate', result);
                vm.isSaving = false;
            }

            function onSaveError () {
                vm.isSaving = false;
            }

        }

        vm.setSong= function(song) {
            document.getElementById("audioplayer").src = song.url;
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
                $("#input-1").rating({min:1, max:10, step:2, size:'xs'});

            }, function(data){
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
        vm.salbums=[];

        function songByName(){
            Album.getSongsByName({idAlbum : vm.albumId}, function (data) {
                vm.songs = data;
            });
        }


        loadAll();

        function loadAll() {
            vm.albumsLoaded=false;

            Album.query(function(result) {
                vm.salbums = result;
                vm.searchQuery = null;

                vm.slickConfig = {
                    enabled: true,
                    autoplay: true,
                    draggable: false,
                    autoplaySpeed: 2000,
                    slidesToShow: 5,
                    slidesToScroll: 1,
                    responsive: [
                        {
                            breakpoint: 1200,
                            settings: {
                                dots: false,
                                slidesToShow: 4,
                                slidesToScroll: 1
                            }
                        },
                        {
                            breakpoint: 1024,
                            settings: {
                                dots: false,
                                slidesToShow: 3,
                                slidesToScroll: 1
                            }
                        },
                        {
                            breakpoint: 600,
                            settings: {
                                slidesToShow: 2,
                                slidesToScroll: 1
                            }
                        },
                        {
                            breakpoint: 480,
                            settings: {
                                arrows: false,
                                slidesToShow: 1,
                                slidesToScroll: 1

                            }
                        }

                    ],
                    method: {},
                    event: {
                        //beforeChange: function (event, slick, currentSlide, nextSlide){},
                        //  afterChange: function (event, slick, currentSlide, nextSlide) {              }
                    }
                };
                vm.albumsLoaded=true;
            });
        }


    }
})();
