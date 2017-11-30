(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('sex', {
            parent: 'entity',
            url: '/sex',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.sex.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sex/sexes.html',
                    controller: 'SexController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('sex');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('sex-detail', {
            parent: 'sex',
            url: '/sex/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.sex.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/sex/sex-detail.html',
                    controller: 'SexDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('sex');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Sex', function($stateParams, Sex) {
                    return Sex.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'sex',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('sex-detail.edit', {
            parent: 'sex-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sex/sex-dialog.html',
                    controller: 'SexDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Sex', function(Sex) {
                            return Sex.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sex.new', {
            parent: 'sex',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sex/sex-dialog.html',
                    controller: 'SexDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                orientation: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('sex', null, { reload: 'sex' });
                }, function() {
                    $state.go('sex');
                });
            }]
        })
        .state('sex.edit', {
            parent: 'sex',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sex/sex-dialog.html',
                    controller: 'SexDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Sex', function(Sex) {
                            return Sex.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sex', null, { reload: 'sex' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('sex.delete', {
            parent: 'sex',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/sex/sex-delete-dialog.html',
                    controller: 'SexDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Sex', function(Sex) {
                            return Sex.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('sex', null, { reload: 'sex' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
