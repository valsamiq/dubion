(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('favourite-band', {
            parent: 'entity',
            url: '/favourite-band',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.favouriteBand.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/favourite-band/favourite-bands.html',
                    controller: 'FavouriteBandController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('favouriteBand');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('favourite-band-detail', {
            parent: 'favourite-band',
            url: '/favourite-band/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.favouriteBand.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/favourite-band/favourite-band-detail.html',
                    controller: 'FavouriteBandDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('favouriteBand');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'FavouriteBand', function($stateParams, FavouriteBand) {
                    return FavouriteBand.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'favourite-band',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('favourite-band-detail.edit', {
            parent: 'favourite-band-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-band/favourite-band-dialog.html',
                    controller: 'FavouriteBandDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FavouriteBand', function(FavouriteBand) {
                            return FavouriteBand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('favourite-band.new', {
            parent: 'favourite-band',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-band/favourite-band-dialog.html',
                    controller: 'FavouriteBandDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                liked: null,
                                date: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('favourite-band', null, { reload: 'favourite-band' });
                }, function() {
                    $state.go('favourite-band');
                });
            }]
        })
        .state('favourite-band.edit', {
            parent: 'favourite-band',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-band/favourite-band-dialog.html',
                    controller: 'FavouriteBandDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FavouriteBand', function(FavouriteBand) {
                            return FavouriteBand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('favourite-band', null, { reload: 'favourite-band' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('favourite-band.delete', {
            parent: 'favourite-band',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-band/favourite-band-delete-dialog.html',
                    controller: 'FavouriteBandDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['FavouriteBand', function(FavouriteBand) {
                            return FavouriteBand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('favourite-band', null, { reload: 'favourite-band' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
