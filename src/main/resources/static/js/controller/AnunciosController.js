app.controller('AnunciosController', function ($scope, $http, Auth) {

    var self = this;

    $scope.imagePath = '/img/anuncioDefault.jpeg';
    $scope.anuncios;

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

});