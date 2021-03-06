(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('Artist', Artist);

    Artist.$inject = ['$resource'];

    function Artist ($resource) {
        var resourceUrl =  'api/artists/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'queryByName': { method: 'GET', isArray: true, url: 'api/artist/by-name/:name'},
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
