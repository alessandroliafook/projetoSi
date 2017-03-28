package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
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

	public void deletarToken(Token token) {
		repository.delete(token);
	}

	public Token getTokenByChave(String chave) {
		return repository.findByChave(chave);
	}

	public Token getTokenByUsuarioId(Long usuarioId){return repository.findByUsuarioId(usuarioId);}

	public Token generarToken(Long usuarioId) {
		return repository.save(new Token(usuarioId));
	}

}
