(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('beta', {
            parent: 'entity',
            url: '/beta',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.beta.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/beta/betas.html',
                    controller: 'BetaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('beta');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('beta-detail', {
            parent: 'beta',
            url: '/beta/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.beta.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/beta/beta-detail.html',
                    controller: 'BetaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('beta');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Beta', function($stateParams, Beta) {
                    return Beta.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'beta',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('beta-detail.edit', {
            parent: 'beta-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/beta/beta-dialog.html',
                    controller: 'BetaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Beta', function(Beta) {
                            return Beta.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('beta.new', {
            parent: 'beta',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/beta/beta-dialog.html',
                    controller: 'BetaDialogController',
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
                    $state.go('beta', null, { reload: 'beta' });
                }, function() {
                    $state.go('beta');
                });
            }]
        })
        .state('beta.edit', {
            parent: 'beta',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/beta/beta-dialog.html',
                    controller: 'BetaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Beta', function(Beta) {
                            return Beta.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('beta', null, { reload: 'beta' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('beta.delete', {
            parent: 'beta',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/beta/beta-delete-dialog.html',
                    controller: 'BetaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Beta', function(Beta) {
                            return Beta.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('beta', null, { reload: 'beta' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
