'use strict';

describe('Controller Tests', function() {

    describe('Band Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockBand, MockCountry, MockLabel, MockSocial, MockGenre, MockRatingBand, MockFavouriteBand, MockArtist;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockBand = jasmine.createSpy('MockBand');
            MockCountry = jasmine.createSpy('MockCountry');
            MockLabel = jasmine.createSpy('MockLabel');
            MockSocial = jasmine.createSpy('MockSocial');
            MockGenre = jasmine.createSpy('MockGenre');
            MockRatingBand = jasmine.createSpy('MockRatingBand');
            MockFavouriteBand = jasmine.createSpy('MockFavouriteBand');
            MockArtist = jasmine.createSpy('MockArtist');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Band': MockBand,
                'Country': MockCountry,
                'Label': MockLabel,
                'Social': MockSocial,
                'Genre': MockGenre,
                'RatingBand': MockRatingBand,
                'FavouriteBand': MockFavouriteBand,
                'Artist': MockArtist
            };
            createController = function() {
                $injector.get('$controller')("BandDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'dubionApp:bandUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
