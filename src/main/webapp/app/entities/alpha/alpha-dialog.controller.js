(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('AlphaDialogController', AlphaDialogController);

    AlphaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Alpha', 'Beta'];

    function AlphaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Alpha, Beta) {
        var vm = this;

        vm.alpha = entity;
        vm.clear = clear;
        vm.save = save;
        vm.betas = Beta.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.alpha.id !== null) {
                Alpha.update(vm.alpha, onSaveSuccess, onSaveError);
            } else {
                Alpha.save(vm.alpha, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:alphaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
