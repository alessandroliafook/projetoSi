package br.edu.ufcg.computacao.si1;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alessandro Fook on 17/03/2017.
 */
public class Fachada {

	@Autowired
	Controller controller;

	@RequestMapping(value = "/user/anuncio/listar", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Anuncio>> listarAnuncios(String tokenKey) {
		List<Anuncio> lista = controller.listarAnuncios(tokenKey);

		return (lista == null) ?
				new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION) :
				new ResponseEntity<>(lista, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/user/anuncio/cadastro", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Anuncio> cadastroAnuncio(@Valid Anuncio anuncio, String tokenKey) {
		Anuncio anuncioSalvo = controller.cadastrarAnuncio(anuncio, tokenKey);

		return (anuncioSalvo == null) ?
				new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION) :
				new ResponseEntity<>(anuncioSalvo, HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/usuario/cadastro", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Usuario> cadastro(@Valid Usuario usuario) {

		Usuario usuarioSalvo = controller.cadastroUsuario(usuario);

		return (usuarioSalvo == null) ?
				new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION) :
				new ResponseEntity<>(usuarioSalvo, HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/autentication", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> generateToken(String email, String senha) {

		Token token = controller.generateToken(email, senha);

		return (token == null) ?
				new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION) :
				new ResponseEntity<>(token.getKey(), HttpStatus.ACCEPTED);
	}
}
