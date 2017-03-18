app.service('AnuncioFactory', function () {

    var self = this;

    self.getTipo = function (userData) {
        Object.keys(userData.type).forEach(function (key) {
            if (userData.type[key]) return key;
        });
    };

    return {

        create: function (userData) {
            var type = self.getTipo(userData);
            var currentDate = new Date();

            return {titulo: userData.title, preco: userData.price, tipo: type, nota: 0.0, dataDeCriacao: currentDate}
        }

    }

});