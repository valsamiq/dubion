(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('Beta', Beta);

    Beta.$inject = ['$resource'];

    function Beta ($resource) {
        var resourceUrl =  'api/betas/:id';

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
