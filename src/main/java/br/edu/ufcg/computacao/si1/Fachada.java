package br.edu.ufcg.computacao.si1;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.service.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alessandro Fook on 17/03/2017.
 */
@RestController
public class Fachada {

	@Autowired
	Administrador administrador;

	@RequestMapping(value = "/usuario/anuncio/{token}", method = RequestMethod.POST)
	public
	@ResponseBody
	Anuncio cadastrarAnuncio(@RequestBody Anuncio anuncio, @PathVariable String token) {
		return administrador.cadastrarAnuncio(anuncio, token);
	}

	@RequestMapping(value = "/usuario/anuncio/listar/{token}", method = RequestMethod.GET)
	public
	@ResponseBody
	List<Anuncio> listarAnuncios(@PathVariable String token) {
		return administrador.listarAnuncios(token);
	}

	@RequestMapping(value = "/usuario/cadastro", method = RequestMethod.POST)
	public
	@ResponseBody
	Usuario cadastro(@RequestBody Usuario usuario) {
		return administrador.cadastro(usuario);
	}

	@RequestMapping(value = "/autenticacao/{email}/{senha}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public
	@ResponseBody
	String autenticacao(@PathVariable String email, @PathVariable String senha) {
		return administrador.autenticacao(email, senha);
	}
}
