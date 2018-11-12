package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(schema = "publico", name = "noticia")
public class Noticia implements Serializable {

    private static final long serialVersionUID = 189533548214715527L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_associado")
    private Long idNoticia;

    private String titulo;

    private LocalDateTime publicacao;

    private String severidade;

    private boolean abrangencia;

    private String conteudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instituicao")
    private Instituicao instituicao;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, optional = true)
    @JoinColumn(name = "id_associado")
    private Associado associado;
    
    

    public Noticia() {
		this.associado = new Associado();
	}

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

	public boolean isAbrangencia() {
		return abrangencia;
	}

	public void setAbrangencia(boolean abrangencia) {
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
