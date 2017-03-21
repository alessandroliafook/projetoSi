app.controller('UsuarioController', function ($http, $scope, $mdToast, $mdDialog, Auth, UserFactory) {

    var self = this;
    $scope.inputDataModel = {
        name: "",
        email: "",
        password: "",
        repeatPassword: "",
        type: {FISICO: false, JURIDICO: false}
    };
    $scope.inputData = angular.copy($scope.inputDataModel);


    $scope.checkPassword = function () {
        return $scope.inputData.password !== $scope.inputData.repeatPassword;
    };

    $scope.checkData = function () {
        return $scope.checkPassword() || self.checkType() || self.checkEmptyPassword();
    };

    self.checkEmptyPassword = function () {
        return $scope.inputData.password.length === 0;
    };

    self.checkType = function () {
        return (!$scope.inputData.type['FISICO'] && !$scope.inputData.type['JURIDICO']);
    };

    $scope.clearInput = function () {
        $scope.inputData = angular.copy($scope.inputDataModel);
    };

    $scope.cadastrar = function () {

        var usuario = UserFactory.createUser($scope.inputData);
        console.log(usuario);

        $http({
            method: 'POST',
            url: "/usuario/cadastro",
            data: usuario,
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).success(function (data, status) {

            console.log(data + "\n" + status);

        }).error(function (err) {

            console.log(err);
        });
    };

});