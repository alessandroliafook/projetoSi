package br.edu.ufcg.computacao.si1.model.autenticacao;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Alessandro Fook on 18/03/2017.
 */
@Entity(name = "Token")
@Table(name = "tb_token")
public class Token {

	private final int DIA_IN_MILISECONDS = 86400000;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String chave;
	@Column
	private Date expirationDate;
	@Column(unique = true)
	private Long usuarioId;

	public Token(Long usuarioId){
		setExpirationDate(new Date());
		getExpirationDate().setTime(System.currentTimeMillis() + DIA_IN_MILISECONDS);
		setChave(UUID.randomUUID().toString());
		setUusuarioId(usuarioId);
	}

    public Token() {
    }

	public String getChave() {
		return chave;
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

	public void setChave(String chave) {
		this.chave = chave;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUusuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
