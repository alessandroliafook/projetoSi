app.controller('LoginController', function ($http, $scope, $mdToast, $mdDialog, $state, Auth) {

	$scope.estaLogando = false;

	$scope.login = function(usuario) {
        $scope.estaLogando = true;
        console.log(usuario.login + " senha = " + usuario.senha);
        $http({
            method: 'GET',
            url: '/autenticacao/' + usuario.login + "/" + usuario.senha,
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'text/plain'
            }
        }).success(function (data, status) {

            if (data.length == 0) {
                $scope.dadosIncorretos = true;
                confirm("Dados incorretos. Tente novamente");
                $scope.user = {}
            } else {
                Auth.saveToken(data);
                confirm("Login realizado. Ok para ser redirecionado.");
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
});