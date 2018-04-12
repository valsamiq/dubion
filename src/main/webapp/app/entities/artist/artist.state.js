(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('artist', {
            parent: 'entity',
            url: '/artist',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.artist.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/artist/artists.html',
                    controller: 'ArtistController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('artist');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('artist-detail', {
            parent: 'artist',
            url: '/artist/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.artist.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/artist/artist-detail.html',
                    controller: 'ArtistDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('artist');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Artist', function($stateParams, Artist) {
                    return Artist.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'artist',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('artist-detail.edit', {
            parent: 'artist-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/artist/artist-dialog.html',
                    controller: 'ArtistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Artist', function(Artist) {
                            return Artist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('artist.new', {
            parent: 'artist',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/artist/artist-dialog.html',
                    controller: 'ArtistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                bio: null,
                                photo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('artist', null, { reload: 'artist' });
                }, function() {
                    $state.go('artist');
                });
            }]
        })
        .state('artist.edit', {
            parent: 'artist',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/artist/artist-dialog.html',
                    controller: 'ArtistDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Artist', function(Artist) {
                            return Artist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('artist', null, { reload: 'artist' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('artist.delete', {
            parent: 'artist',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/artist/artist-delete-dialog.html',
                    controller: 'ArtistDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Artist', function(Artist) {
                            return Artist.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('artist', null, { reload: 'artist' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
