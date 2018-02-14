(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('albumPage', {
                parent: 'entity',
                url: '/albumPage',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'dubionApp.albumPage.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/albumPage/albumPage.html',
                        controller: 'albumPageController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('albumPage');
                        $translatePartialLoader.addPart('status');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
    }

})();
