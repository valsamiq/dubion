(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('albumPageController', albumPageController);

    albumPageController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Album','$stateParams'];

    function albumPageController ($scope, Principal, LoginService, $state, Album,$stateParams) {

        var vm = this;
        vm.songByName = songByName;


        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        Album.get({id : $stateParams.id}, function(data) {
            vm.albumActual = data;
            vm.albumName = vm.albumActual.name;
            songByName();
            vm.imatgeAlbum = '<img  src="data:image/jpg;base64, '+vm.albumActual.photo+'" />';
            $scope.apply();
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
            });
        }


    }
})();
