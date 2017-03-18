package br.edu.ufcg.computacao.si1.model.autenticacao;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Alessandro Fook on 18/03/2017.
 */
@Entity
public class Token {

	private final int HALF_HOUR_IN_MILISECONDS = 1800000;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String key;
	@Column
	private Date expirationDate;
	@OneToOne
	private Usuario usuario;

	public Token(Usuario usuario){
		expirationDate = new Date();
		expirationDate.setTime(System.currentTimeMillis() + HALF_HOUR_IN_MILISECONDS);
		key = UUID.randomUUID().toString();
		this.usuario = usuario;
	}

	public String getKey() {
		return key;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
