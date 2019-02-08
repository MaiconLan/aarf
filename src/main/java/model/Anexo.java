package model;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "cadastro", name = "anexo")
public class Anexo implements Serializable {

    private static final long serialVersionUID = -5565107213468597015L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anexo")
    private Long idAnexo;

    private String nome;

    private String extensao;

    private String caminho;

    private String tipo;

    private String hash;

    @Transient
    private File file;

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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public File getFile() {
        return file;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public void setFile(File file) {
        this.file = file;
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

    public String getPath(){
        return caminho + hash +"."+ extensao;
    }

}
