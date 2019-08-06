package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "cadastro", name = "associado")
public class Associado implements Serializable {

    private static final long serialVersionUID = 5462575499370690865L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_associado")
    private Long idAssociado;

    private String cargo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, optional = true)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    private Boolean inativo;

    public Associado() {
        this.pessoa = new Pessoa();
        this.usuario = new Usuario();
    }
    
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
    
    public Boolean getInativo() {
        return inativo;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
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
