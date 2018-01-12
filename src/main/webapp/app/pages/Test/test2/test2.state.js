(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('test2', {
            parent: 'app',
            url: '/test2',
            data: {
                authorities: []
            },
            views: {
                'bannerImg@': {
                    templateUrl: 'app/pages/test2/test2.html',
                    controller: 'test2Controller',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
