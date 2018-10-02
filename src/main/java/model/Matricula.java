package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "matricula",name = "matricula")
public class Matricula  implements Serializable {

    private static final long serialVersionUID = -4839789861123080961L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;

    private LocalDateTime inscricao;

    private LocalDateTime confirmacao;

    @ManyToOne
    @JoinColumn(name = "id_estudante")
    private Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "id_edital")
    private Edital edital;

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

    public boolean isConfirmada(){
        return confirmacao != null;
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
