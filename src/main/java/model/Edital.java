package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "matricula", name = "edital")
public class Edital implements Serializable {

    private static final long serialVersionUID = -5197187050289800713L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_edital")
    private Long idEdital;

    private String titulo;

    private LocalDate inicio;

    private LocalDate termino;

    private Boolean finalizado;

    @OneToMany(mappedBy = "edital", targetEntity = Matricula.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Matricula> matriculas;

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public Long getIdEdital() {
        return idEdital;
    }

    public void setIdEdital(Long idEdital) {
        this.idEdital = idEdital;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public void setTermino(LocalDate termino) {
        this.termino = termino;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edital edital = (Edital) o;
        return Objects.equals(idEdital, edital.idEdital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEdital);
    }
}
