(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('prueba', {
            parent: 'entity',
            url: '/prueba',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.prueba.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/prueba/pruebas.html',
                    controller: 'PruebaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('prueba');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('prueba-detail', {
            parent: 'prueba',
            url: '/prueba/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.prueba.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/prueba/prueba-detail.html',
                    controller: 'PruebaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('prueba');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Prueba', function($stateParams, Prueba) {
                    return Prueba.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'prueba',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('prueba-detail.edit', {
            parent: 'prueba-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/prueba/prueba-dialog.html',
                    controller: 'PruebaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Prueba', function(Prueba) {
                            return Prueba.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('prueba.new', {
            parent: 'prueba',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/prueba/prueba-dialog.html',
                    controller: 'PruebaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('prueba', null, { reload: 'prueba' });
                }, function() {
                    $state.go('prueba');
                });
            }]
        })
        .state('prueba.edit', {
            parent: 'prueba',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/prueba/prueba-dialog.html',
                    controller: 'PruebaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Prueba', function(Prueba) {
                            return Prueba.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('prueba', null, { reload: 'prueba' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('prueba.delete', {
            parent: 'prueba',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/prueba/prueba-delete-dialog.html',
                    controller: 'PruebaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Prueba', function(Prueba) {
                            return Prueba.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('prueba', null, { reload: 'prueba' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
