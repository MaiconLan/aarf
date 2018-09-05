package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "matricula")
public class Anexo implements Serializable {

    private static final long serialVersionUID = -5565107213468597015L;

    private String nome;

    private String caminho;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
