app.controller('MainController', function ($scope, $state, $mdDialog, Auth) {


    $scope.cadastrarAnuncio = function () {
        $state.go('cadastrarAnuncio');
    };

    $scope.mostraAnuncios = function () {
        $state.go("anuncios");
    };

    $scope.logout = function () {
        Auth.logout();
    };


    if (Auth.getToken() == null) {
        alert("Você não está autenticado, para acessar essa página realize login.")
        $state.go('login');
    }

    $scope.openMenu = function($mdMenu, ev) {
      originatorEv = ev;
      $mdMenu.open(ev);
    };

});