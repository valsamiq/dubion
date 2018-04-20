(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('mainSearch', {
                parent: 'entity',
                url: '/mainSearch',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'dubionApp.mainSearch.home.title'
                },
                views: {
                    'searchBar@': {
                        templateUrl: 'app/mainSearch/mainSearch.html',
                        controller: 'mainSearchController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('mainSearch');
                        $translatePartialLoader.addPart('status');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
    }
})();
