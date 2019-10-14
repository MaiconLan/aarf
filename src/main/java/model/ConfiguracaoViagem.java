package model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "matricula", name = "configuracao_viagem")
@TypeDefs(@TypeDef(name = "stringArray", typeClass = StringArrayType.class))
public class ConfiguracaoViagem implements Serializable {

    private static final long serialVersionUID = 247882893551670894L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracao_viagem")
    private Long idConfiguracaoViagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instituicao")
    private Instituicao instituicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_edital")
    private Edital edital;

    private Double valor;

    public Long getIdConfiguracaoViagem() {
        return idConfiguracaoViagem;
    }

    public void setIdConfiguracaoViagem(Long idConfiguracaoViagem) {
        this.idConfiguracaoViagem = idConfiguracaoViagem;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }


    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

}
