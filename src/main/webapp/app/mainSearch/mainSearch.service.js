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
            'queryAlbumByName': { method: 'GET', isArray: true, url: 'api/albums/search/:name'},
            //'queryAlbumById': { method: 'GET', isArray: true, url: ''},
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
