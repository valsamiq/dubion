'use strict';

describe('Controller Tests', function() {

    describe('Album Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockAlbum, MockBand, MockGenre, MockRatingAlbum, MockFavouriteAlbum, MockSong;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockAlbum = jasmine.createSpy('MockAlbum');
            MockBand = jasmine.createSpy('MockBand');
            MockGenre = jasmine.createSpy('MockGenre');
            MockRatingAlbum = jasmine.createSpy('MockRatingAlbum');
            MockFavouriteAlbum = jasmine.createSpy('MockFavouriteAlbum');
            MockSong = jasmine.createSpy('MockSong');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Album': MockAlbum,
                'Band': MockBand,
                'Genre': MockGenre,
                'RatingAlbum': MockRatingAlbum,
                'FavouriteAlbum': MockFavouriteAlbum,
                'Song': MockSong
            };
            createController = function() {
                $injector.get('$controller')("AlbumDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'dubionApp:albumUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
