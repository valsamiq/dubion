(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('mainSearchController', mainSearchController);

    mainSearchController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$stateParams','Album','MainSearch'];

    function mainSearchController ($scope, Principal, LoginService, $state, $stateParams, Album, MainSearch) {
    console.log("Contorlllerrr!!");
        var vm = this;
        vm.byName = byName;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.name;
        vm.albums=[];
        vm.loading=false;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();

        });

        vm.searchTrack = function(){
            console.log("searchTrack!");
            /*MainSearch.query(function(result){
                console.log(result)
                vm.albums=result;
            });*/

        }

        vm.searchAlbum=function(){
            vm.loading=true;
            console.log("searchAlbum");
            byName();
            $('#SearchModalAlbum').modal({})
        }

        vm.searchArtist=function(){
            console.log("searchArtist");
        }
        vm.artists = [];
        vm.goToAlbum= function(name) {
            vm.albums = [];
            console.log(name);

            //window.location.href='#/albumPage/'+id;
        }
        // Album.get({id : $stateParams.id}, function(data) {
        //     vm.albumActual = data;
        // });
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
                vm.loading=false;
            });

        }
    }
})();
