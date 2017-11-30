(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rating-band', {
            parent: 'entity',
            url: '/rating-band',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.ratingBand.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rating-band/rating-bands.html',
                    controller: 'RatingBandController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ratingBand');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('rating-band-detail', {
            parent: 'rating-band',
            url: '/rating-band/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.ratingBand.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rating-band/rating-band-detail.html',
                    controller: 'RatingBandDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ratingBand');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'RatingBand', function($stateParams, RatingBand) {
                    return RatingBand.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rating-band',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rating-band-detail.edit', {
            parent: 'rating-band-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-band/rating-band-dialog.html',
                    controller: 'RatingBandDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RatingBand', function(RatingBand) {
                            return RatingBand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rating-band.new', {
            parent: 'rating-band',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-band/rating-band-dialog.html',
                    controller: 'RatingBandDialogController',
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
                    $state.go('rating-band', null, { reload: 'rating-band' });
                }, function() {
                    $state.go('rating-band');
                });
            }]
        })
        .state('rating-band.edit', {
            parent: 'rating-band',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-band/rating-band-dialog.html',
                    controller: 'RatingBandDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RatingBand', function(RatingBand) {
                            return RatingBand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rating-band', null, { reload: 'rating-band' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rating-band.delete', {
            parent: 'rating-band',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rating-band/rating-band-delete-dialog.html',
                    controller: 'RatingBandDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RatingBand', function(RatingBand) {
                            return RatingBand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rating-band', null, { reload: 'rating-band' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
