package model;

import java.io.Serializable;
import java.util.Objects;

public class Regra implements Serializable {

    private static final long serialVersionUID = -1101541073193067018L;

    private Long idRegra;
    private String nome;
    private String descricao;

    public Long getIdRegra() {
        return idRegra;
    }

    public void setIdRegra(Long idRegra) {
        this.idRegra = idRegra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Regra regra = (Regra) o;
        return Objects.equals(idRegra, regra.idRegra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRegra);
    }
}
