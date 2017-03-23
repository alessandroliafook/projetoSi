package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.usuario.Notificacao;
import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.anuncio.TipoAnuncio;
import br.edu.ufcg.computacao.si1.model.autenticacao.Token;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	@Autowired
	NotificacaoService notificacaoService;

	//metodos de anuncio
	public Anuncio cadastrarAnuncio(Anuncio anuncio, String chave) {

		if (validarToken(chave)) {
			adicionarAnuncioAoUsuario(anuncio, chave);

			return anuncioService.create(anuncio);
		}

		return null;
	}

	public Notificacao venderAnuncio(Anuncio anuncio, String chave) {

		if (validarToken(chave) && !anuncio.getTipo().equals(TipoAnuncio.EMPREGO)) {

			Token token = autenticacaoService.getTokenByChave(chave);
			Usuario comprador = usuarioService.getById(token.getUsuarioId());
			Usuario vendedor = usuarioService.getById(anuncio.getUsuarioId());

			if (!comprador.equals(vendedor)) {

				realizarNegocio(anuncio, comprador, vendedor);

				return notificar(anuncio, comprador, vendedor);
			}
		}
		return null;
	}

	public List<Anuncio> listarAnuncios(String chave) {

		if (validarToken(chave)) {
			List<Anuncio> anuncios = anuncioService.getAll();

			return filtrarAnunciosDisponiveis(anuncios);
		}
		return null;
	}

	public Anuncio getAnuncio(Long id, String chave) {
		return (validarToken(chave)) ? anuncioService.getById(id) : null;
	}

	// metodos de notificação
	public List<Notificacao> listarNotificacoesDoUsuario(Long idUsuario, String chave) {

		if (validarToken(chave)) {

			return getNotificacoesDoUsuario(idUsuario);
		}

		return null;
	}

	public Notificacao visualizarNotificacao(Long id, String chave) {

		if (validarToken(chave)) {

			Notificacao notificacao = notificacaoService.getNotificacao(id);
			notificacao.setVisto(true);

			return notificacaoService.save(notificacao);
		}

		return null;
	}

	//metodos do usuario
	public Usuario cadastrarUsuario(Usuario usuario) {
		Usuario novoUsuario = usuarioService.create(usuario);
		return (novoUsuario != null) ? novoUsuario.copiaSemSenha() : null;
	}

	public Usuario getUsuario(Long id, String chave) {
		return (validarToken(chave)) ? usuarioService.getById(id) : null;
	}

	//metodos de seguranca
	public String autenticacao(String email, String senha) {

		Usuario usuario = usuarioService.getUsuarioByEmailAndSenha(email, senha);
		return generateToken(usuario);

	}

	// métodos auxiliares

	private void adicionarAnuncioAoUsuario(Anuncio anuncio, String chave) {

		Token token = autenticacaoService.getTokenByChave(chave);
		Usuario usuario = usuarioService.getById(token.getUsuarioId());

		usuarioService.adicionarAnuncio(usuario, anuncio);
	}

	private void realizarNegocio(Anuncio anuncio, Usuario comprador, Usuario vendedor) {

		double valorDaVenda = anuncio.getPreco();

		comprador.realizarCompra(valorDaVenda);
		vendedor.realizarVenda(valorDaVenda);
		anuncio.realizarVendido();
	}

	private Notificacao notificar(Anuncio anuncio, Usuario comprador, Usuario vendedor) {

		String titulo = "Venda de anuncio";
		String mensagem = "O usuário " + comprador.getNome() + " realizou a compra do anuncio " + anuncio.getTitulo();

		Notificacao notificacao = new Notificacao(titulo, mensagem, anuncio.getUsuarioId(), anuncio.getId());
		vendedor.notificar(notificacao);

		return notificacaoService.save(notificacao);
	}

	private List<Notificacao> getNotificacoesDoUsuario(Long idUsuario) {

		Usuario usuario = usuarioService.getById(idUsuario);

		return usuario.getNotificacoes();
	}

	private List<Anuncio> filtrarAnunciosDisponiveis(List<Anuncio> anuncios) {

		List<Anuncio> anunciosDisponiveis = new ArrayList<>();

		for (Anuncio anuncio : anuncios) {
			if (anuncio.isVendido() == false)
				anunciosDisponiveis.add(anuncio);
		}
		return anunciosDisponiveis;
	}

	private String generateToken(Usuario usuario) {

		if (usuario != null) {
			Token token = autenticacaoService.getTokenByUsuarioId(usuario.getId());

			if (token == null)
				token = autenticacaoService.generarToken(usuario.getId());

			System.out.println(token.getChave());
			return token.getChave();
		}
		return "";
	}

	private boolean validarToken(String chave) {

		Token token = autenticacaoService.getTokenByChave(chave);

		if (token == null)
			return false;

		else if (token.getExpirationDate().getTime() < System.currentTimeMillis()) {
			autenticacaoService.deletarToken(token);
			return false;

		} else
			return true;
	}
}
