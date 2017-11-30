(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('SexDialogController', SexDialogController);

    SexDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Sex', 'UserExt'];

    function SexDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Sex, UserExt) {
        var vm = this;

        vm.sex = entity;
        vm.clear = clear;
        vm.save = save;
        vm.userexts = UserExt.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.sex.id !== null) {
                Sex.update(vm.sex, onSaveSuccess, onSaveError);
            } else {
                Sex.save(vm.sex, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:sexUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
