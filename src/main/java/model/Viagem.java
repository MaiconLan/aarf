package model;

import javax.persistence.*;
import java.io.Serializable;

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

	@ManyToOne
	@JoinColumn(name = "id_instituicao")
	private Instituicao instituicao;

	@ManyToOne
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idViagem == null) ? 0 : idViagem.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viagem other = (Viagem) obj;
		if (idViagem == null) {
			if (other.idViagem != null)
				return false;
		} else if (!idViagem.equals(other.idViagem))
			return false;
		return true;
	}


}
