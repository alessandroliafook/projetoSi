package br.edu.ufcg.computacao.si1;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.service.AnuncioService;
import br.edu.ufcg.computacao.si1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Alessandro Fook on 17/03/2017.
 */
public class Fachada {

	@Autowired
	AnuncioService anuncioService;

	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = "/user/cadastrar/anuncio", method = RequestMethod.GET)
	public @ResponseBody Anuncio cadastrarAnuncio(Anuncio anuncio){
		return anuncioService.getById(anuncio.get_id());
	}

	@RequestMapping(value = "/user/listar/anuncios", method = RequestMethod.GET)
	public @ResponseBody List<Anuncio> listarAnuncios(){
		return anuncioService.getAll();
	}

	@RequestMapping(value = "/user/cadastrar/anuncio", method = RequestMethod.POST)
	public @ResponseBody Anuncio  cadastroAnuncio(Anuncio anuncio){
		 return anuncioService.create(anuncio);
	}


	@RequestMapping(value = "/cadastrar-se", method = RequestMethod.GET)
	public @ResponseBody Usuario getPageCadastro(Usuario usuario){
		return usuarioService.getById(usuario.getId());
	}

	@RequestMapping(value = "/cadastrar-se", method = RequestMethod.POST)
	public @ResponseBody Usuario cadastro(Usuario usuario){
		return usuarioService.create(usuario);
	}


}
