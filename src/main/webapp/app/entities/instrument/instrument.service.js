(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('Instrument', Instrument);

    Instrument.$inject = ['$resource'];

    function Instrument ($resource) {
        var resourceUrl =  'api/instruments/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
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
