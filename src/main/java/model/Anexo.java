package model;

import java.io.Serializable;
import java.util.Objects;

public class Anexo implements Serializable {

    private static final long serialVersionUID = -5565107213468597015L;

    private Long idAnexo;

    private String nome;

    private String caminho;

    private String tipo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(Long idAnexo) {
        this.idAnexo = idAnexo;
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
        Anexo anexo = (Anexo) o;
        return Objects.equals(idAnexo, anexo.idAnexo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnexo);
    }


}
