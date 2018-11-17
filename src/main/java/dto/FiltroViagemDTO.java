package dto;

import model.Instituicao;

import java.io.Serializable;

public class FiltroViagemDTO implements Serializable {

    private static final long serialVersionUID = -2175871766045047629L;

    private String[] diaSemana;

    private String[] sentido;

    private Instituicao instituicao;

    private Long idEdital;

    public String[] getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String[] diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String[] getSentido() {
        return sentido;
    }

    public void setSentido(String[] sentido) {
        this.sentido = sentido;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Long getIdEdital() {
        return idEdital;
    }

    public void setIdEdital(Long idEdital) {
        this.idEdital = idEdital;
    }
}
