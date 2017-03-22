package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public void setRepository(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario create(Usuario usuario) {
        System.out.println(usuario + "estah sendo criado");
        return repository.save(usuario);
    }

    public Usuario getById(Long id) {
        return repository.findOne(id);
    }

    public Usuario getByEmail(String email) {
        System.out.println(email + "estah sendo retornado");
        return repository.findByEmail(email);
    }

    public List<Usuario> getAll() {
        return repository.findAll();
    }

    public boolean update(Usuario usuario) {
        System.out.println(usuario + "estah sendo atualizado");

        if (repository.exists(usuario.getId())) {
            repository.save(usuario);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (repository.exists(id)) {
            repository.delete(id);
            return true;
        }
        return false;
    }

    public Usuario getUsuarioByEmailAndSenha(String email, String senha){
        return repository.findByEmailAndSenha(email, senha);
    }
}
