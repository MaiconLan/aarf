package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "financeiro", name = "prestacao_conta")
public class PrestacaoConta implements Serializable {

    private static final long serialVersionUID = -2956805342959922492L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestacao_conta")
    private Long idPrestacaoConta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_insituicao")
    private Instituicao instituicao;

    private Double valor;

    private LocalDate recebimento;

    private String observacao;

    @ManyToMany
    @JoinTable(name="prestacao_conta_anexo", joinColumns=
            {@JoinColumn(name="id_prestacaoConta")}, inverseJoinColumns=
            {@JoinColumn(name="id_anexo")})
    private List<Anexo> anexos;

    @OneToMany(mappedBy = "prestacaoConta", cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos = new ArrayList<>();

    public Long getIdPrestacaoConta() {
        return idPrestacaoConta;
    }

    public void setIdPrestacaoConta(Long idPrestacaoConta) {
        this.idPrestacaoConta = idPrestacaoConta;
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

    public LocalDate getRecebimento() {
        return recebimento;
    }

    public void setRecebimento(LocalDate recebimento) {
        this.recebimento = recebimento;
    }

    public List<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<Anexo> anexos) {
        this.anexos = anexos;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
