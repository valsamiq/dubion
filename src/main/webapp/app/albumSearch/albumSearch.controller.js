(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('albumSearchController', albumSearchController);

    albumSearchController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Album','$stateParams'];

    function albumSearchController ($scope, Principal, LoginService, $state, Album,$stateParams) {

        var vm = this;
        vm.byName = byName;
    //vm.saludo="hola";,,
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.name;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        Album.get({id : $stateParams.id}, function(data) {
            vm.albumActual = data;
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

        function byName(){
            Album.queryByName({name : vm.name}, function (data) {
                vm.albums = data;

            });
        }

       // vm.images = ['/content/images/Sia%20Cover8.jpg', '/content/images/Sia%20Cover9.jpg', '/content/images/Sia%20Cover7.jpg'];
        vm.salbums=[];

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
