(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('SongDetailController', SongDetailController);

    SongDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Song', 'Album', 'RatingSong', 'FavouriteSong'];

    function SongDetailController($scope, $rootScope, $stateParams, previousState, entity, Song, Album, RatingSong, FavouriteSong) {
        var vm = this;

        vm.song = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:songUpdate', function(event, result) {
            vm.song = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
