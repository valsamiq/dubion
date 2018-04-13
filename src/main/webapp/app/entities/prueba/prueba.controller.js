(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('PruebaController', PruebaController);

    PruebaController.$inject = ['Prueba'];

    function PruebaController(Prueba) {

        var vm = this;

        vm.pruebas = [];

        loadAll();

        function loadAll() {
            Prueba.query(function(result) {
                vm.pruebas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
