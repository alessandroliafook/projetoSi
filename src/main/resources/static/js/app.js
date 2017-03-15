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

app.service('Auth',function($cookies, $state, $http) {
    this.getToken = function () {
        var tokenUser = $cookies.get('token');
        if (tokenUser) {
            $http.defaults.headers.common['Authorization'] = tokenUser;
            return tokenUser;
        } else {
            return null; 
        }
    }

    this.saveToken = function (token) {
        $cookies.put('token', token);
        $http.defaults.headers.common['Authorization'] = token;
    }

    this.logout = function () {
        $cookies.remove('token');
    }

    this.verificaStatus = function(status) {
        if (status == 203) {
            $cookies.remove('token');
            $state.go('login');
            return false;
        } else {
            return true;
        }
    }

    if (this.getToken()) {
        $http.defaults.headers.common['Authorization'] = this.getToken();
    }    
});