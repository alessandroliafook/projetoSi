var app = angular.module('AdExtreme', ['ngMaterial', 'ui.router']);

app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');
    
    $stateProvider.state('main', {
            url: '/main',
            templateUrl: '/views/main.html',
            controller: 'MainController'
        }).state('login', {
            url: '/login',
            templateUrl: '/views/login.html',
            controller: 'LoginController'    
        });
});