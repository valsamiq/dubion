(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingArtistDetailController', RatingArtistDetailController);

    RatingArtistDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RatingArtist', 'User', 'Artist'];

    function RatingArtistDetailController($scope, $rootScope, $stateParams, previousState, entity, RatingArtist, User, Artist) {
        var vm = this;

        vm.ratingArtist = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:ratingArtistUpdate', function(event, result) {
            vm.ratingArtist = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
