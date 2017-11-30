(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('Sex', Sex);

    Sex.$inject = ['$resource'];

    function Sex ($resource) {
        var resourceUrl =  'api/sexes/:id';

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
