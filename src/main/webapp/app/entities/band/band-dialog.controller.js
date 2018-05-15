(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BandDialogController', BandDialogController);

    BandDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Band', 'Country', 'Label', 'Social', 'Genre', 'RatingBand', 'FavouriteBand', 'Artist'];

    function BandDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Band, Country, Label, Social, Genre, RatingBand, FavouriteBand, Artist) {
        var vm = this;

        vm.band = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.countries = Country.query();
        vm.labels = Label.query();
        vm.socials = Social.query();
        vm.genres = Genre.query();
        vm.ratingbands = RatingBand.query();
        vm.favouritebands = FavouriteBand.query();
        vm.artists = Artist.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.band.id !== null) {
                Band.update(vm.band, onSaveSuccess, onSaveError);
            } else {
                Band.save(vm.band, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:bandUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.birthDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
