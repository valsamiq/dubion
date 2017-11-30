(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('FavouriteSongDialogController', FavouriteSongDialogController);

    FavouriteSongDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FavouriteSong', 'User', 'Song'];

    function FavouriteSongDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FavouriteSong, User, Song) {
        var vm = this;

        vm.favouriteSong = entity;
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
            if (vm.favouriteSong.id !== null) {
                FavouriteSong.update(vm.favouriteSong, onSaveSuccess, onSaveError);
            } else {
                FavouriteSong.save(vm.favouriteSong, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:favouriteSongUpdate', result);
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
