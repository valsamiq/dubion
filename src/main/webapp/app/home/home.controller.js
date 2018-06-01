(function () {
    'use strict';

    angular
        .module('dubionApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'Album', 'Band', 'FavouriteAlbum', 'FavouriteBand'];

    function HomeController($scope, Principal, LoginService, $state, Album, Band, FavouriteAlbum, FavouriteBand) {
        var vm = this;

        vm.top10Slick = {};
        vm.lasReleasesSlick = {};
        vm.userFavSlick = {};
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function (account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
                if(vm.account==null){
                  //  console.log("hola que tal");
                }

                loadAll();
            });
        }

        function register() {
            $state.go('register');
        }


        vm.salbums = [];
        vm.salbumstop = [];
        vm.sbands = [];


        function fullAlbum() {
            /*Album.query(function(result) {
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
                        //  afterChange: function (event, slick, currentSlide, nextSlide) {}
                    }
                };
                vm.albumsLoaded=true;
            });*/
            FavouriteAlbum.favoriteByAlbumlike(function (result) {
                vm.salbums = result;
                console.log(vm.salbums);
                vm.searchQuery = null;
                if (vm.salbums.length == 0) {
                    Album.query(function (result) {
                        vm.salbums = result;

                    });
                }
                    // console.dir(vm.salbums);
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
                        //  afterChange: function (event, slick, currentSlide, nextSlide) {}
                    }
                };

                vm.albumsLoaded = true;

            });
            // FavouriteBand.favoriteByBandlike(function (result) {
            //     vm.sbands = result;
            //     vm.searchQuery = null;
            //     if (vm.sbands.length == 0) {
            //         Band.query(function (result) {
            //             vm.sbands = result;
            //             vm.slickConfig = {
            //                 enabled: true,
            //                 autoplay: true,
            //                 draggable: false,
            //                 autoplaySpeed: 2000,
            //                 slidesToShow: 5,
            //                 slidesToScroll: 1,
            //                 responsive: [
            //                     {
            //                         breakpoint: 1200,
            //                         settings: {
            //                             dots: false,
            //                             slidesToShow: 4,
            //                             slidesToScroll: 1
            //                         }
            //                     },
            //                     {
            //                         breakpoint: 1024,
            //                         settings: {
            //                             dots: false,
            //                             slidesToShow: 3,
            //                             slidesToScroll: 1
            //                         }
            //                     },
            //                     {
            //                         breakpoint: 600,
            //                         settings: {
            //                             slidesToShow: 2,
            //                             slidesToScroll: 1
            //                         }
            //                     },
            //                     {
            //                         breakpoint: 480,
            //                         settings: {
            //                             arrows: false,
            //                             slidesToShow: 1,
            //                             slidesToScroll: 1
            //
            //                         }
            //                     }
            //
            //                 ],
            //                 method: {},
            //                 event: {
            //                     //beforeChange: function (event, slick, currentSlide, nextSlide){},
            //                     //  afterChange: function (event, slick, currentSlide, nextSlide) {              }
            //                 }
            //             };
            //
            //         });
            //
            //
            //     }
            //
            //     vm.slickConfig = {
            //         enabled: true,
            //         autoplay: true,
            //         draggable: false,
            //         autoplaySpeed: 2000,
            //         slidesToShow: 5,
            //         slidesToScroll: 1,
            //         responsive: [
            //             {
            //                 breakpoint: 1200,
            //                 settings: {
            //                     dots: false,
            //                     slidesToShow: 4,
            //                     slidesToScroll: 1
            //                 }
            //             },
            //             {
            //                 breakpoint: 1024,
            //                 settings: {
            //                     dots: false,
            //                     slidesToShow: 3,
            //                     slidesToScroll: 1
            //                 }
            //             },
            //             {
            //                 breakpoint: 600,
            //                 settings: {
            //                     slidesToShow: 2,
            //                     slidesToScroll: 1
            //                 }
            //             },
            //             {
            //                 breakpoint: 480,
            //                 settings: {
            //                     arrows: false,
            //                     slidesToShow: 1,
            //                     slidesToScroll: 1
            //
            //                 }
            //             }
            //
            //         ],
            //         method: {},
            //         event: {
            //             //beforeChange: function (event, slick, currentSlide, nextSlide){},
            //             //  afterChange: function (event, slick, currentSlide, nextSlide) {              }
            //         }
            //     };
            //     vm.albumsLoaded3 = true;
            // });
            Band.query(function(result) {
                vm.sbands = result;
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
                vm.albumsLoaded3=true;
            });
        }


        function loadAll() {
            vm.albumsLoaded = false;
            vm.albumsLoaded2 = false;
            vm.albumsLoaded3 = false;


            Album.querytop(function (result) {
                vm.salbumstop = result;
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
                vm.albumsLoaded2 = true;
                fullAlbum();
            });
        }

    }
})();
