(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingAlbumDetailController', RatingAlbumDetailController);

    RatingAlbumDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RatingAlbum', 'User', 'Album'];

    function RatingAlbumDetailController($scope, $rootScope, $stateParams, previousState, entity, RatingAlbum, User, Album) {
        var vm = this;

        vm.ratingAlbum = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:ratingAlbumUpdate', function(event, result) {
            vm.ratingAlbum = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
