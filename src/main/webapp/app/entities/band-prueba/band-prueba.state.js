(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('band-prueba', {
            parent: 'entity',
            url: '/band-prueba',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.bandPrueba.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/band-prueba/band-pruebas.html',
                    controller: 'BandPruebaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('bandPrueba');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('band-prueba-detail', {
            parent: 'band-prueba',
            url: '/band-prueba/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.bandPrueba.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/band-prueba/band-prueba-detail.html',
                    controller: 'BandPruebaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('bandPrueba');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'BandPrueba', function($stateParams, BandPrueba) {
                    return BandPrueba.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'band-prueba',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('band-prueba-detail.edit', {
            parent: 'band-prueba-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/band-prueba/band-prueba-dialog.html',
                    controller: 'BandPruebaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BandPrueba', function(BandPrueba) {
                            return BandPrueba.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('band-prueba.new', {
            parent: 'band-prueba',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/band-prueba/band-prueba-dialog.html',
                    controller: 'BandPruebaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                birthdate: null,
                                bio: null,
                                photo: null,
                                photoContentType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('band-prueba', null, { reload: 'band-prueba' });
                }, function() {
                    $state.go('band-prueba');
                });
            }]
        })
        .state('band-prueba.edit', {
            parent: 'band-prueba',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/band-prueba/band-prueba-dialog.html',
                    controller: 'BandPruebaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BandPrueba', function(BandPrueba) {
                            return BandPrueba.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('band-prueba', null, { reload: 'band-prueba' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('band-prueba.delete', {
            parent: 'band-prueba',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/band-prueba/band-prueba-delete-dialog.html',
                    controller: 'BandPruebaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['BandPrueba', function(BandPrueba) {
                            return BandPrueba.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('band-prueba', null, { reload: 'band-prueba' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
