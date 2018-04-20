(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('mainSearchController', mainSearchController);

    mainSearchController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$stateParams'];

    function mainSearchController ($scope, Principal, LoginService, $state) {

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

            });
        }
    }
})();
