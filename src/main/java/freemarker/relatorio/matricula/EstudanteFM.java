package freemarker.relatorio.matricula;

import java.util.List;

public class EstudanteFM {

    private Long idMatricula;
    private String nome;
    private List<String> diasSemana;

    public EstudanteFM() {
    }

    public EstudanteFM(Long idMatricula, String nome) {
        this.idMatricula = idMatricula;
        this.nome = nome;
    }

    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<String> diasSemana) {
        this.diasSemana = diasSemana;
    }
}
