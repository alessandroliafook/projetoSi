package br.edu.ufcg.computacao.si1.model.usuario;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "O nome do usuário não pode ser vazio!")
    private String nome;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "O email não pode ser vazio!")
    @Email
    private String email;

    @Column(nullable = false)
    @Size(min = 8, message = "A senão não pode ter menos de 8 dígitos!")
    private String senha;

    @Column(nullable = false)
    @NotEmpty(message = "Escolha um tipo válido para o usuário!")
    private TipoUsuario tipo;

    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipoUsuario;
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
