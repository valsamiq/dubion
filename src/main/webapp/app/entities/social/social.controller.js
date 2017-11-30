(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('SocialController', SocialController);

    SocialController.$inject = ['Social'];

    function SocialController(Social) {

        var vm = this;

        vm.socials = [];

        loadAll();

        function loadAll() {
            Social.query(function(result) {
                vm.socials = result;
                vm.searchQuery = null;
            });
        }
    }
})();
