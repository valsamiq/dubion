(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('gamma', {
            parent: 'entity',
            url: '/gamma',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.gamma.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/gamma/gammas.html',
                    controller: 'GammaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('gamma');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('gamma-detail', {
            parent: 'gamma',
            url: '/gamma/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.gamma.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/gamma/gamma-detail.html',
                    controller: 'GammaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('gamma');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Gamma', function($stateParams, Gamma) {
                    return Gamma.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'gamma',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('gamma-detail.edit', {
            parent: 'gamma-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gamma/gamma-dialog.html',
                    controller: 'GammaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Gamma', function(Gamma) {
                            return Gamma.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('gamma.new', {
            parent: 'gamma',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gamma/gamma-dialog.html',
                    controller: 'GammaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('gamma', null, { reload: 'gamma' });
                }, function() {
                    $state.go('gamma');
                });
            }]
        })
        .state('gamma.edit', {
            parent: 'gamma',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gamma/gamma-dialog.html',
                    controller: 'GammaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Gamma', function(Gamma) {
                            return Gamma.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('gamma', null, { reload: 'gamma' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('gamma.delete', {
            parent: 'gamma',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gamma/gamma-delete-dialog.html',
                    controller: 'GammaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Gamma', function(Gamma) {
                            return Gamma.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('gamma', null, { reload: 'gamma' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
