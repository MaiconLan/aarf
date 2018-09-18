package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Apoio implements Serializable {

    private static final long serialVersionUID = 7028517256488745101L;

    private Long idApoio;

    private Double valor;

    private LocalDate pagamento;

    private Instituicao instituicao;

    public Long getIdApoio() {
        return idApoio;
    }

    public void setIdApoio(Long idApoio) {
        this.idApoio = idApoio;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getPagamento() {
        return pagamento;
    }

    public void setPagamento(LocalDate pagamento) {
        this.pagamento = pagamento;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apoio apoio = (Apoio) o;
        return Objects.equals(idApoio, apoio.idApoio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idApoio);
    }
}
