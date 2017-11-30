'use strict';

describe('Controller Tests', function() {

    describe('FavouriteAlbum Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFavouriteAlbum, MockUser, MockAlbum;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFavouriteAlbum = jasmine.createSpy('MockFavouriteAlbum');
            MockUser = jasmine.createSpy('MockUser');
            MockAlbum = jasmine.createSpy('MockAlbum');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'FavouriteAlbum': MockFavouriteAlbum,
                'User': MockUser,
                'Album': MockAlbum
            };
            createController = function() {
                $injector.get('$controller')("FavouriteAlbumDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'dubionApp:favouriteAlbumUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
