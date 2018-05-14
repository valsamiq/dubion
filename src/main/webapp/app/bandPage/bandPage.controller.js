(function(){
    'use strict';

    angular
        .module('dubionApp')
        .controller('bandPageController', BandPageController);

    BandPageController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Band','$stateParams','FavouriteBand','RatingBand'];

    function BandPageController ($scope, Principal, LoginService, $state,Band,$stateParams,FavouriteBand,RatingBand) {
        var vm = this;
        vm.bandActual;
        vm.likeUpDown="r";
        vm.favouriteBand={};
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });
        vm.bandId;
        vm.ratingBands = [];
        vm.ratingBand= {};

        Band.get({id : $stateParams.id}, function(data) {
            vm.bandActual = data;
            getRatingBand(data.id);
            getFavoriteBand(data.id);
            vm.bandName = vm.bandActual.name;
            vm.bandId = vm.bandActual.id;
            vm.imatgeBand = '<img  src="data:image/jpg;base64, '+vm.bandActual.photo+'" />';

        });

        vm.likeDislike=function(){
            if(vm.likeUpDown=="s"){
                vm.favouriteBand.liked=false;
                vm.likeUpDown="r";
            }else{
                vm.favouriteBand.liked=true;
                vm.likeUpDown="s";
            }

            save();
            function save () {
                vm.isSaving = true;

                if(vm.favouriteBand.id){
                    vm.favouriteBand.band=vm.bandActual;
                    FavouriteBand.update(vm.favouriteBand, onSaveSuccess, onSaveError);
                } else {
                    vm.favouriteBand.band=vm.bandActual;
                    FavouriteBand.save(vm.favouriteBand, onSaveSuccess, onSaveError);
                }
            }
            function onSaveSuccess (result) {
                $scope.$emit('dubionApp:favouriteBandUpdate', result);
                vm.isSaving = false;
            }

            function onSaveError () {
                vm.isSaving = false;
            }

        }
        function getFavoriteBand(id) {
            FavouriteBand.favoriteByBand({id : id}, function (data){
                vm.favouriteBand = data;
                if(vm.favouriteBand.liked){
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
                if(vm.ratingBand.id){
                    vm.ratingBand.band=vm.bandActual;
                    vm.ratingBand.rating=value;
                    RatingBand.update(vm.ratingBand, onSaveSuccess, onSaveError);
                } else {
                    vm.ratingBand={band:null,user:null,rating:null,date:null,id:null}
                    vm.ratingBand.band=vm.bandActual;
                    vm.ratingBand.rating=value;
                    RatingBand.save(vm.ratingBand, onSaveSuccess, onSaveError);
                }
            }
            function onSaveSuccess (result) {
                $scope.$emit('dubionApp:ratingBandUpdate', result);
                vm.isSaving = false;
            }

            function onSaveError () {
                vm.isSaving = false;
            }
        });
        function getRatingBand(id) {
            RatingBand.ratingByBand({id : id}, function (data){
                vm.ratingBand = data;
                $("#input-1").val(vm.ratingBand.rating);
                $("#input-1").rating({min:1, max:10, step:2, size:'xs'});

            }, function(data){
                vm.ratingBand= 0;
                $("#input-1").rating({min:1, max:10, step:2, size:'xs'});
            });

        }

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
        vm.sbands=[];


        loadAll();

        function loadAll() {




            Band.query(function(result) {
                vm.bandsLoaded=false;

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
                vm.bandsLoaded=true;
            });
        }


    }
})();
