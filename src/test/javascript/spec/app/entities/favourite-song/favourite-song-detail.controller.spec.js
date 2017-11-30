'use strict';

describe('Controller Tests', function() {

    describe('FavouriteSong Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFavouriteSong, MockUser, MockSong;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFavouriteSong = jasmine.createSpy('MockFavouriteSong');
            MockUser = jasmine.createSpy('MockUser');
            MockSong = jasmine.createSpy('MockSong');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'FavouriteSong': MockFavouriteSong,
                'User': MockUser,
                'Song': MockSong
            };
            createController = function() {
                $injector.get('$controller')("FavouriteSongDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'dubionApp:favouriteSongUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
