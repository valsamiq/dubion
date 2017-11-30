(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('SongDialogController', SongDialogController);

    SongDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Song', 'Album', 'RatingSong', 'FavouriteSong'];

    function SongDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Song, Album, RatingSong, FavouriteSong) {
        var vm = this;

        vm.song = entity;
        vm.clear = clear;
        vm.save = save;
        vm.albums = Album.query();
        vm.ratingsongs = RatingSong.query();
        vm.favouritesongs = FavouriteSong.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.song.id !== null) {
                Song.update(vm.song, onSaveSuccess, onSaveError);
            } else {
                Song.save(vm.song, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:songUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
