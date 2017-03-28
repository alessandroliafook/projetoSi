app.controller('AnunciosController', function ($scope, $http, Auth, $state, ModalService) {

    var self = this;

    $scope.imagePath = '/img/anuncioDefault.jpeg';
    $scope.anuncios;
    $scope.searchParam = "";
    $scope.currentNavItem = "moveis";

    $scope.carregandoRequisicaoAnuncio = false;

    $scope.goToMain = function () {
        $state.go('main');
    };

    $scope.populate = function () {
        $http({
            method: "GET",
            url: "/anuncio/listar/",
            headers: {
                'token': Auth.getToken()
            }
        }).success(function (data, status) {
            console.log(JSON.stringify(data) + "\n" + status);
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


    $scope.compraAnuncio = function(anuncio) {
        $scope.carregandoRequisicaoAnuncio = true;
        console.log(anuncio);
        $http.post("/anuncio/vender/"+anuncio.id).success(function (data) {
            console.log(data);
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

});