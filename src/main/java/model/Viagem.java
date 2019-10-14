package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "matricula", name = "viagem")
public class Viagem implements Serializable {

    private static final long serialVersionUID = -671949289689195711L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viagem")
    private Long idViagem;

    @Column(name = "dia_semana")
    private String diaSemana;

    @Column(name = "sentido")
    private String sentido;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_instituicao")
    private Instituicao instituicao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @Cascade(value = {org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    @JoinColumn(name = "id_matricula")
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name = "id_configuracao_viagem")
    private ConfiguracaoViagem configuracaoViagem;

    public Viagem() {
        this.instituicao = new Instituicao();
        this.matricula = new Matricula();
    }

    public Long getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Long idViagem) {
        this.idViagem = idViagem;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public ConfiguracaoViagem getConfiguracaoViagem() {
        return configuracaoViagem;
    }

    public void setConfiguracaoViagem(ConfiguracaoViagem configuracaoViagem) {
        this.configuracaoViagem = configuracaoViagem;
    }

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viagem viagem = (Viagem) o;
        return Objects.equals(idViagem, viagem.idViagem) &&
                Objects.equals(diaSemana, viagem.diaSemana) &&
                Objects.equals(sentido, viagem.sentido) &&
                Objects.equals(instituicao, viagem.instituicao) &&
                Objects.equals(matricula, viagem.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idViagem, diaSemana, sentido, instituicao, matricula);
    }
}
