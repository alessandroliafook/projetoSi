package br.edu.ufcg.computacao.si1.model.usuario;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

    @Column
    @DecimalMin(value = "0.0")
    private double saldo;

    @OneToMany
    @Column(name = "id_anuncios")
    private List<Long> idsAnuncios;

    @OneToMany
    @Column
    private List<Notificacao> notificacoes;

    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario, double saldo,  List<Long>
            IdsAnuncios, List<Notificacao> notificacoes) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipoUsuario;
        this.saldo = saldo;
        this.idsAnuncios = IdsAnuncios;
        this.notificacoes = notificacoes;
    }

    public Usuario() {
        this.nome = "default";
        this.email = "default@default";
        this.senha = "12345678";
        this.tipo = TipoUsuario.FISICO;
        this.saldo = 0d;
        this.idsAnuncios = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
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

    public void setNome(String nome) {
        this.nome = nome;
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Long> getIdsAnuncios() {
        return idsAnuncios;
    }

    public void setIdsAnuncios(List<Long> idsAnuncios) {
        this.idsAnuncios = idsAnuncios;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public Usuario copiaSemSenha() {
        return new Usuario(getNome(), getEmail(), "", getTipo(), getSaldo(), getIdsAnuncios(), getNotificacoes());
    }

    public boolean cadastrarAnuncio(Long idAnuncio){
        return getIdsAnuncios().add(idAnuncio);
    }

    public boolean deletarAnuncio(Long idAnuncio) {
        return getIdsAnuncios().remove(idAnuncio);
    }

    public void realizarCompra(double preco) {
        setSaldo(getSaldo() - preco);
    }

    public void realizarVenda(double preco){
        setSaldo(getSaldo() + preco);
    }

    public boolean notificar(Notificacao notificacao) {
        return getNotificacoes().add(notificacao);
    }
}
