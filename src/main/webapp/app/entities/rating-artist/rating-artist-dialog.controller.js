(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('RatingArtistDialogController', RatingArtistDialogController);

    RatingArtistDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RatingArtist', 'User', 'Artist'];

    function RatingArtistDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RatingArtist, User, Artist) {
        var vm = this;

        vm.ratingArtist = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.users = User.query();
        vm.artists = Artist.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.ratingArtist.id !== null) {
                RatingArtist.update(vm.ratingArtist, onSaveSuccess, onSaveError);
            } else {
                RatingArtist.save(vm.ratingArtist, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dubionApp:ratingArtistUpdate', result);
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
