(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BetaDialogController', BetaDialogController);

    BetaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Beta'];

    function BetaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Beta) {
        var vm = this;

        vm.beta = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.beta.id !== null) {
                Beta.update(vm.beta, onSaveSuccess, onSaveError);
            } else {
                Beta.save(vm.beta, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:betaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
