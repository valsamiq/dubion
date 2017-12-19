(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BandPruebaDialogController', BandPruebaDialogController);

    BandPruebaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'BandPrueba'];

    function BandPruebaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, BandPrueba) {
        var vm = this;

        vm.bandPrueba = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.bandPrueba.id !== null) {
                BandPrueba.update(vm.bandPrueba, onSaveSuccess, onSaveError);
            } else {
                BandPrueba.save(vm.bandPrueba, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:bandPruebaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.birthdate = false;

        vm.setPhoto = function ($file, bandPrueba) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        bandPrueba.photo = base64Data;
                        bandPrueba.photoContentType = $file.type;
                    });
                });
            }
        };

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
