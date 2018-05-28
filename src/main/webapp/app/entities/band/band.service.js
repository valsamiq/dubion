(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('Band', Band);

    Band.$inject = ['$resource', 'DateUtils'];

    function Band ($resource, DateUtils) {
        var resourceUrl =  'api/bands/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'queryByName': { method: 'GET', isArray: true, url: '/api/bands/by-name/:name'},
            'albumBand': { method: 'GET', isArray: true, url: '/api/albums/band/:bandId'},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.birthDate = DateUtils.convertLocalDateFromServer(data.birthDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.birthDate = DateUtils.convertLocalDateToServer(copy.birthDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.birthDate = DateUtils.convertLocalDateToServer(copy.birthDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
