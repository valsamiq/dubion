(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('testSex-testSex', {
                parent: 'page-sets',
                url: '/pages/test-sex/test-sex',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'dubionApp.testSex.title'
                },
        views: {
            'content@': {
                templateUrl: 'app/pages/test-sex/testSex.html',
                    controller: 'testSexController',
                    controllerAs: 'vm'
            }
        },
        resolve: {
            translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                $translatePartialLoader.addPart('testSex');
                $translatePartialLoader.addPart('global');
                return $translate.refresh();
            }],

        }
    })
    ;
    }

})();
