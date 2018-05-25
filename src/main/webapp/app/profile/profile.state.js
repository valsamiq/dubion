(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('profile', {
                parent: 'entity',
                url: '/profile/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'profile'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/profile/profile.html',
                        controller: 'profileController',
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
