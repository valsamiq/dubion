(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('testSexController', testSexController);

testSexController.$inject = ['Sex'];

    function testSexController(Sex) {

        var vm = this;

        vm.sexes = [];

        loadAll();

        function loadAll() {
            Sex.query(function(result) {
                vm.sexes = result;
                vm.searchQuery = null;
            });
        }

    }
})();
