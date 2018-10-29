package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "financeiro", name = "banco")
public class Banco implements Serializable {

    private static final long serialVersionUID = 273337671956496658L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_banco")
    private Long idBanco;

    private String nome;

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Banco banco = (Banco) o;

        return idBanco.equals(banco.idBanco);
    }

    @Override
    public int hashCode() {
        return idBanco.hashCode();
    }
}
