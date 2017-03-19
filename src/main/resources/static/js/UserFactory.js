app.service('UserFactory', function () {

    var self = this;

    self.getTipo = function (userData) {
        if (userData.type['FISICA']) return 'FISICA';
        else return 'JURIDICA';
    };

    var teste = function (nome, email, senha, tipo, id) {
        this.nome= nome;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.tipo = tipo;
    };

    return {

        createUser: function (userData) {
            var tipoPessoa = self.getTipo(userData);
            return new teste(userData.name, userData.email, userData.password, tipoPessoa, 0);
            //return {'nome': userData.name, 'email': userData.email, 'senha': userData.password, 'tipo': tipoPessoa}
        }

    }

});