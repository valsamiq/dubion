(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('bandPage', {
                parent: 'entity',
                url: '/bandPage',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'dubionApp.bandPage.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/bandPage/bandPage.html',
                        controller: 'bandPageController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('bandPage');
                        $translatePartialLoader.addPart('status');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
    }
})();
