package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "cadastro", name = "estudante")
public class Estudante implements Serializable {

    private static final long serialVersionUID = 1260353098758642380L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudante")
    private Long idEstudante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_instituicao")
    private Instituicao instituicao;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, optional = true)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Estudante() {
        this.pessoa = new Pessoa();
        this.instituicao = new Instituicao();
        this.usuario = new Usuario();
    }

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
