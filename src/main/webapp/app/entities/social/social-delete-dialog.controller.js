(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('SocialDeleteController',SocialDeleteController);

    SocialDeleteController.$inject = ['$uibModalInstance', 'entity', 'Social'];

    function SocialDeleteController($uibModalInstance, entity, Social) {
        var vm = this;

        vm.social = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Social.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
