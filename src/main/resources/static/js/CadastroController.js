app.controller('CadastroController', function ($http, $scope, AnuncioFactory, Auth) {

    var self = this;

    self.inputDataModel = {title: "", price: undefined, type: {'MOVEL': false, 'IMOVEL': false, 'EMPREGO': false}};
    $scope.inputData = angular.copy(self.inputDataModel);

    $scope.isDisabled = function (typeKey) {

        if (self.allFalse($scope.inputData.type))
            return false;

        var howManyTrue = 0;

        Object.keys($scope.inputData.type).forEach(function (key) {
            if (key != typeKey && $scope.inputData.type[key]) howManyTrue += 1;
        });

        if (howManyTrue == 0 && $scope.inputData.type[typeKey]) return false;
        return true;
    };

    $scope.checkType = function () {
        return self.allFalse($scope.inputData.type);
    };

    $scope.clearInput = function () {
        $scope.inputData = angular.copy(self.inputDataModel);
    };

    $scope.cadastrar = function () {

        var anuncio = AnuncioFactory.create($scope.inputData);

        $http({
            method: "POST",
            url: "/usuario/anuncio/" + Auth.getToken(),
            data: anuncio,
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

    self.allFalse = function (array) {
        var cont = 0;
        Object.keys(array).forEach(function (key) {
            if (array[key]) cont += 1;
        });
        return cont == 0;
    };



});