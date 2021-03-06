app.controller('LoginController', function ($http, $scope, $mdToast, $state, Auth, ModalService) {

	$scope.estaLogando = false;

    $scope.login = function (usuario, event) {
        $scope.estaLogando = true;
        $http({
            method: 'POST',
            url: '/autenticacao/' + usuario.login + "/" + usuario.senha,
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'text/plain'
            }
        }).success(function (data, status) {

            if (data.length == 0) {
                ModalService.showConfirm(event, "Dados incorretos", "Tente novamente!");
                $scope.user = {}
            } else {
                Auth.saveToken(data);
                $state.go('main');
            }
            $scope.estaLogando = false;
        }).error(function(err){
            $scope.estaLogando = false;
            console.log(err);
        });
    };

	$scope.autenticacao = function () {

        $http({
            method: "GET",
            url: "/autenticacao"

        }).then(function(response) {
            console.log(response.data);
        });
        console.log("por aqui!!")

    };

    $scope.goToRegisterPage = function () {
        $state.go("cadastrarUsuario");
    }

});