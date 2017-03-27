app.controller('MainController', function ($scope, $state, $mdDialog, $mdSidenav, Auth, Menu) {

    $scope.optionsMenu = Menu.getMenuOptionsAccount();
    $scope.menuList = Menu.getMenuList();
    $scope.heightWindow = window.innerHeight;

    $scope.menu = Menu;

    $scope.usuario = {};
    $scope.carregandoUsuario = true;

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

    $scope.changeSidenav = function() {
        Menu.changeSidenav();
    }

    var pegaUsuario = function() {
        $scope.carregandoUsuario = true;
        $http.get("/usuario/").success(function (data) {
            if (data.length != 0) {
                $scope.usuario = data;
                console.log(data);
            }
            $scope.carregandoUsuario = false;
        }).error(function(err) {
            console.log(err);
            $scope.carregandoUsuario = false;
        });
    }

    var pegaSaldo = function() {
        $scope.carregandoSaldo = true;
        $http.get("/usuario/saldo/").success(function (data) {
            console.log(data);
            if (data.length != 0) {
                $scope.usuario.saldo = data;
            }
            $scope.carregandoSaldo = false;
            
        }).error(function(err) {
            $scope.carregandoSaldo = false;
            console.log(err);
        });
    }

    pegaUsuario();
});