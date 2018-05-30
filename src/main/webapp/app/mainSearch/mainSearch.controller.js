(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('mainSearchController', mainSearchController);

    mainSearchController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$stateParams','Song','Album','Artist','Band','MainSearch'];

    function mainSearchController ($scope, Principal, LoginService, $state, $stateParams, Song, Album, Artist, Band, MainSearch) {
        var vm = this;
        vm.AlbumByName = AlbumByName;
        vm.AlbumBySong = AlbumBySong;
        vm.ArtistByName = ArtistByName;
        vm.optionSearch = null;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.name = null;
        vm.albums = [];
        vm.songs = [];
        vm.artists = [];
        vm.loading = false;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });
        // ============SearchByTrack==========
        vm.searchTrack = function(){
            vm.loading=true;
            vm.optionSearch="track";
            console.log("Sarching Album by Track");
            AlbumBySong();
            ///api/songs/search/:name
        }
        vm.goToSong = function(name){
            vm.albums = [];
            console.log(name);
        }
        // ============SearchByAlbum==========
        vm.searchAlbum=function(){
            vm.loading=true;
            vm.optionSearch="album";
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
            vm.optionSearch="band";
            console.log("Searching by Artist!");
            ArtistByName();
            //Plan B:
            //ArtistByNameLocal();
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
        function AlbumBySong(){
            MainSearch.queryAlbumBySong({name : vm.name}, function (data) {
                vm.albums = data;
                vm.loading = false;
                //AlbumByNameLocal();
            });
        }
        function AlbumByNameLocal(){
            MainSearch.queryAlbumByNameLocal({name : vm.name}), function (data){
                vm.albums = data;
                vm.loading = false;
            }
        }
        function AlbumByName(){
            MainSearch.queryAlbumByName({name : vm.name}, function (data) {
                vm.albums = data;
                vm.loading = false;
            });
        }
        function ArtistByName(){
            MainSearch.queryArtistByName({name : vm.name}, function (data) {
                ArtistByNameLocal();
            });
        }
        function ArtistByNameLocal() {
            MainSearch.queryArtistByNameLocal({name : vm.name}, function (data){
                vm.artists = data;
                vm.loading = false;
            });
        }
    }
})();
