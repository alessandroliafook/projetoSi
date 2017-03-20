package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.repository.TokenRepository;

/**
 * Created by Alessandro Fook on 18/03/2017.
 */
public class AutenticacaoService {

	TokenRepository repository;

	public Token getToken(String key){
		return repository.findByKey(key);
	}

	public Token saveToken(Token token) {
		return repository.save(token);
	}

	public void deleteToken(Token token) {
		repository.delete(token);
	}

	public Token findTokenByKey(String key) {
		return repository.findByKey(key);
	}

}
