app.service('UserFactory', function () {

    var self = this;

    self.getTipo = function (userData) {
        if (userData.type['FISICA']) return 'FISICA';
        else return 'JURIDICA';
    };

    return {

        createUser: function (userData) {
            var tipoPessoa = self.getTipo(userData);
            return {nome: userData.name, email: userData.email, senha: userData.password, tipo: tipoPessoa}
        }

    }

});