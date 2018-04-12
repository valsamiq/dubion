(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('artistPage', {
                parent: 'entity',
                url: '/artistPage/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'dubionApp.artistPage.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/artistPage/artistPage.html',
                        controller: 'artistPageController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('artistPage');
                        $translatePartialLoader.addPart('status');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
        }
})();
