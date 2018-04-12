(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('bandSearchController', bandSearchController);

    bandSearchController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Band','$stateParams'];

    function bandSearchController ($scope, Principal, LoginService, $state, Band,$stateParams) {

        var vm = this;
        vm.byName = byName;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.name;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        Band.get({id : $stateParams.id}, function(data) {
            vm.bandActual = data;
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
            Band.queryByName({name : vm.name}, function (data) {
                vm.bands = data;
            });
        }

       // vm.images = ['/content/images/Sia%20Cover8.jpg', '/content/images/Sia%20Cover9.jpg', '/content/images/Sia%20Cover7.jpg'];
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
