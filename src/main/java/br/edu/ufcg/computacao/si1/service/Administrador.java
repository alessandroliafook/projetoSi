package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alessandro Fook on 20/03/2017.
 */
@Service
public class Administrador {

	@Autowired
	AnuncioService anuncioService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	AutenticacaoService autenticacaoService;

	public Anuncio cadastrarAnuncio(Anuncio anuncio, String token) {
		return (validarToken(token)) ? anuncioService.create(anuncio) : null;
	}

	public List<Anuncio> listarAnuncios(String token) {
		return (validarToken(token)) ? anuncioService.getAll() : null;
	}

	public Usuario cadastro(Usuario usuario) {
		Usuario novoUsuario = usuarioService.create(usuario);
		return (novoUsuario != null) ? novoUsuario.copiaSemSenha() : null;
	}

	public String autenticacao(String email, String senha) {

		Usuario usuario = usuarioService.getUsuarioByEmailAndSenha(email, senha);
		return generateToken(usuario);

	}

	private String generateToken(Usuario usuario) {

		if (usuario != null) {
			Token token = autenticacaoService.getTokenByUsuarioId(usuario.getId());

			if (token == null)
				token = autenticacaoService.generateToken(usuario.getId());

			System.out.println(token.getKey());
			return token.getKey();
		}
		return "";
	}

	private boolean validarToken(String tokenKey) {

		Token token = autenticacaoService.getTokenByKey(tokenKey);

		if (token == null)
			return false;

		else if (token.getExpirationDate().getTime() < System.currentTimeMillis()) {
			autenticacaoService.deleteToken(token);
			return false;
		} else
			return true;
	}
}
