package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.model.form.UsuarioForm;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario create(Usuario usuario);

    Usuario getById(Long id);

    Usuario getByEmail(String email);

    List<Usuario> getAll();

    boolean update(Usuario usuario);

    boolean delete(Long id);
}
