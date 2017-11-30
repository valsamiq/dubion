(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('genre', {
            parent: 'entity',
            url: '/genre',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.genre.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/genre/genres.html',
                    controller: 'GenreController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('genre');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('genre-detail', {
            parent: 'genre',
            url: '/genre/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.genre.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/genre/genre-detail.html',
                    controller: 'GenreDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('genre');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Genre', function($stateParams, Genre) {
                    return Genre.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'genre',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('genre-detail.edit', {
            parent: 'genre-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/genre/genre-dialog.html',
                    controller: 'GenreDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Genre', function(Genre) {
                            return Genre.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('genre.new', {
            parent: 'genre',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/genre/genre-dialog.html',
                    controller: 'GenreDialogController',
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
                    $state.go('genre', null, { reload: 'genre' });
                }, function() {
                    $state.go('genre');
                });
            }]
        })
        .state('genre.edit', {
            parent: 'genre',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/genre/genre-dialog.html',
                    controller: 'GenreDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Genre', function(Genre) {
                            return Genre.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('genre', null, { reload: 'genre' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('genre.delete', {
            parent: 'genre',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/genre/genre-delete-dialog.html',
                    controller: 'GenreDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Genre', function(Genre) {
                            return Genre.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('genre', null, { reload: 'genre' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
