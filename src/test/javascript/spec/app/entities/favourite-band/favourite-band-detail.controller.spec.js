'use strict';

describe('Controller Tests', function() {

    describe('FavouriteBand Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFavouriteBand, MockUser, MockBand;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFavouriteBand = jasmine.createSpy('MockFavouriteBand');
            MockUser = jasmine.createSpy('MockUser');
            MockBand = jasmine.createSpy('MockBand');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'FavouriteBand': MockFavouriteBand,
                'User': MockUser,
                'Band': MockBand
            };
            createController = function() {
                $injector.get('$controller')("FavouriteBandDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'dubionApp:favouriteBandUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
