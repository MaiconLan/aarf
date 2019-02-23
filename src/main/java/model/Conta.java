package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "financeiro", name = "conta")
public class Conta implements Serializable {

    private static final long serialVersionUID = 1260353098758642380L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long idConta;

    @Column(name = "numero_conta")
    private Integer numeroConta;

    private Integer agencia;

    private Integer digito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_banco")
    private Banco banco;

    private String titular;

    private Boolean aceite;

    private Integer convenio;

    private Integer cedente;

    @Column(name = "local_pagamento")
    private String localPagamento;

    @Column(name = "especie_documento")
    private String especieDocumento;

    private String especie;

    private Integer carteira;

    private Integer modalidade;

    private String instrucoes;

    @Column(name = "tipo_dias_protesto")
    private String tipoDiasProtesto;

    @Column(name = "dias_protesto")
    private Integer diasProtesto;

    private Boolean inativo;

    private String layout;

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getDigito() {
        return digito;
    }

    public void setDigito(Integer digito) {
        this.digito = digito;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Boolean getAceite() {
        return aceite;
    }

    public void setAceite(Boolean aceite) {
        this.aceite = aceite;
    }

    public Integer getConvenio() {
        return convenio;
    }

    public void setConvenio(Integer convenio) {
        this.convenio = convenio;
    }

    public Integer getCedente() {
        return cedente;
    }

    public void setCedente(Integer cedente) {
        this.cedente = cedente;
    }

    public String getLocalPagamento() {
        return localPagamento;
    }

    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }

    public String getEspecieDocumento() {
        return especieDocumento;
    }

    public void setEspecieDocumento(String especieDocumento) {
        this.especieDocumento = especieDocumento;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getCarteira() {
        return carteira;
    }

    public void setCarteira(Integer carteira) {
        this.carteira = carteira;
    }

    public Integer getModalidade() {
        return modalidade;
    }

    public void setModalidade(Integer modalidade) {
        this.modalidade = modalidade;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public String getTipoDiasProtesto() {
        return tipoDiasProtesto;
    }

    public void setTipoDiasProtesto(String tipoDiasProtesto) {
        this.tipoDiasProtesto = tipoDiasProtesto;
    }

    public Integer getDiasProtesto() {
        return diasProtesto;
    }

    public void setDiasProtesto(Integer diasProtesto) {
        this.diasProtesto = diasProtesto;
    }

    public Boolean getInativo() {
        return inativo;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conta conta = (Conta) o;

        if (idConta != null ? !idConta.equals(conta.idConta) : conta.idConta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idConta != null ? idConta.hashCode() : 0;
    }

}
