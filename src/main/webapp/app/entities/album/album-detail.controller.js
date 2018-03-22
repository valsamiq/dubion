(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('AlbumDetailController', AlbumDetailController);

    AlbumDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Album', 'Band', 'Genre', 'RatingAlbum', 'FavouriteAlbum', 'Song'];

    function AlbumDetailController($scope, $rootScope, $stateParams, previousState, entity, Album, Band, Genre, RatingAlbum, FavouriteAlbum, Song) {
        var vm = this;

        vm.album = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:albumUpdate', function(event, result) {
            vm.album = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
