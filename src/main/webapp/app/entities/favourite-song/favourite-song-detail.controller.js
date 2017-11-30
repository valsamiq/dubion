(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('FavouriteSongDetailController', FavouriteSongDetailController);

    FavouriteSongDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FavouriteSong', 'User', 'Song'];

    function FavouriteSongDetailController($scope, $rootScope, $stateParams, previousState, entity, FavouriteSong, User, Song) {
        var vm = this;

        vm.favouriteSong = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:favouriteSongUpdate', function(event, result) {
            vm.favouriteSong = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
