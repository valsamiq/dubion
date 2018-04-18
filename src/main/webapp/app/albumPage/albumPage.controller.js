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

        vm.likeUpDown="up";
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.favouriteAlbum={};
        //vm.favouriteAlbum.liked=true;

        vm.ratingAlbums = [];
        vm.ratingAlbum= {};

        Album.get({id : $stateParams.id}, function(data) {

            vm.albumActual = data;
            getRatingAlbum(data.id);
            getFavoriteAlbum(data.id);

            /*console.log( "Info Album"+vm.albumActual);
            console.log(  "Info Account"+vm.account)
            vm.favouriteAlbum.album = vm.albumActual.id;
            vm.favouriteAlbum.user=vm.account.id;
*/

            vm.albumName = vm.albumActual.name;
            songByName();
            vm.imatgeAlbum = '<img  src="data:image/jpg;base64, '+vm.albumActual.photo+'" />';
            $scope.apply();

        });
        vm.likeDislike=function(){
            if(vm.likeUpDown=="up"){
                vm.favouriteAlbum.liked=false;
                vm.likeUpDown="down";
            }else{
                vm.favouriteAlbum.liked=true;
                vm.likeUpDown="up";
            }

            save();
            function save () {
                vm.isSaving = true;

                if(vm.favouriteAlbum.id){
                    console.log("RA_ID: "+vm.favouriteAlbum.id);
                    vm.favouriteAlbum.album=vm.albumActual;
                    FavouriteAlbum.update(vm.favouriteAlbum, onSaveSuccess, onSaveError);
                } else {
                    vm.favouriteAlbum.album=vm.albumActual;
                    FavouriteAlbum.save(vm.favouriteAlbum, onSaveSuccess, onSaveError);
                }
            }
            function onSaveSuccess (result) {
                console.log("SUCCESSS");
                $scope.$emit('dubionApp:favouriteAlbumUpdate', result);
                $uibModalInstance.close(result);
                vm.isSaving = false;
            }

            function onSaveError () {
                console.log("EERROR");
                vm.isSaving = false;
            }

        }




        function getFavoriteAlbum(id) {
           FavouriteAlbum.favoriteByAlbum({id : id}, function (data){
            // FavouriteAlbum.get({id : id},   function (data) {
                console.log("hola");
                console.dir(data);
                vm.favouriteAlbum = data;
                if(vm.favouriteAlbum.liked){
                    vm.likeUpDown="up";
                }else{
                    vm.likeUpDown="down";
                }


            }, function(data){
                console.log("error??");
              // vm.favouriteAlbum.liked = false;

            });

        }


        $('#input-1').on('rating:change', function(event, value, caption) {
            save();
            function save () {


                vm.isSaving = true;
                console.log("RA: "+vm.ratingAlbum);

                console.log("AlbumActual:");
                console.dir(vm.albumActual);


                console.log("Rating Album:");
                console.dir(vm.ratingAlbum);


            //    console.log("RA_ID: "+vm.ratingAlbum.id);
                if(vm.ratingAlbum.id){
                    console.log("RA_ID: "+vm.ratingAlbum.id);
              //  if (!vm.ratingAlbum.id || vm.ratingAlbum.id !== null ) {
                    vm.ratingAlbum.album=vm.albumActual;
                    vm.ratingAlbum.rating=value;
                    RatingAlbum.update(vm.ratingAlbum, onSaveSuccess, onSaveError);
                } else {
                    //vm.ratingAlbumm == undefined
                    vm.ratingAlbum={album:null,user:null,rating:null,date:null,id:null}
                    //console.log("RA_ID Undefined: "+vm.ratingAlbum.id);
                    vm.ratingAlbum.album=vm.albumActual;
                    vm.ratingAlbum.rating=value;
                    RatingAlbum.save(vm.ratingAlbum, onSaveSuccess, onSaveError);
                }
            }

            function onSaveSuccess (result) {
                console.log("SUCCESSS");
                $scope.$emit('dubionApp:ratingAlbumUpdate', result);
                $uibModalInstance.close(result);
                vm.isSaving = false;
            }

            function onSaveError () {
                console.log("EERROR");
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

                console.log("error??");
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
            Album.getsongsByName({name : vm.albumName}, function (data) {
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
