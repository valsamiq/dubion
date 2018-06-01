(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('Album', Album);

    Album.$inject = ['$resource', 'DateUtils'];

    function Album ($resource, DateUtils) {
        var resourceUrl =  'api/albums/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'queryByName': { method: 'GET', isArray: true, url: '/api/albums/search/:name'},
            'getSongsByName': { method: 'GET', isArray: true, url: 'api/songs/:idAlbum/albums-song'},
            'getSongsByIdPageble' : {method: 'GET', isArray: true, url: 'api/albums/:idAlbum/page/:page'},
            'querytop' : {method: 'GET', isArray: true, url: 'api/albums/top2'},
            'querylast' : {method: 'GET', isArray: true, url: 'api/albumsNew'},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.releaseDate = DateUtils.convertLocalDateFromServer(data.releaseDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.releaseDate = DateUtils.convertLocalDateToServer(copy.releaseDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.releaseDate = DateUtils.convertLocalDateToServer(copy.releaseDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
