package br.edu.ufcg.computacao.si1;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alessandro Fook on 17/03/2017.
 */
@Controller
public class Fachada {

	Manager manager = new Manager();

	@RequestMapping(value = "/user/anuncio/listar", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Anuncio>> listarAnuncios(String tokenKey) {
		List<Anuncio> lista = manager.listarAnuncios(tokenKey);

		return (lista == null) ?
				new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION) :
				new ResponseEntity<>(lista, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/user/anuncio/cadastro", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Anuncio> cadastroAnuncio(@Valid Anuncio anuncio, String tokenKey) {
		Anuncio anuncioSalvo = manager.cadastrarAnuncio(anuncio, tokenKey);

		return (anuncioSalvo == null) ?
				new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION) :
				new ResponseEntity<>(anuncioSalvo, HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/usuario/cadastro", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Usuario> cadastro(@Valid Usuario usuario) {

		Usuario usuarioSalvo = manager.cadastroUsuario(usuario);

		return (usuarioSalvo == null) ?
				new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION) :
				new ResponseEntity<>(usuarioSalvo, HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/autentication", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> generateToken(String email, String senha) {

		Token token = manager.generateToken(email, senha);

		return (token == null) ?
				new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION) :
				new ResponseEntity<>(token.getKey(), HttpStatus.ACCEPTED);
	}
}
