(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('mainSearchController', mainSearchController);

    mainSearchController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$stateParams','Song','Album','Artist','MainSearch'];

    function mainSearchController ($scope, Principal, LoginService, $state, $stateParams, Song, Album, Artist, MainSearch) {
        var vm = this;
        vm.AlbumByName = AlbumByName;
        vm.SongByName = SongByName;
        vm.ArtistByName = ArtistByName;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.name;
        vm.albums=[];
        vm.songs=[];
        vm.artists=[];
        vm.loading=false;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });
        // ============SearchByTrack==========
        vm.searchTrack = function(){
            vm.loading=true;
            console.log("Sarching by Track");
            SongByName();
            /*MainSearch.query(function(result){
                console.log(result)
                vm.albums=result;
            });*/
        }
        vm.goToSong = function(name){
            vm.songs = [];
            console.log(name);
        }
        // ============SearchByAlbum==========
        vm.searchAlbum=function(){
            vm.loading=true;
            console.log("Searhing by Album");
            AlbumByName();
        }
        vm.goToAlbum= function(name) {
            vm.albums = [];
            console.log(name);
        }
        // ============SearchByArtist==========
        vm.searchArtist=function(){
            vm.loading=true;
            console.log("Searching by Artist!");
            ArtistByName();
        }
        vm.goToArtist= function(name) {
            vm.artists = [];
            console.log(name);
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
        function SongByName(){
            Song.queryByName({name : vm.name}, function (data) {
                vm.songs = data;
                vm.loading = false;
            });
        }
        function AlbumByName(){
            MainSearch.queryAlbumByName({name : vm.name}, function (data) {
                vm.albums = data;
                vm.loading=false;
            });
        }
        function ArtistByName(){
            MainSearch.queryArtistByName({name : vm.name}, function (data) {
                vm.band=data;
                vm.loading=false;
            });
        }
    }
})();
