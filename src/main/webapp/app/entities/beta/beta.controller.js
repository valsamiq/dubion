(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BetaController', BetaController);

    BetaController.$inject = ['Beta'];

    function BetaController(Beta) {

        var vm = this;

        vm.betas = [];

        loadAll();

        function loadAll() {
            Beta.query(function(result) {
                vm.betas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
