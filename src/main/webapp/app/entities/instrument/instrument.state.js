(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('instrument', {
            parent: 'entity',
            url: '/instrument',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.instrument.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/instrument/instruments.html',
                    controller: 'InstrumentController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('instrument');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('instrument-detail', {
            parent: 'instrument',
            url: '/instrument/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.instrument.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/instrument/instrument-detail.html',
                    controller: 'InstrumentDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('instrument');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Instrument', function($stateParams, Instrument) {
                    return Instrument.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'instrument',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('instrument-detail.edit', {
            parent: 'instrument-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/instrument/instrument-dialog.html',
                    controller: 'InstrumentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Instrument', function(Instrument) {
                            return Instrument.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('instrument.new', {
            parent: 'instrument',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/instrument/instrument-dialog.html',
                    controller: 'InstrumentDialogController',
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
                    $state.go('instrument', null, { reload: 'instrument' });
                }, function() {
                    $state.go('instrument');
                });
            }]
        })
        .state('instrument.edit', {
            parent: 'instrument',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/instrument/instrument-dialog.html',
                    controller: 'InstrumentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Instrument', function(Instrument) {
                            return Instrument.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('instrument', null, { reload: 'instrument' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('instrument.delete', {
            parent: 'instrument',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/instrument/instrument-delete-dialog.html',
                    controller: 'InstrumentDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Instrument', function(Instrument) {
                            return Instrument.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('instrument', null, { reload: 'instrument' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
