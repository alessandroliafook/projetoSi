package br.edu.ufcg.computacao.si1;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alessandro Fook on 17/03/2017.
 */
public class Fachada {

	@Autowired
	Controller controller;

	@RequestMapping(value = "/user/anuncio/listar", method = RequestMethod.GET)
	public
	@ResponseBody
	List<Anuncio> listarAnuncios(String tokenKey) {
		return controller.listarAnuncios(tokenKey);
	}

	@RequestMapping(value = "/user/anuncio/cadastro", method = RequestMethod.POST)
	public
	@ResponseBody
	Anuncio cadastroAnuncio(@Valid Anuncio anuncio, String tokenKey) {
		return controller.cadastrarAnuncio(anuncio, tokenKey);
	}

	@RequestMapping(value = "/usuario/cadastro", method = RequestMethod.POST)
	public
	@ResponseBody
	Usuario cadastro(@Valid Usuario usuario) {
		return controller.cadastroUsuario(usuario);
	}

	@RequestMapping(value = "/autentication", method = RequestMethod.POST)
	public
	@ResponseBody
	Token generateToken(String email, String senha) {
		return controller.generateToken(email, senha);
	}
}
