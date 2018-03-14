(function(){
    'use strict';

    angular
        .module('dubionApp')
        .controller('artistPageController', ArtistPageController);

    ArtistPageController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Artist','$stateParams'];

    function ArtistPageController ($scope, Principal, LoginService, $state, Artist, $stateParams) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        Artist.get({id :1}, function(data) {
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
    }
})();
