(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('favourite-album', {
            parent: 'entity',
            url: '/favourite-album',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.favouriteAlbum.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/favourite-album/favourite-albums.html',
                    controller: 'FavouriteAlbumController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('favouriteAlbum');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('favourite-album-detail', {
            parent: 'favourite-album',
            url: '/favourite-album/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.favouriteAlbum.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/favourite-album/favourite-album-detail.html',
                    controller: 'FavouriteAlbumDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('favouriteAlbum');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'FavouriteAlbum', function($stateParams, FavouriteAlbum) {
                    return FavouriteAlbum.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'favourite-album',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('favourite-album-detail.edit', {
            parent: 'favourite-album-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-album/favourite-album-dialog.html',
                    controller: 'FavouriteAlbumDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FavouriteAlbum', function(FavouriteAlbum) {
                            return FavouriteAlbum.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('favourite-album.new', {
            parent: 'favourite-album',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-album/favourite-album-dialog.html',
                    controller: 'FavouriteAlbumDialogController',
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
                    $state.go('favourite-album', null, { reload: 'favourite-album' });
                }, function() {
                    $state.go('favourite-album');
                });
            }]
        })
        .state('favourite-album.edit', {
            parent: 'favourite-album',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-album/favourite-album-dialog.html',
                    controller: 'FavouriteAlbumDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FavouriteAlbum', function(FavouriteAlbum) {
                            return FavouriteAlbum.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('favourite-album', null, { reload: 'favourite-album' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('favourite-album.delete', {
            parent: 'favourite-album',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/favourite-album/favourite-album-delete-dialog.html',
                    controller: 'FavouriteAlbumDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['FavouriteAlbum', function(FavouriteAlbum) {
                            return FavouriteAlbum.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('favourite-album', null, { reload: 'favourite-album' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
