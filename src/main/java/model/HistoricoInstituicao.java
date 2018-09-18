package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class HistoricoInstituicao implements Serializable {

    private static final long serialVersionUID = -8091851451256266836L;

    private Long idHistoricoInstituicao;

    private LocalDateTime termino;

    private Instituicao instituicao;

    private Estudante estudante;

    public Long getIdHistoricoInstituicao() {
        return idHistoricoInstituicao;
    }

    public void setIdHistoricoInstituicao(Long idHistoricoInstituicao) {
        this.idHistoricoInstituicao = idHistoricoInstituicao;
    }

    public LocalDateTime getTermino() {
        return termino;
    }

    public void setTermino(LocalDateTime termino) {
        this.termino = termino;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoInstituicao that = (HistoricoInstituicao) o;
        return Objects.equals(idHistoricoInstituicao, that.idHistoricoInstituicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistoricoInstituicao);
    }

}
