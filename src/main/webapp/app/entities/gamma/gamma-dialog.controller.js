(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('GammaDialogController', GammaDialogController);

    GammaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Gamma', 'Album'];

    function GammaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Gamma, Album) {
        var vm = this;

        vm.gamma = entity;
        vm.clear = clear;
        vm.save = save;
        vm.albums = Album.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.gamma.id !== null) {
                Gamma.update(vm.gamma, onSaveSuccess, onSaveError);
            } else {
                Gamma.save(vm.gamma, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:gammaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
