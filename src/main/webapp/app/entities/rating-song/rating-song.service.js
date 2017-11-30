(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('RatingSong', RatingSong);

    RatingSong.$inject = ['$resource', 'DateUtils'];

    function RatingSong ($resource, DateUtils) {
        var resourceUrl =  'api/rating-songs/:id';

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
