package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "financeiro", name = "prestacao_conta")
public class PrestacaoConta implements Serializable {
    private static final long serialVersionUID = -2956805342959922492L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestacaoConta")
    private Long id_prestacaoConta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_insituicao")
    private Instituicao instituicao;

    private Long    valor;
    private Date    data;
    private String  observacao;
    private String  nome_gasto;
    private Long    valor_gasto;
    private String  observacao_gasto;
    private Long    id_instituicao;

    @ManyToMany
    @JoinTable(name="prestacao_conta_anexo", joinColumns=
            {@JoinColumn(name="id_prestacaoConta")}, inverseJoinColumns=
            {@JoinColumn(name="id_anexo")})
    private List<Anexo> anexos;

    public Long getId_prestacaoConta() {
        return id_prestacaoConta;
    }

    public void setId_prestacaoConta(Long id_pestacaoConta) {
        this.id_prestacaoConta = id_pestacaoConta;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNome_gasto() {
        return nome_gasto;
    }

    public void setNome_gasto(String nome_gasto) {
        this.nome_gasto = nome_gasto;
    }

    public Long getValor_gasto() {
        return valor_gasto;
    }

    public void setValor_gasto(Long valor_gasto) {
        this.valor_gasto = valor_gasto;
    }

    public String getObservacao_gasto() {
        return observacao_gasto;
    }

    public void setObservacao_gasto(String observacao_gasto) {
        this.observacao_gasto = observacao_gasto;
    }

    public List<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<Anexo> anexos) {
        this.anexos = anexos;
    }
}
