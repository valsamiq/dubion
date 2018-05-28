(function() {
    'use strict';
    angular
        .module('dubionApp')
        .factory('ProfileServiceK', ProfileServiceK);

    ProfileServiceK.$inject = ['$resource'];

    function ProfileServiceK ($resource) {
        var resourceUrl =  'api/users/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'currentUser': {
                method: 'GET',
                url: 'api/users/activeUser',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'currentUserExt': {
                method: 'GET',
                url: 'api/user-exts/getUserExtData',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
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




//'queryByName': { method: 'GET', isArray: true, url: 'api/albums/search/:name'},
