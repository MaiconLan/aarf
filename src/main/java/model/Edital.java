package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Edital implements Serializable {

    private static final long serialVersionUID = -5197187050289800713L;

    private Long idEdital;

    private String titulo;

    private LocalDate inicio;

    private LocalDate termino;

    private Boolean finalizado;

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
