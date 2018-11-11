package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "cadastro", name = "cidade")
public class Cidade implements Serializable {

    private static final long serialVersionUID = -2662233063077142764L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private Long idCidade;

    private String nome;

    private String uf;

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cidade cidade = (Cidade) o;

        return idCidade.equals(cidade.idCidade);
    }

    @Override
    public int hashCode() {
        return idCidade.hashCode();
    }

    @Override
    public String toString(){
        return nome + " - " + uf;
    }
}
