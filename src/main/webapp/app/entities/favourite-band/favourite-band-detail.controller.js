(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('FavouriteBandDetailController', FavouriteBandDetailController);

    FavouriteBandDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FavouriteBand', 'User', 'Band'];

    function FavouriteBandDetailController($scope, $rootScope, $stateParams, previousState, entity, FavouriteBand, User, Band) {
        var vm = this;

        vm.favouriteBand = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:favouriteBandUpdate', function(event, result) {
            vm.favouriteBand = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
