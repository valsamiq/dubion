(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('Social', Social);

    Social.$inject = ['$resource'];

    function Social ($resource) {
        var resourceUrl =  'api/socials/:id';

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
