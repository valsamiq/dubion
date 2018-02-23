(function() {
    'use strict';

    angular
        .module('dubionApp')
        .controller('BannerController', BannerController);

    BannerController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];

    function BannerController ($scope, Principal, LoginService, $state) {
        var vm = this;

////////////
vm.myInterval = 5000;
vm.noWrapSlides = false;
vm.active = 0;
var slides = vm.slides = [];
var currIndex = 0;
//
vm.addSlide = function() {
    var newWidth = 600 + slides.length + 1;
    slides.push({
        image:'content/banner/Banner3SizeOK.jpg',
        //image: '//unsplash.it/' + newWidth + '/300',
        text: ['Brutal'][slides.length % 4],
        id: currIndex++
    });
    slides.push({
        image:'content/banner/Banner2SizeOK.jpg',
        //image: '//unsplash.it/' + newWidth + '/300',
        text: ['Brutal','Awesome things','That.s good shit, bro','I want to eat a dinosaur'][slides.length % 4],
        id: currIndex++
    });
    slides.push({
        image:'content/banner/Banner1SizeOK.jpg',
        //image: '//unsplash.it/' + newWidth + '/300',
        text: ['Brutal','Awesome things','That.s good shit, bro','I want to eat a dinosaur'][slides.length % 4],
        id: currIndex++
    });
};
//
vm.randomize = function() {
    var indexes = generateIndexesArray();
    assignNewIndexesToSlides(indexes);
};

//
for (var i = 0; i < 1; i++) {
    vm.addSlide();
}
//
// // Randomize logic below
//
function assignNewIndexesToSlides(indexes) {
    for (var i = 0, l = slides.length; i < l; i++) {
        slides[i].id = indexes.pop();
    }
}

function generateIndexesArray() {
    var indexes = [];
    for (var i = 0; i < currIndex; ++i) {
        indexes[i] = i;
    }
    return shuffle(indexes);
}

// http://stackoverflow.com/questions/962802#962890
function shuffle(array) {
    var tmp, current, top = array.length;
    if (top) {
        while (--top) {
            current = Math.floor(Math.random() * (top + 1));
            tmp = array[current];
            array[current] = array[top];
            array[top] = tmp;
        }
    }
    return array;
}
// ////////////

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }
    }
})();
