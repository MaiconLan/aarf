package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(schema = "cadastro", name = "atualizacao")
public class Atualizacao implements Serializable {

    private static final long serialVersionUID = 3306139872907280192L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atualizacao")
    private Long idAtualizacao;

    private LocalDate inicio;

    private LocalDate termino;

    private Boolean concluido;

    private String observacao;

    public Long getIdAtualizacao() {
        return idAtualizacao;
    }

    public void setIdAtualizacao(Long idAtualizacao) {
        this.idAtualizacao = idAtualizacao;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public void setTermino(LocalDate termino) {
        this.termino = termino;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
