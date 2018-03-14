(function(){
    'use strict';

    angular
        .module('dubionApp')
        .controller('bandPageController', BandPageController);

    BandPageController.$inject = ['$scope', 'Principal', 'LoginService', '$state','Band','$stateParams'];

    function BandPageController ($scope, Principal, LoginService, $state,Band,$stateParams) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        Band.get({id :1}, function(data) {
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
    }
})();
