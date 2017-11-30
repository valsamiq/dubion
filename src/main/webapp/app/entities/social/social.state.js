(function() {
    'use strict';

    angular
        .module('dubionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('social', {
            parent: 'entity',
            url: '/social',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.social.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/social/socials.html',
                    controller: 'SocialController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('social');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('social-detail', {
            parent: 'social',
            url: '/social/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dubionApp.social.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/social/social-detail.html',
                    controller: 'SocialDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('social');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Social', function($stateParams, Social) {
                    return Social.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'social',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('social-detail.edit', {
            parent: 'social-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/social/social-dialog.html',
                    controller: 'SocialDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Social', function(Social) {
                            return Social.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('social.new', {
            parent: 'social',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/social/social-dialog.html',
                    controller: 'SocialDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                url: null,
                                official: null,
                                facebook: null,
                                twitter: null,
                                youTube: null,
                                googlePlus: null,
                                instagram: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('social', null, { reload: 'social' });
                }, function() {
                    $state.go('social');
                });
            }]
        })
        .state('social.edit', {
            parent: 'social',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/social/social-dialog.html',
                    controller: 'SocialDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Social', function(Social) {
                            return Social.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('social', null, { reload: 'social' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('social.delete', {
            parent: 'social',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/social/social-delete-dialog.html',
                    controller: 'SocialDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Social', function(Social) {
                            return Social.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('social', null, { reload: 'social' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
