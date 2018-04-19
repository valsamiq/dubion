(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('FavouriteBand', FavouriteBand);

    FavouriteBand.$inject = ['$resource', 'DateUtils'];

    function FavouriteBand ($resource, DateUtils) {
        var resourceUrl =  'api/favourite-bands/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'favoriteByBand': {method: 'GET', url: 'api/favourite-bands/band/:id'},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.date = DateUtils.convertLocalDateFromServer(data.date);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.date = DateUtils.convertLocalDateToServer(copy.date);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.date = DateUtils.convertLocalDateToServer(copy.date);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
