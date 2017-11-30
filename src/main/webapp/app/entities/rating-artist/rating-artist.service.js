(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('RatingArtist', RatingArtist);

    RatingArtist.$inject = ['$resource', 'DateUtils'];

    function RatingArtist ($resource, DateUtils) {
        var resourceUrl =  'api/rating-artists/:id';

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
