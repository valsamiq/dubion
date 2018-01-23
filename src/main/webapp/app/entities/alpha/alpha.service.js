(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('Alpha', Alpha);

    Alpha.$inject = ['$resource'];

    function Alpha ($resource) {
        var resourceUrl =  'api/alphas/:id';

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
