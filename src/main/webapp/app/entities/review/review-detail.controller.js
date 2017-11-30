(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('ReviewDetailController', ReviewDetailController);

    ReviewDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Review', 'User', 'Band', 'Album'];

    function ReviewDetailController($scope, $rootScope, $stateParams, previousState, entity, Review, User, Band, Album) {
        var vm = this;

        vm.review = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:reviewUpdate', function(event, result) {
            vm.review = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
