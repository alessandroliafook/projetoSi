package br.edu.ufcg.computacao.si1;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.service.AnuncioService;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alessandro Fook on 17/03/2017.
 */
public class Fachada {


	AnuncioService anuncioService = new AnuncioServiceImpl();
	
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



}
