app.controller('LoginController', function($http, $scope, $mdToast, $mdDialog, Auth) {

	$scope.estaLogando = false;

	$scope.login = function(usuario) {
        $scope.estaLogando = true;
        $http.post("/autenticacao/", usuario).success(function(data, status) {
            if (status == 202) {
                Auth.saveToken(data.token);
                $state.go('main');
            } else if (status == 203) {
                $scope.dadosIncorretos = true;
                $scope.user = {}
            }
            $scope.estaLogando = false;
        }).error(function(err){
            $scope.estaLogando = false;
            console.log(err);
        });
    };
    
});