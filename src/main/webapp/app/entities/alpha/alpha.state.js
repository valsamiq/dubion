(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('alpha', {
            parent: 'entity',
            url: '/alpha',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.alpha.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/alpha/alphas.html',
                    controller: 'AlphaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('alpha');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('alpha-detail', {
            parent: 'alpha',
            url: '/alpha/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.alpha.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/alpha/alpha-detail.html',
                    controller: 'AlphaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('alpha');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Alpha', function($stateParams, Alpha) {
                    return Alpha.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'alpha',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('alpha-detail.edit', {
            parent: 'alpha-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alpha/alpha-dialog.html',
                    controller: 'AlphaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Alpha', function(Alpha) {
                            return Alpha.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('alpha.new', {
            parent: 'alpha',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alpha/alpha-dialog.html',
                    controller: 'AlphaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                edad: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('alpha', null, { reload: 'alpha' });
                }, function() {
                    $state.go('alpha');
                });
            }]
        })
        .state('alpha.edit', {
            parent: 'alpha',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alpha/alpha-dialog.html',
                    controller: 'AlphaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Alpha', function(Alpha) {
                            return Alpha.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('alpha', null, { reload: 'alpha' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('alpha.delete', {
            parent: 'alpha',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/alpha/alpha-delete-dialog.html',
                    controller: 'AlphaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Alpha', function(Alpha) {
                            return Alpha.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('alpha', null, { reload: 'alpha' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
