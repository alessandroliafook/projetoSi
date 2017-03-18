package br.edu.ufcg.computacao.si1;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.service.AnuncioService;
import br.edu.ufcg.computacao.si1.service.AutenticacaoService;
import br.edu.ufcg.computacao.si1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Alessandro Fook on 18/03/2017.
 */
public class Controller {

	@Autowired
	AnuncioService anuncioService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	AutenticacaoService autenticacaoService;

	public List<Anuncio> listarAnuncios(String tokenKey){
		return (validarToken(tokenKey)) ? anuncioService.getAll() : null;
	}

	public Anuncio  cadastrarAnuncio(Anuncio anuncio, String tokenKey){
		return (validarToken(tokenKey)) ? anuncioService.create(anuncio) : null;
	}

	public Usuario cadastroUsuario(Usuario usuario){
		return usuarioService.create(usuario);
	}

	public Token generateToken(String email, String senha){

		Usuario usuario = usuarioService.getUsuarioByEmailAndSenha(email, senha);
		return (usuario != null) ? autenticacaoService.saveToken(new Token(usuario)) : null;
	}

	private boolean validarToken(String tokenKey){
		return autenticacaoService.getToken(tokenKey) != null;
	}
}
