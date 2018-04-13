(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('Prueba', Prueba);

    Prueba.$inject = ['$resource'];

    function Prueba ($resource) {
        var resourceUrl =  'api/pruebas/:id';

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
