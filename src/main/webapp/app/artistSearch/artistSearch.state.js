(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('artistSearch', {
                parent: 'entity',
                url: '/artistSearch',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'dubionApp.artistSearch.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/artistSearch/artistSearch.html',
                        controller: 'artistSearchController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('artistSearch');
                        $translatePartialLoader.addPart('status');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
        }
})();
