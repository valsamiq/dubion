(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rating-song', {
            parent: 'entity',
            url: '/rating-song',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.ratingSong.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rating-song/rating-songs.html',
                    controller: 'RatingSongController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ratingSong');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('rating-song-detail', {
            parent: 'rating-song',
            url: '/rating-song/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.ratingSong.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rating-song/rating-song-detail.html',
                    controller: 'RatingSongDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ratingSong');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'RatingSong', function($stateParams, RatingSong) {
                    return RatingSong.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rating-song',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rating-song-detail.edit', {
            parent: 'rating-song-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-song/rating-song-dialog.html',
                    controller: 'RatingSongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RatingSong', function(RatingSong) {
                            return RatingSong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rating-song.new', {
            parent: 'rating-song',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-song/rating-song-dialog.html',
                    controller: 'RatingSongDialogController',
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
                    $state.go('rating-song', null, { reload: 'rating-song' });
                }, function() {
                    $state.go('rating-song');
                });
            }]
        })
        .state('rating-song.edit', {
            parent: 'rating-song',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-song/rating-song-dialog.html',
                    controller: 'RatingSongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RatingSong', function(RatingSong) {
                            return RatingSong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rating-song', null, { reload: 'rating-song' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rating-song.delete', {
            parent: 'rating-song',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-song/rating-song-delete-dialog.html',
                    controller: 'RatingSongDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RatingSong', function(RatingSong) {
                            return RatingSong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rating-song', null, { reload: 'rating-song' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
