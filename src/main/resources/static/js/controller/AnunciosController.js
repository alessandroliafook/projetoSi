app.controller('AnunciosController', function ($scope, $http, Auth, $state, $mdDialog, $mdSidenav, ModalService, Menu) {

    var self = this;

    $scope.imagePath = '/img/anuncioDefault.jpeg';
    $scope.anuncios;
    $scope.searchParam = "";
    $scope.currentNavItem = "moveis";

    $scope.optionsMenu = Menu.getMenuOptionsAccount();
    $scope.menuList = Menu.getMenuList();
    $scope.heightWindow = window.innerHeight;

    $scope.menu = Menu;

    $scope.carregandoRequisicaoAnuncio = false;

    $scope.goToMain = function () {
        $state.go('main');
    };

    $scope.populate = function () {
        $http.get("/anuncio/listar/").success(function (data, status) {
            $scope.anuncios = data;
        }).error(function (err) {
            console.log(err);
        });
    };

    $scope.logout = function () {
        Auth.logout();
        $state.go('login');
    };

    $scope.isSearchFiltered = function (anuncio) {

        if ($scope.searchParam.length == 0) return true;

        var canShow = false;
        Object.keys(anuncio).forEach(function (key) {
            if (JSON.stringify(anuncio[key]).indexOf($scope.searchParam) !== -1) canShow = true;
        });

        return canShow;
    };

    if (Auth.getToken() == null) {
        alert("Você não está autenticado, para acessar essa página realize login.");
        $state.go('login');
    }


    $scope.compraAnuncio = function(anuncio, event) {
        $scope.carregandoRequisicaoAnuncio = true;
        $http.post("/anuncio/vender/"+anuncio.id).success(function (data) {
            if (data.length != 0) {
                var index = $scope.anuncios.indexOf(anuncio);
                if (index > -1) {
                    $scope.anuncios.splice(index, 1);
                }
                ModalService.showConfirm(event, "Compra efetuada", "Sua conta foi efetuada com sucesso e descontada do seu saldo.");
            }
            $scope.carregandoRequisicaoAnuncio = false;
        }).error(function(err) {
            console.log(err);
            $scope.carregandoRequisicaoAnuncio = false;
        });
    }

    var pegaUsuario = function() {
        $scope.carregandoUsuario = true;
        $http.get("/usuario/").success(function (data) {
            if (data.length != 0) {
                $scope.usuario = data;
            }
            $scope.carregandoUsuario = false;
        }).error(function(err) {
            console.log(err);
            $scope.carregandoUsuario = false;
        });
    }

    pegaUsuario();

});