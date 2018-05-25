(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('profileController', profileController);

    profileController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'ProfileServiceK'];

    function profileController ($scope, Principal, LoginService, $state, ProfileServiceK) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;

        console.log("=====Controller de Profile=====")

        $scope.$on('authenticationSuccess', function() {
            getAccount();
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

        vm.userData = null;
        ProfileServiceK.currentUser(function(data) {
            vm.userData = data;
            console.log(data);
        });

        vm.userDataExt=null;
        ProfileServiceK.currentUserExt(function(data) {
            vm.userDataExt = data;
            console.log(data);
        });
    }
})();
