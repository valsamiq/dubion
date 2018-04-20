(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BandDetailController', BandDetailController);

    BandDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Band', 'Country', 'Label', 'Social', 'Genre', 'RatingBand', 'FavouriteBand', 'Artist'];

    function BandDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Band, Country, Label, Social, Genre, RatingBand, FavouriteBand, Artist) {
        var vm = this;

        vm.band = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.likeUpDown="r";
        vm.favouriteBand={};
        vm.albumActual;

        vm.ratingBands = [];
        vm.ratingBand= {};

        var unsubscribe = $rootScope.$on('dubionApp:bandUpdate', function(event, result) {
            vm.band = result;
        });
        $scope.$on('$destroy', unsubscribe);


        Band.get({id : $stateParams.id}, function(data) {

            vm.bandActual = data;
            getRatingBand(data.id);
            getFavoriteBand(data.id);

            vm.bandName = vm.bandActual.name;
            // songByName();
            vm.imatgeBand = '<img  src="data:image/jpg;base64, '+vm.bandActual.photo+'" />';
            // $scope.apply();

        });
        vm.likeDislike=function(){
            if(vm.likeUpDown=="s"){
                vm.favouriteBand.liked=false;
                vm.likeUpDown="r";
            }else{
                vm.favouriteBand.liked=true;
                vm.likeUpDown="s";
            }

            save();
            function save () {
                vm.isSaving = true;

                if(vm.favouriteBand.id){
                    console.log("RA_ID: "+vm.favouriteBand.id);
                    vm.favouriteBand.band=vm.bandActual;
                    FavouriteBand.update(vm.favouriteBand, onSaveSuccess, onSaveError);
                } else {
                    vm.favouriteBand.band=vm.bandActual;
                    FavouriteBand.save(vm.favouriteBand, onSaveSuccess, onSaveError);
                }
            }
            function onSaveSuccess (result) {
                console.log("SUCCESSS");
                $scope.$emit('dubionApp:favouriteBandUpdate', result);
                // $uibModalInstance.close(result);
                vm.isSaving = false;
            }

            function onSaveError () {
                console.log("EERROR");
                vm.isSaving = false;
            }

        }




        function getFavoriteBand(id) {
            FavouriteBand.favoriteByBand({id : id}, function (data){
                // FavouriteBand.get({id : id},   function (data) {
                console.log("hola");
                console.dir(data);
                vm.favouriteBand = data;
                if(vm.favouriteBand.liked){
                    vm.likeUpDown="s";
                }else{
                    vm.likeUpDown="r";
                }


            }, function(data){
                console.log("error??");
                // vm.favouriteBand.liked = false;

            });

        }


        $('#input-1').on('rating:change', function(event, value, caption) {
            save();
            function save () {


                vm.isSaving = true;
                console.log("RA: "+vm.ratingBand);

                console.log("BandActual:");
                console.dir(vm.bandActual);


                console.log("Rating Band:");
                console.dir(vm.ratingBand);


                //    console.log("RA_ID: "+vm.ratingBand.id);
                if(vm.ratingBand.id){
                    console.log("RA_ID: "+vm.ratingBand.id);
                    //  if (!vm.ratingBand.id || vm.ratingBand.id !== null ) {
                    vm.ratingBand.band=vm.bandActual;
                    vm.ratingBand.rating=value;
                    RatingBand.update(vm.ratingBand, onSaveSuccess, onSaveError);
                } else {
                    //vm.ratingBandm == undefined
                    vm.ratingBand={band:null,user:null,rating:null,date:null,id:null}
                    //console.log("RA_ID Undefined: "+vm.ratingBand.id);
                    vm.ratingBand.band=vm.bandActual;
                    vm.ratingBand.rating=value;
                    RatingBand.save(vm.ratingBand, onSaveSuccess, onSaveError);
                }
            }

            function onSaveSuccess (result) {
                console.log("SUCCESSS");
                $scope.$emit('dubionApp:ratingBandUpdate', result);
                // $uibModalInstance.close(result);
                vm.isSaving = false;
            }

            function onSaveError () {
                console.log("EERROR");
                vm.isSaving = false;
            }
        });




        function getRatingBand(id) {
            RatingBand.ratingByBand({id : id}, function (data){

                vm.ratingBand = data;
                $("#input-1").val(vm.ratingBand.rating);
                // with plugin options
                $("#input-1").rating({min:1, max:10, step:2, size:'xs'});

            }, function(data){

                console.log("error??");
                vm.ratingBand= 0;
                $("#input-1").rating({min:1, max:10, step:2, size:'xs'});

            });

        }

    }
})();
