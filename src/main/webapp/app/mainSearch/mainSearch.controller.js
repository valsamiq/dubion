(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('mainSearchController', mainSearchController);

    mainSearchController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$stateParams','Song','Album','MainSearch'];

    function mainSearchController ($scope, Principal, LoginService, $state, $stateParams, Song, Album, MainSearch) {
        var vm = this;
        vm.AlbumByName = AlbumByName;
        vm.SongByName = SongByName;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.name;
        vm.albums=[];
        vm.songs=[];
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
            //$('#SearchModalAlbum').modal({})
        }
        vm.goToAlbum= function(name) {
            vm.albums = [];
            console.log(name);
            window.location.href='#/albumPage/'+id;
        }
        // ============SearchByArtist==========
        vm.searchArtist=function(){
            console.log("Searching By Artist!");
        }
        vm.artists = [];

        //window.location.href='#/albumPage/'+id;
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
        function SongByName(){
            Song.queryByName({name : vm.name}, function (data) {
                vm.songs = data;
                vm.loading = false;
            })
        }

        function AlbumByName(){
            Album.queryByName({name : vm.name}, function (data) {
                vm.albums = data;
                vm.loading=false;
            });
        }
    }
})();
