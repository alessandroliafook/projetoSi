/**
 * Created by lucasdiniz on 23/03/17.
 */
app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');

    $stateProvider.state('main', {
        url: '/main',
        templateUrl: '/views/main.html',
        controller: 'MainController'
    }).state('login', {
        url: '/login',
        templateUrl: '/views/login.html',
        controller: 'LoginController'
    }).state('anuncios', {
        url: '/anuncios',
        templateUrl: '/views/anuncios.html',
        controller: 'AnunciosController'
    }).state('cadastrarAnuncio', {
        url: '/cadastrarAnuncio',
        templateUrl: '/views/cadastrarAnuncio.html',
        controller: 'CadastroController'
    }).state('cadastrarUsuario', {
        url: '/cadastrarUsuario',
        templateUrl: '/views/cadastrarUsuario.html',
        controller: 'UsuarioController'
    });
});