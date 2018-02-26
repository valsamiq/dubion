(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('artistamain', {
            parent: 'app',
            url: '/pages/Artist',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/pages/Artist/artistmain.html',
                    controller: 'HomeControler',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
