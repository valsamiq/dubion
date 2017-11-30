(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('ArtistDetailController', ArtistDetailController);

    ArtistDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Artist', 'Band', 'Instrument', 'RatingArtist'];

    function ArtistDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Artist, Band, Instrument, RatingArtist) {
        var vm = this;

        vm.artist = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('dubionApp:artistUpdate', function(event, result) {
            vm.artist = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
