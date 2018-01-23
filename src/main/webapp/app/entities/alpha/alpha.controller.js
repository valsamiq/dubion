(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('AlphaController', AlphaController);

    AlphaController.$inject = ['Alpha'];

    function AlphaController(Alpha) {

        var vm = this;

        vm.alphas = [];

        loadAll();

        function loadAll() {
            Alpha.query(function(result) {
                vm.alphas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
