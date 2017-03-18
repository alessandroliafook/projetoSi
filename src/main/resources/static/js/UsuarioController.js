app.controller('UsuarioController', function ($http, $scope, $mdToast, $mdDialog, Auth, UserFactory) {

    var self = this;
    $scope.inputDataModel = {
        name: "",
        email: "",
        password: "",
        repeatPassword: "",
        type: {FISICA: false, JURIDICA: false}
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
        return (!$scope.inputData.type['FISICA'] && !$scope.inputData.type['JURIDICA']);
    };

    $scope.clearInput = function () {
        $scope.inputData = angular.copy($scope.inputDataModel);
    };

    $scope.cadastrar = function () {

        var usuario = UserFactory.createUser($scope.inputData);
        console.log(usuario);

        $http.post("/cadastrar-se", usuario).success(function (data, status) {

            console.log(data);

        }).error(function (err) {

            console.log(err);
        });
    };

});