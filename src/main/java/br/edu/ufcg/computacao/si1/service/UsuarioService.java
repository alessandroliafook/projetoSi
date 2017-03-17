package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario create(Usuario usuario) {
        System.out.println(usuario + "estah sendo criado");
        return usuarioRepository.save(usuario);
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findOne(id);
    }

    public Usuario getByEmail(String email) {
        System.out.println(email + "estah sendo retornado");
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public boolean update(Usuario usuario) {
        System.out.println(usuario + "estah sendo atualizado");

        if (usuarioRepository.exists(usuario.getId())) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (usuarioRepository.exists(id)) {
            usuarioRepository.delete(id);
            return true;
        }
        return false;
    }
}
