package model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "financeiro", name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long idPagemento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prestacao_conta")
    private PrestacaoConta prestacaoConta;

    private LocalDate pagamento;

    private Double valor;

    public Long getIdPagemento() {
        return idPagemento;
    }

    public void setIdPagemento(Long idPagemento) {
        this.idPagemento = idPagemento;
    }

    public PrestacaoConta getPrestacaoConta() {
        return prestacaoConta;
    }

    public void setPrestacaoConta(PrestacaoConta prestacaoConta) {
        this.prestacaoConta = prestacaoConta;
    }

    public LocalDate getPagamento() {
        return pagamento;
    }

    public void setPagamento(LocalDate pagamento) {
        this.pagamento = pagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
