package br.edu.ufcg.computacao.si1.model.usuario;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotEmpty(message = "O nome não pode ser vazio")
    private String nome;

    @Column(unique = true)
    @NotEmpty(message = "O email não pode ser vazio")
    private String email;

    @Column
    @Size(min = 8, message = "A senha não pode conter menos de 8 caracteres")
    private String senha;

    @Column(nullable = false)
    private TipoUsuario tipo;

    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipoUsuario;
    }

    public Usuario() {
        this.nome = "default";
        this.email = "default@default";
        this.senha = "12345678";
        this.tipo = TipoUsuario.FISICO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipoUsuario) {
        this.tipo = tipoUsuario;
    }

}
