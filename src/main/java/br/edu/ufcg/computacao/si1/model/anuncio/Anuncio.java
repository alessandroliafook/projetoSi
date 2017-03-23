package br.edu.ufcg.computacao.si1.model.anuncio;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Marcus Oliveira on 08/12/16.
 */
@Entity
@Table(name="tb_anuncio")
public class Anuncio {

    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "titulo", nullable = false)
    @NotEmpty(message = "O título não pode ser vazio")
    private String titulo;

    @Column(name = "data_criacao", nullable = false)
    private Date dataDeCriacao;

    @DecimalMin(value = "0.01", message = "Preco não pode ser menor que 0.01")
    @Column(name = "preco", nullable = false)
    private double preco;

    @DecimalMin(value = "0.0", message = "Nota não pode ser menor que 0.0")
    @DecimalMax(value = "5.0", message = "Nota não pode ser maior que 5.0")
    @Column(name = "nota")
    private double nota;

    @Column(name = "tipo", nullable = false)
    private TipoAnuncio tipo;

    @ManyToOne
    @Column(name = "id_usuario_dono")
    private Long usuarioId;

    @Column
    private boolean vendido;

    /**
     * Retorna o id do anuncio
     * @return o id do anuncio
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica o id do anuncio
     * @param id id a ser colocado no anuncio
     */public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataDeCriacao() {
        return DATE_FORMAT.format(dataDeCriacao);
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public TipoAnuncio getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnuncio tipo) {
        this.tipo = tipo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public void realizarVendido(){ setVendido(true);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anuncio)) return false;

        Anuncio anuncio = (Anuncio) o;

        if (Double.compare(anuncio.getPreco(), getPreco()) != 0) return false;
        if (!getId().equals(anuncio.getId())) return false;
        if (!getTitulo().equals(anuncio.getTitulo())) return false;
        if (!getDataDeCriacao().equals(anuncio.getDataDeCriacao())) return false;
        if (Double.compare(anuncio.getNota(), getNota()) != 0) return false;
        return getTipo().equals(anuncio.getTipo());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId().hashCode();
        result = 31 * result + getTitulo().hashCode();
        result = 31 * result + getDataDeCriacao().hashCode();
        temp = Double.doubleToLongBits(getPreco());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getNota());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getTipo().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", dataDeCriacao=" + getDataDeCriacao() +
                ", preco=" + preco +
                ", nota=" + nota +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
