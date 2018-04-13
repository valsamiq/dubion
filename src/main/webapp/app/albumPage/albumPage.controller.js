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

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.favouriteAlbum={};
        vm.favouriteAlbum.liked=false;

        vm.ratingAlbums = [];
        vm.ratingAlbum=0 ;

        Album.get({id : $stateParams.id}, function(data) {

            vm.albumActual = data;
            getRatingAlbum(data.id);

            console.log(data);
            console.log("ha");
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



        $('#input-1').on('rating:change', function(event, value, caption) {
            console.log("inside")
            console.log(value);
            console.log(caption);
            save();
            function save () {
                console.log("hu");
                vm.ratingAlbum.album=vm.albumActual;
                vm.ratingAlbum.rating=value;

                vm.isSaving = true;
                if (vm.ratingAlbum.id !== null) {
                    RatingAlbum.update(vm.ratingAlbum, onSaveSuccess, onSaveError);
                } else {
                    RatingAlbum.save(vm.ratingAlbum, onSaveSuccess, onSaveError);
                }
            }

            function onSaveSuccess (result) {
                $scope.$emit('dubionApp:ratingAlbumUpdate', result);
                $uibModalInstance.close(result);
                vm.isSaving = false;
            }

            function onSaveError () {
                vm.isSaving = false;
            }
        });




        function getRatingAlbum(id) {
            RatingAlbum.ratingByAlbum({id : id}, function (data){

                console.log(data.rating);
                console.log("he");
                vm.ratingAlbum = data.rating;
                console.log(vm.ratingAlbum);
                $("#input-1").val(vm.ratingAlbum);
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


        FavouriteAlbum.get()
        /*Volem saber si l'usuari actual amb el id del album actual te like o no*/
         FavouriteAlbum.get({id: 1},   function (data) {
         //FavouriteAlbum.get({idUser:vm.account.id, idAlbum: vm.albumActual.id},   function (data) {
             console.log(data);
            // vm.favouriteAlbum=data;
             // console.log(vm.favouriteAlbum);∫


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
