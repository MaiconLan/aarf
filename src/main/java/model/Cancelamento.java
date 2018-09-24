package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Cancelamento implements Serializable {

    private static final long serialVersionUID = 5484318972885335435L;

    private Long idCancelamento;

    private LocalDateTime cancelamento;

    private String motivo;

    private Matricula matricula;

    public Long getIdCancelamento() {
        return idCancelamento;
    }

    public void setIdCancelamento(Long idCancelamento) {
        this.idCancelamento = idCancelamento;
    }

    public LocalDateTime getCancelamento() {
        return cancelamento;
    }

    public void setCancelamento(LocalDateTime cancelamento) {
        this.cancelamento = cancelamento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cancelamento that = (Cancelamento) o;
        return Objects.equals(idCancelamento, that.idCancelamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCancelamento);
    }
}
