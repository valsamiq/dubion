(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('userPage', {
                parent: 'entity',
                url: '/userPage',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'dubionApp.userPage.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/userPage/userPage.html',
                        controller: 'userPageController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('userPage');
                        $translatePartialLoader.addPart('status');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
    }

})();
