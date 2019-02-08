package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "cadastro", name = "endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 8250637772356880384L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long idEndereco;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    
    public String getEnderecoFormatado() {
    	return "Cep: " + this.cep + ", Bairro:" + this.bairro + ", Rua: " + this.logradouro;
    }
    
    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(idEndereco, endereco.idEndereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEndereco);
    }

    @Override
    public String toString() {
        return "Rua " + logradouro + ", " + bairro + " - " + cep;
    }
}
