package dto;

import java.io.Serializable;

public class ViagemDTO implements Serializable {

    private static final long serialVersionUID = 7408211615555966289L;

    private Long idMatricula;
    private Long idInstituciao;
    private String nomeEstudante;
    private Long totalViagens;
    private Double valor;

    public ViagemDTO(Long idMatricula, Long idInstituciao, String nomeEstudante, Double valor, Long totalViagens) {
        this.idMatricula = idMatricula;
        this.idInstituciao = idInstituciao;
        this.nomeEstudante = nomeEstudante;
        this.valor = valor;
        this.totalViagens = totalViagens;
    }

    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Long getIdInstituciao() {
        return idInstituciao;
    }

    public void setIdInstituciao(Long idInstituciao) {
        this.idInstituciao = idInstituciao;
    }

    public Double getValor() {
        return valor != null ? valor : 0D;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getTotalViagens() {
        return totalViagens;
    }

    public void setTotalViagens(Long totalViagens) {
        this.totalViagens = totalViagens;
    }

    public String getNomeEstudante() {
        return nomeEstudante;
    }

    public void setNomeEstudante(String nomeEstudante) {
        this.nomeEstudante = nomeEstudante;
    }
}
