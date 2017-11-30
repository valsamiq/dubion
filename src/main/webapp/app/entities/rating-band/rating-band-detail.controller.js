(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingBandDetailController', RatingBandDetailController);

    RatingBandDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RatingBand', 'User', 'Band'];

    function RatingBandDetailController($scope, $rootScope, $stateParams, previousState, entity, RatingBand, User, Band) {
        var vm = this;

        vm.ratingBand = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:ratingBandUpdate', function(event, result) {
            vm.ratingBand = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
