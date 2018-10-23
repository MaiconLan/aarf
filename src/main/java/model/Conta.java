package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "cadastro", name = "conta")
public class Conta implements Serializable {

    private static final long serialVersionUID = 1260353098758642380L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long idConta;

    private Integer numero_conta;

    private Integer agencia;

    private Integer digito;

    private String banco;

    private String titular;

    private boolean aceite;

    private Integer convenio;

    private Integer cedente;

    private String local_pagamento;

    private String especie_documento;

    private String especie;

    private Integer carteira;

    private Integer modalidade;

    private String instrucoes;

    private String tipo_dias_protestto;

    private Integer dias_protesto;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getNumero_conta() {
        return numero_conta;
    }

    public void setNumero_conta(Integer numero_conta) {
        this.numero_conta = numero_conta;
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

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public boolean isAceite() {
        return aceite;
    }

    public void setAceite(boolean aceite) {
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

    public String getLocal_pagamento() {
        return local_pagamento;
    }

    public void setLocal_pagamento(String local_pagamento) {
        this.local_pagamento = local_pagamento;
    }

    public String getEspecie_documento() {
        return especie_documento;
    }

    public void setEspecie_documento(String especie_documento) {
        this.especie_documento = especie_documento;
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

    public String getTipo_dias_protestto() {
        return tipo_dias_protestto;
    }

    public void setTipo_dias_protestto(String tipo_dias_protestto) {
        this.tipo_dias_protestto = tipo_dias_protestto;
    }

    public Integer getDias_protesto() {
        return dias_protesto;
    }

    public void setDias_protesto(Integer dias_protesto) {
        this.dias_protesto = dias_protesto;
    }

    public Conta() {

    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(idConta, conta.idConta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConta);
    }

//    @Override
//    public String toString() {
//        return "Conta: " + idConta.toString();
//    }

}
