(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider.state('favoritesUser', {
            parent: 'entity',
            url: '/favoritesUser',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.favoritesUser.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/FavoritesUser/favoritesUser.html',
                    controller: 'favoritesUserController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('favoritesUser');
                    return $translate.refresh();
                }]
            }
        });
        console.log("State controller Favorite User");
    }
})();
