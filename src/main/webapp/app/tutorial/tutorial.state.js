(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('tutorial', {
                parent: 'entity',
                url: '/tutorial',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'tutorial'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/tutorial/tutorial.html',
                        controller: 'tutorialController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('tutorial');
                        $translatePartialLoader.addPart('status');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
    }
})();
