package model;

import java.io.Serializable;
import java.util.Objects;

public class Associado implements Serializable {

    private static final long serialVersionUID = 5462575499370690865L;

    private Long idAssociado;
    private String cargo;

    private Pessoa pessoa;
    private Usuario usuario;

    public Long getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Long idAssociado) {
        this.idAssociado = idAssociado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        Associado associado = (Associado) o;
        return Objects.equals(idAssociado, associado.idAssociado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAssociado);
    }

    @Override
    public String toString() {
        return "Associado: " + pessoa.toString();
    }
}
