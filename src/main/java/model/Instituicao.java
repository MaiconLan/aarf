package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "cadastro", name = "instituicao")
public class Instituicao  implements Serializable {

    private static final long serialVersionUID = -8049920322251164021L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instituicao")
    private Long idInstituicao;

    private String nome;

    private String tipo;

    public Long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instituicao that = (Instituicao) o;
        return Objects.equals(idInstituicao, that.idInstituicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInstituicao);
    }

}


