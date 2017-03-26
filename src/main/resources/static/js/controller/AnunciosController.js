app.controller('AnunciosController', function ($scope, $http, Auth, $state) {

    var self = this;

    $scope.imagePath = '/img/anuncioDefault.jpeg';
    $scope.anuncios;
    $scope.searchParam = "";

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
    }

});