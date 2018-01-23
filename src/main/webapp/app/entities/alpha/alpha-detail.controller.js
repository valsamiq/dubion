(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('AlphaDetailController', AlphaDetailController);

    AlphaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Alpha', 'Beta'];

    function AlphaDetailController($scope, $rootScope, $stateParams, previousState, entity, Alpha, Beta) {
        var vm = this;

        vm.alpha = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:alphaUpdate', function(event, result) {
            vm.alpha = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
