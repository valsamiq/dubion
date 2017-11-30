(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('SocialDetailController', SocialDetailController);

    SocialDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Social'];

    function SocialDetailController($scope, $rootScope, $stateParams, previousState, entity, Social) {
        var vm = this;

        vm.social = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dubionApp:socialUpdate', function(event, result) {
            vm.social = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
