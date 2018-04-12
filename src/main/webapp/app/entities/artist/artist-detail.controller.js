(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('ArtistDetailController', ArtistDetailController);

    ArtistDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Artist', 'Band', 'RatingArtist'];

    function ArtistDetailController($scope, $rootScope, $stateParams, previousState, entity, Artist, Band, RatingArtist) {
        var vm = this;

        vm.artist = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:artistUpdate', function(event, result) {
            vm.artist = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
