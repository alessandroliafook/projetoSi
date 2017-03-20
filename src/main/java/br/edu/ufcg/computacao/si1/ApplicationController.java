package br.edu.ufcg.computacao.si1;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.service.AnuncioService;
import br.edu.ufcg.computacao.si1.service.AutenticacaoService;
import br.edu.ufcg.computacao.si1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alessandro Fook on 17/03/2017.
 */
@RestController
public class ApplicationController {

	@Autowired
	AnuncioService anuncioService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	AutenticacaoService autenticacaoService;
	
	@RequestMapping(value = "/user/cadastrar/anuncio/{token}", method = RequestMethod.GET)
	public @ResponseBody Anuncio cadastrarAnuncio(@RequestBody Anuncio anuncio, @PathVariable String token){
		return (validarToken(token)) ? anuncioService.getById(anuncio.get_id()) : null;
	}

	@RequestMapping(value = "/user/listar/anuncios/{token}", method = RequestMethod.GET)
	public @ResponseBody List<Anuncio> listarAnuncios(@PathVariable String token){
		return (validarToken(token)) ? anuncioService.getAll() : null;
	}

	@RequestMapping(value = "/user/cadastrar/anuncio/{token}", method = RequestMethod.POST)
	public @ResponseBody Anuncio  cadastroAnuncio(@RequestBody Anuncio anuncio, @PathVariable String token){
		 return (validarToken(token)) ? anuncioService.create(anuncio) : null;
	}

	@RequestMapping(value = "/cadastrar-se", method = RequestMethod.POST)
	public @ResponseBody Usuario cadastro(Usuario usuario){
		return usuarioService.create(usuario);
	}

	@RequestMapping(value = "/autenticacao/{email}/{senha}", method = RequestMethod.GET)
	public @ResponseBody String autenticacao(@PathVariable String email, @PathVariable String senha){

		Usuario usuario = usuarioService.getUsuarioByEmailAndSenha(email, senha);
		Token token = null;

		if (usuario != null)
			token = autenticacaoService.generateToken(usuario);

		return (token != null) ? token.getKey() : null;
	}

	private boolean validarToken(String tokenKey){

		Token token = autenticacaoService.getTokenByKey(tokenKey);

		if(token == null)
			return false;

		else if(token.getExpirationDate().getTime() < System.currentTimeMillis()) {
			autenticacaoService.deleteToken(token);
			return false;
		}

		else
			return true;
	}
}
