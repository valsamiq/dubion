(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BandDetailController', BandDetailController);

    BandDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Band', 'Country', 'Label', 'Social', 'Genre', 'RatingBand', 'FavouriteBand', 'Artist'];

    function BandDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Band, Country, Label, Social, Genre, RatingBand, FavouriteBand, Artist) {
        var vm = this;

        vm.band = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('dubionApp:bandUpdate', function(event, result) {
            vm.band = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
