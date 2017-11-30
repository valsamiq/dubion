(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingBandDialogController', RatingBandDialogController);

    RatingBandDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RatingBand', 'User', 'Band'];

    function RatingBandDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RatingBand, User, Band) {
        var vm = this;

        vm.ratingBand = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.users = User.query();
        vm.bands = Band.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.ratingBand.id !== null) {
                RatingBand.update(vm.ratingBand, onSaveSuccess, onSaveError);
            } else {
                RatingBand.save(vm.ratingBand, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:ratingBandUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
