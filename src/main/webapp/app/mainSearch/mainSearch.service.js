(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('MainSearch', MainSearch);

    MainSearch.$inject = ['$resource'];

    function MainSearch ($resource) {
        var resourceUrl =  'api/artists/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'queryAlbumBySong': { method: 'GET', isArray: true, url: '/api/songs/search/:name'},
            'queryAlbumByNameLocal': {method: 'GET', isArray: true, url: '/api/albums/by-name/:name'},
            'queryAlbumByName': { method: 'GET', isArray: true, url: 'api/albums/search/:name'},
            'queryArtistByName': {method: 'GET', isArray: true, url: 'api/artist/search/:name'},
            'queryArtistByNameLocal': {method: 'GET', isArray: true, url: 'api/bands/by-name/:name'},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
