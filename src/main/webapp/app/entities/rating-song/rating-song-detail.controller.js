(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingSongDetailController', RatingSongDetailController);

    RatingSongDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RatingSong', 'User', 'Song'];

    function RatingSongDetailController($scope, $rootScope, $stateParams, previousState, entity, RatingSong, User, Song) {
        var vm = this;

        vm.ratingSong = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:ratingSongUpdate', function(event, result) {
            vm.ratingSong = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
