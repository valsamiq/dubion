(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('FavouriteAlbumDetailController', FavouriteAlbumDetailController);

    FavouriteAlbumDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FavouriteAlbum', 'User', 'Album'];

    function FavouriteAlbumDetailController($scope, $rootScope, $stateParams, previousState, entity, FavouriteAlbum, User, Album) {
        var vm = this;

        vm.favouriteAlbum = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:favouriteAlbumUpdate', function(event, result) {
            vm.favouriteAlbum = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
