package model;

import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Noticia implements Serializable {

    private static final long serialVersionUID = 189533548214715527L;

    private Long idNoticia;

    private String titulo;

    private LocalDateTime publicacao;

    private String severidade;

    private String abrangencia;

    private String conteudo;

    @ManyToMany
    private Instituicao instituicao;

    private Associado associado;

    public Long getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(Long idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(LocalDateTime publicacao) {
        this.publicacao = publicacao;
    }

    public String getSeveridade() {
        return severidade;
    }

    public void setSeveridade(String severidade) {
        this.severidade = severidade;
    }

    public String getAbrangencia() {
        return abrangencia;
    }

    public void setAbrangencia(String abrangencia) {
        this.abrangencia = abrangencia;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noticia noticia = (Noticia) o;
        return Objects.equals(idNoticia, noticia.idNoticia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNoticia);
    }
}
