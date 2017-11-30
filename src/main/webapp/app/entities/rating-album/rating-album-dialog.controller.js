(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingAlbumDialogController', RatingAlbumDialogController);

    RatingAlbumDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RatingAlbum', 'User', 'Album'];

    function RatingAlbumDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RatingAlbum, User, Album) {
        var vm = this;

        vm.ratingAlbum = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.users = User.query();
        vm.albums = Album.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.ratingAlbum.id !== null) {
                RatingAlbum.update(vm.ratingAlbum, onSaveSuccess, onSaveError);
            } else {
                RatingAlbum.save(vm.ratingAlbum, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:ratingAlbumUpdate', result);
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
