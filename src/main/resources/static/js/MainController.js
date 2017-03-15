app.controller('MainController', function ($scope, $state, Auth) {


    $scope.cadastrarAnuncio = function () {
        $state.go('cadastrarAnuncio');
    };

    $scope.mostraAnuncios = function () {
        $state.go("anuncios");
    };

    $scope.logout = function () {
        Auth.logout();
    };
    
});