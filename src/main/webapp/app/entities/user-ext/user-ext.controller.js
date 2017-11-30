(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('UserExtController', UserExtController);

    UserExtController.$inject = ['DataUtils', 'UserExt'];

    function UserExtController(DataUtils, UserExt) {

        var vm = this;

        vm.userExts = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            UserExt.query(function(result) {
                vm.userExts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
