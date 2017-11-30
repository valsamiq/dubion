(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('FavouriteAlbumDialogController', FavouriteAlbumDialogController);

    FavouriteAlbumDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FavouriteAlbum', 'User', 'Album'];

    function FavouriteAlbumDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FavouriteAlbum, User, Album) {
        var vm = this;

        vm.favouriteAlbum = entity;
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
            if (vm.favouriteAlbum.id !== null) {
                FavouriteAlbum.update(vm.favouriteAlbum, onSaveSuccess, onSaveError);
            } else {
                FavouriteAlbum.save(vm.favouriteAlbum, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:favouriteAlbumUpdate', result);
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
