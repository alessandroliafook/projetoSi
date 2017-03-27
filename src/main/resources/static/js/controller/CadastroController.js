app.controller('CadastroController', function ($http, $scope, $state, AnuncioFactory, Auth, ModalService) {

    var self = this;

    self.inputDataModel = {
        title: "",
        price: undefined,
        type: {'MOVEL': false, 'IMOVEL': false, 'EMPREGO': false, 'SERVICO': false}
    };
    $scope.inputData = angular.copy(self.inputDataModel);
    $scope.esperandoRequisicoes = false;

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

    $scope.cadastrar = function (event) {

        $scope.esperandoRequisicoes = true;

        var anuncio = AnuncioFactory.create($scope.inputData);

        var promise = self.getUserTypePromise();

        promise.then(function (data) {

            var tipoUsuario = data.data;

            if (anuncio.tipo == "SERVICO" && tipoUsuario !== "JURIDICO") {
                ModalService.showConfirm(event, "Não autorizado", "Apenas pessoas jurídicas podem oferecer serviços.");
                $scope.esperandoRequisicoes = false;
            }

            else {
                self.sendData(anuncio, event);
            }
        });
    };

    $scope.logout = function () {
        Auth.logout();
        $state.go('login');
    };

    self.allFalse = function (array) {
        var cont = 0;
        Object.keys(array).forEach(function (key) {
            if (array[key]) cont += 1;
        });
        return cont == 0;
    };

    self.getUserTypePromise = function () {
        return $http({
            method: "GET",
            url: "/usuario/tipo/",
            headers: {
                'token': Auth.getToken()
            }
        });
    };

    self.sendData = function (anuncio, event) {
        $http({
            method: "POST",
            url: "/usuario/anuncio/",
            data: anuncio,
            headers: {
                'token': Auth.getToken(),
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).success(function (data, status) {
            console.log(JSON.stringify(data) + "\n" + status);
            ModalService.showConfirm(event, "Cadastro realizado", "Cadastro realizado com sucesso!");
            $scope.esperandoRequisicoes = false;
            $state.go('main');
        }).error(function (err) {
            console.log(err);
            $scope.esperandoRequisicoes = false;
            ModalService.showConfirm(event, "Cadastro não realizado", "Insira os dados corretamente!");
        });
    };


    if (Auth.getToken() == null) {
        alert("Você não está autenticado, para acessar essa página realize login.");
        $state.go('login');
    }


});