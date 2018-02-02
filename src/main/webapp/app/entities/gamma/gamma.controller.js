(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('GammaController', GammaController);

    GammaController.$inject = ['Gamma'];

    function GammaController(Gamma) {

        var vm = this;

        vm.gammas = [];

        loadAll();

        function loadAll() {
            Gamma.query(function(result) {
                vm.gammas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
