(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('GammaDetailController', GammaDetailController);

    GammaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Gamma', 'Album'];

    function GammaDetailController($scope, $rootScope, $stateParams, previousState, entity, Gamma, Album) {
        var vm = this;

        vm.gamma = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:gammaUpdate', function(event, result) {
            vm.gamma = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
