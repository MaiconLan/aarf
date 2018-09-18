package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Investimento implements Serializable {

    private static final long serialVersionUID = 7855015756941939925L;

    private Long idInvestimento;

    private Double valor;

    private LocalDate pagamento;

    private Apoio apoio;

    public Long getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(Long idInvestimento) {
        this.idInvestimento = idInvestimento;
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

    public Apoio getApoio() {
        return apoio;
    }

    public void setApoio(Apoio apoio) {
        this.apoio = apoio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Investimento that = (Investimento) o;
        return Objects.equals(idInvestimento, that.idInvestimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInvestimento);
    }
}
