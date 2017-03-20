package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alessandro Fook on 18/03/2017.
 */
@Service
public class AutenticacaoService {

	@Autowired
	TokenRepository repository;

	public Token saveToken(Token token) {
		return repository.save(token);
	}

	public void deleteToken(Token token) {
		repository.delete(token);
	}

	public Token getTokenByKey(String key) {
		return repository.findByKey(key);
	}

	public Token generateToken(Usuario usuario) {
		return saveToken(new Token(usuario));
	}

}
