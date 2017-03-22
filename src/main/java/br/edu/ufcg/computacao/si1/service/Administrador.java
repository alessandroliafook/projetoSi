package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Notificacao;
import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
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

			Token token = autenticacaoService.getTokenByChave(chave);
			Usuario usuario = usuarioService.getById(token.getUsuarioId());

			usuarioService.adicionarAnuncio(usuario, anuncio.getId());

			return anuncioService.create(anuncio);
		}

		return null;
	}

	public Notificacao venderAnuncio(Anuncio anuncio, String chave){

		if (validarToken(chave)){

			Token token = autenticacaoService.getTokenByChave(chave);
			Usuario comprador = usuarioService.getById(token.getUsuarioId());
			Usuario vendedor = usuarioService.getById(anuncio.getUsuarioId());
			double valorDaVenda = anuncio.getPreco();

			comprador.realizarCompra(valorDaVenda);
			vendedor.realizarVenda(valorDaVenda);

			String titulo = "Venda de anuncio";
			String mensagem = "O usuário " + comprador.getNome() + " realizou a compra do anuncio " + anuncio.getTitulo();

			Notificacao notificacao = new Notificacao(titulo, mensagem, anuncio.getUsuarioId(), anuncio.getId());

			return notificacaoService.save(notificacao);
		}

		return null;
	}

	public List<Anuncio> listarAnuncios(String chave) {

		if (validarToken(chave)){

			List<Anuncio> anuncios = anuncioService.getAll();
			List<Anuncio> anunciosDisponiveis = new ArrayList<>();

			for (Anuncio anuncio :	anuncios) {
				if (anuncio.isVendido() == false)
					anunciosDisponiveis.add(anuncio);
			}

			return anunciosDisponiveis;
		}
		return null;
	}

	public Anuncio getAnuncio(Long id, String chave){return (validarToken(chave)) ? anuncioService.getById(id) : null;}

	// metodos de notificação
	public List<Notificacao> listarNotificacoesDoUsuario(Long idUsuario, String chave){

			return (validarToken(chave)) ?
			notificacaoService.getNotificacoesUsuario(idUsuario) : null;
	}

	public Notificacao visualizarNotificacao(Long id, String chave){

		if (validarToken(chave)){

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

	public Usuario getUsuario(Long id, String chave){
		return (validarToken(chave)) ? usuarioService.getById(id) : null;
	}

	//metodos de seguranca
	public String autenticacao(String email, String senha) {

		Usuario usuario = usuarioService.getUsuarioByEmailAndSenha(email, senha);
		return generateToken(usuario);

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
