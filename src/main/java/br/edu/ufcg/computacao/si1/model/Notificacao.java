package br.edu.ufcg.computacao.si1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Alessandro Fook on 22/03/2017.
 */
@Entity
public class Notificacao {

	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String titulo;

	@Column
	private boolean visto;

	@Column
	private String mensagem;

	@Column
	private Long idUsuario;

	@Column
	private Long idAnuncio;

	public Notificacao(String titulo, String mensagem, Long idUsuario, Long idAnuncio){
		setTitulo(titulo);
		setMensagem(mensagem);
		setIdUsuario(idUsuario);
		setIdAnuncio(idAnuncio);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(Long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}
}
