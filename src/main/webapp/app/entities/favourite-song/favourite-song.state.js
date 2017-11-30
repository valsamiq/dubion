(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('favourite-song', {
            parent: 'entity',
            url: '/favourite-song',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.favouriteSong.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/favourite-song/favourite-songs.html',
                    controller: 'FavouriteSongController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('favouriteSong');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('favourite-song-detail', {
            parent: 'favourite-song',
            url: '/favourite-song/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.favouriteSong.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/favourite-song/favourite-song-detail.html',
                    controller: 'FavouriteSongDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('favouriteSong');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'FavouriteSong', function($stateParams, FavouriteSong) {
                    return FavouriteSong.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'favourite-song',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('favourite-song-detail.edit', {
            parent: 'favourite-song-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-song/favourite-song-dialog.html',
                    controller: 'FavouriteSongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FavouriteSong', function(FavouriteSong) {
                            return FavouriteSong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('favourite-song.new', {
            parent: 'favourite-song',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-song/favourite-song-dialog.html',
                    controller: 'FavouriteSongDialogController',
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
                    $state.go('favourite-song', null, { reload: 'favourite-song' });
                }, function() {
                    $state.go('favourite-song');
                });
            }]
        })
        .state('favourite-song.edit', {
            parent: 'favourite-song',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-song/favourite-song-dialog.html',
                    controller: 'FavouriteSongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FavouriteSong', function(FavouriteSong) {
                            return FavouriteSong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('favourite-song', null, { reload: 'favourite-song' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('favourite-song.delete', {
            parent: 'favourite-song',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-song/favourite-song-delete-dialog.html',
                    controller: 'FavouriteSongDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['FavouriteSong', function(FavouriteSong) {
                            return FavouriteSong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('favourite-song', null, { reload: 'favourite-song' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
