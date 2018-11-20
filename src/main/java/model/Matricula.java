package model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "matricula",name = "matricula")
public class Matricula  implements Serializable {

    private static final long serialVersionUID = -4839789861123080961L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;

    private String semestre;

    @OneToMany(mappedBy = "matricula", targetEntity = Viagem.class, fetch = FetchType.LAZY, cascade = {javax.persistence.CascadeType.ALL})
    @Cascade(value = CascadeType.ALL)
    private List<Viagem> viagens;
    
    private LocalDateTime inscricao;

    private LocalDateTime confirmacao;

    @ManyToOne
    @JoinColumn(name = "id_estudante")
    private Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "id_edital")
    private Edital edital;

    @OneToOne(mappedBy = "matricula")
    private Cancelamento cancelamento;

    @ManyToMany
    @JoinTable(name="cadastro.matriculas_anexos", joinColumns=
            {@JoinColumn(name="id_matricula")}, inverseJoinColumns=
            {@JoinColumn(name="id_anexo")})
    private List<Anexo> anexos;

    public Matricula() {
        this.anexos = new ArrayList<>();
    }

    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public LocalDateTime getInscricao() {
        return inscricao;
    }

    public void setInscricao(LocalDateTime inscricao) {
        this.inscricao = inscricao;
    }

    public LocalDateTime getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(LocalDateTime confirmacao) {
        this.confirmacao = confirmacao;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public List<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(List<Viagem> viagens) {
		this.viagens = viagens;
	}

	public boolean isConfirmada(){
        return confirmacao != null;
    }

    public Cancelamento getCancelamento() {
        return cancelamento;
    }

    public void setCancelamento(Cancelamento cancelamento) {
        this.cancelamento = cancelamento;
    }

    public List<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<Anexo> anexos) {
        this.anexos = anexos;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(idMatricula, matricula.idMatricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMatricula);
    }

    @Override
    public String toString() {
        String confirmada = isConfirmada() ? "Confirmada" : "Não-Confirmada";

        return "Estudante: " + estudante.toString()
                + " - " + confirmada;
    }
}
