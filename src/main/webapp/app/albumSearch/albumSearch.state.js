(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('albumSearch', {
                parent: 'entity',
                url: '/albumSearch',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'dubionApp.albumSearch.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/albumSearch/albumSearch.html',
                        controller: 'albumSearchController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('albumSearch');
                        $translatePartialLoader.addPart('status');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
        }
})();
