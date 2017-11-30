(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('RatingAlbum', RatingAlbum);

    RatingAlbum.$inject = ['$resource', 'DateUtils'];

    function RatingAlbum ($resource, DateUtils) {
        var resourceUrl =  'api/rating-albums/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.date = DateUtils.convertDateTimeFromServer(data.date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
