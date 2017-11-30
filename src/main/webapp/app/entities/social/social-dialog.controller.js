(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('SocialDialogController', SocialDialogController);

    SocialDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Social'];

    function SocialDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Social) {
        var vm = this;

        vm.social = entity;
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
            if (vm.social.id !== null) {
                Social.update(vm.social, onSaveSuccess, onSaveError);
            } else {
                Social.save(vm.social, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:socialUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
