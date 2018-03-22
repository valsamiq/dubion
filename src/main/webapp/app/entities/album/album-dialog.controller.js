(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('AlbumDialogController', AlbumDialogController);

    AlbumDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Album', 'Band', 'Genre', 'RatingAlbum', 'FavouriteAlbum', 'Song'];

    function AlbumDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Album, Band, Genre, RatingAlbum, FavouriteAlbum, Song) {
        var vm = this;

        vm.album = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.bands = Band.query();
        vm.genres = Genre.query();
        vm.ratingalbums = RatingAlbum.query();
        vm.favouritealbums = FavouriteAlbum.query();
        vm.songs = Song.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.album.id !== null) {
                Album.update(vm.album, onSaveSuccess, onSaveError);
            } else {
                Album.save(vm.album, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:albumUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.releaseDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
