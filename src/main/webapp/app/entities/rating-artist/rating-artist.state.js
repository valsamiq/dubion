(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rating-artist', {
            parent: 'entity',
            url: '/rating-artist',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.ratingArtist.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rating-artist/rating-artists.html',
                    controller: 'RatingArtistController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ratingArtist');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('rating-artist-detail', {
            parent: 'rating-artist',
            url: '/rating-artist/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.ratingArtist.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rating-artist/rating-artist-detail.html',
                    controller: 'RatingArtistDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ratingArtist');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'RatingArtist', function($stateParams, RatingArtist) {
                    return RatingArtist.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rating-artist',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rating-artist-detail.edit', {
            parent: 'rating-artist-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-artist/rating-artist-dialog.html',
                    controller: 'RatingArtistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RatingArtist', function(RatingArtist) {
                            return RatingArtist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rating-artist.new', {
            parent: 'rating-artist',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-artist/rating-artist-dialog.html',
                    controller: 'RatingArtistDialogController',
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
                    $state.go('rating-artist', null, { reload: 'rating-artist' });
                }, function() {
                    $state.go('rating-artist');
                });
            }]
        })
        .state('rating-artist.edit', {
            parent: 'rating-artist',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-artist/rating-artist-dialog.html',
                    controller: 'RatingArtistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RatingArtist', function(RatingArtist) {
                            return RatingArtist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rating-artist', null, { reload: 'rating-artist' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rating-artist.delete', {
            parent: 'rating-artist',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-artist/rating-artist-delete-dialog.html',
                    controller: 'RatingArtistDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RatingArtist', function(RatingArtist) {
                            return RatingArtist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rating-artist', null, { reload: 'rating-artist' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
