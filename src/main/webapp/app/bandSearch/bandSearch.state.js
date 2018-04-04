(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('bandSearch', {
                parent: 'entity',
                url: '/bandSearch',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'dubionApp.bandSearch.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/bandSearch/bandSearch.html',
                        controller: 'bandSearchController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('bandSearch');
                        $translatePartialLoader.addPart('status');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
        }
})();
