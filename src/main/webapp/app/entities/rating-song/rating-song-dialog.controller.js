(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingSongDialogController', RatingSongDialogController);

    RatingSongDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RatingSong', 'User', 'Song'];

    function RatingSongDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RatingSong, User, Song) {
        var vm = this;

        vm.ratingSong = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.users = User.query();
        vm.songs = Song.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.ratingSong.id !== null) {
                RatingSong.update(vm.ratingSong, onSaveSuccess, onSaveError);
            } else {
                RatingSong.save(vm.ratingSong, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:ratingSongUpdate', result);
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
