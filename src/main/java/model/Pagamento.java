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

    private String descricao;

    private String observacao;

    private LocalDate pagamento;

    private Double valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prestacao_conta")
    private PrestacaoConta prestacaoConta;

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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
