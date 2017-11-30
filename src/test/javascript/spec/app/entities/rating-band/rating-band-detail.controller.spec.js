'use strict';

describe('Controller Tests', function() {

    describe('RatingBand Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockRatingBand, MockUser, MockBand;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockRatingBand = jasmine.createSpy('MockRatingBand');
            MockUser = jasmine.createSpy('MockUser');
            MockBand = jasmine.createSpy('MockBand');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'RatingBand': MockRatingBand,
                'User': MockUser,
                'Band': MockBand
            };
            createController = function() {
                $injector.get('$controller')("RatingBandDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'dubionApp:ratingBandUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
