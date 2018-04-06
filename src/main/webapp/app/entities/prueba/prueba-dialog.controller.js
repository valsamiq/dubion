(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('PruebaDialogController', PruebaDialogController);

    PruebaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Prueba'];

    function PruebaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Prueba) {
        var vm = this;

        vm.prueba = entity;
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
            if (vm.prueba.id !== null) {
                Prueba.update(vm.prueba, onSaveSuccess, onSaveError);
            } else {
                Prueba.save(vm.prueba, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:pruebaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
