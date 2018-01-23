(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BetaDetailController', BetaDetailController);

    BetaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Beta'];

    function BetaDetailController($scope, $rootScope, $stateParams, previousState, entity, Beta) {
        var vm = this;

        vm.beta = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:betaUpdate', function(event, result) {
            vm.beta = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
