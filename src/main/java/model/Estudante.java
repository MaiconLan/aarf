package model;

import java.io.Serializable;
import java.util.Objects;

public class Estudante implements Serializable {

    private static final long serialVersionUID = 1260353098758642380L;

    private Long idEstudante;

    private Pessoa pessoa;
    private Instituicao instituicao;
    private Usuario usuario;

    public Long getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(Long idEstudante) {
        this.idEstudante = idEstudante;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudante estudante = (Estudante) o;
        return Objects.equals(idEstudante, estudante.idEstudante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstudante);
    }

    @Override
    public String toString() {
        return "Estudante: " + pessoa.toString();
    }

}
