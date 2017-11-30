(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('ArtistDialogController', ArtistDialogController);

    ArtistDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Artist', 'Band', 'Instrument', 'RatingArtist'];

    function ArtistDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Artist, Band, Instrument, RatingArtist) {
        var vm = this;

        vm.artist = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.bands = Band.query();
        vm.instruments = Instrument.query();
        vm.ratingartists = RatingArtist.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.artist.id !== null) {
                Artist.update(vm.artist, onSaveSuccess, onSaveError);
            } else {
                Artist.save(vm.artist, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:artistUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.birthDate = false;

        vm.setPhoto = function ($file, artist) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        artist.photo = base64Data;
                        artist.photoContentType = $file.type;
                    });
                });
            }
        };

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
