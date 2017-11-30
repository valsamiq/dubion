(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rating-album', {
            parent: 'entity',
            url: '/rating-album',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.ratingAlbum.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rating-album/rating-albums.html',
                    controller: 'RatingAlbumController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ratingAlbum');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('rating-album-detail', {
            parent: 'rating-album',
            url: '/rating-album/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.ratingAlbum.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rating-album/rating-album-detail.html',
                    controller: 'RatingAlbumDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ratingAlbum');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'RatingAlbum', function($stateParams, RatingAlbum) {
                    return RatingAlbum.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rating-album',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rating-album-detail.edit', {
            parent: 'rating-album-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-album/rating-album-dialog.html',
                    controller: 'RatingAlbumDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RatingAlbum', function(RatingAlbum) {
                            return RatingAlbum.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rating-album.new', {
            parent: 'rating-album',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-album/rating-album-dialog.html',
                    controller: 'RatingAlbumDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                date: null,
                                rating: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('rating-album', null, { reload: 'rating-album' });
                }, function() {
                    $state.go('rating-album');
                });
            }]
        })
        .state('rating-album.edit', {
            parent: 'rating-album',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-album/rating-album-dialog.html',
                    controller: 'RatingAlbumDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RatingAlbum', function(RatingAlbum) {
                            return RatingAlbum.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rating-album', null, { reload: 'rating-album' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rating-album.delete', {
            parent: 'rating-album',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-album/rating-album-delete-dialog.html',
                    controller: 'RatingAlbumDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RatingAlbum', function(RatingAlbum) {
                            return RatingAlbum.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rating-album', null, { reload: 'rating-album' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
