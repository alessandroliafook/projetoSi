package br.edu.ufcg.computacao.si1;

import br.edu.ufcg.computacao.si1.model.usuario.Notificacao;
import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.usuario.TipoUsuario;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.service.Administrador;
import ch.qos.logback.core.net.SyslogOutputStream;
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

	@RequestMapping(value = "/usuario/anuncio", method = RequestMethod.POST)
	public
	@ResponseBody
	Anuncio cadastrarAnuncio(@RequestBody Anuncio anuncio, @RequestHeader String token) {
		return administrador.cadastrarAnuncio(anuncio, token);
	}

	@RequestMapping(value = "/anuncio/listar", method = RequestMethod.GET)
	public
	@ResponseBody
	List<Anuncio> listarAnuncios(@RequestHeader String token) {
		return administrador.listarAnuncios(token);
	}


	@RequestMapping(value = "/anuncio/vender/{id}", method = RequestMethod.POST)
	public
	@ResponseBody
	Notificacao venderAnuncio(@PathVariable Long id, @RequestHeader String token) {
		return administrador.venderAnuncio(id, token);
	}

	@RequestMapping(value = "/anuncio/{id}", method = RequestMethod.GET)
	public
	@ResponseBody
	Anuncio getAnuncio(@PathVariable Long id, @RequestHeader String chave) {
		return administrador.getAnuncio(id, chave);
	}

	@RequestMapping(value = "/usuario/notificacao/listar/{idUsuario}", method = RequestMethod.GET)
	public
	@ResponseBody
	List<Notificacao> listarNotificacoesDoUsuario(@PathVariable Long idUsuario, @RequestHeader String chave) {
		return administrador.listarNotificacoesDoUsuario(idUsuario, chave);
	}

	@RequestMapping(value = "/usuario/notificacao/{id}")
	public
	@ResponseBody
	Notificacao visualizarNotificacao(@PathVariable Long id, @RequestHeader String chave) {
		return administrador.visualizarNotificacao(id, chave);
	}

	@RequestMapping(value = "/usuario/cadastro", method = RequestMethod.POST)
	public
	@ResponseBody
	Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
		return administrador.cadastrarUsuario(usuario);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
	public
	@ResponseBody
	Usuario getUsuario(@PathVariable Long id, @RequestHeader String chave) {
		return administrador.getUsuario(id, chave);
	}

	@RequestMapping(value = "/usuario/", method = RequestMethod.GET)
	public
	@ResponseBody
	Usuario getUsuario(@RequestHeader String token) {
		return administrador.getUsuario(token).copiaSemSenha();
	}

	@RequestMapping(value = "/autenticacao/{email}/{senha}", method = RequestMethod.POST, produces = MediaType
			.TEXT_PLAIN_VALUE)
	public
	@ResponseBody
	String autenticacao(@PathVariable String email, @PathVariable String senha) {
		return administrador.autenticacao(email, senha);
	}

	@RequestMapping(value = "/usuario/saldo/", method = RequestMethod.GET)
	public
	@ResponseBody
	double getSaldoUsuario(@RequestHeader String token) {
		return administrador.getUsuario(token).getSaldo();
	}

	@RequestMapping(value = "/usuario/nome/{id}", method = RequestMethod.GET, produces = MediaType
			.TEXT_PLAIN_VALUE)
	public
	@ResponseBody
	String getNomeUsuario(@PathVariable Long id, @RequestHeader String token) {
		return administrador.getUsuario(id, token).getNome();
	}

	@RequestMapping(value = "/usuario/tipo/", method = RequestMethod.GET)
	public
	@ResponseBody
	TipoUsuario getTipoUsuario(@RequestHeader String token) {
		return administrador.getUsuario(token).getTipo();
	}

}
