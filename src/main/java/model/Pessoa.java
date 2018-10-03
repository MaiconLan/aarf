package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "cadastro", name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = -6629545866132877655L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long idPessoa;

    private String nome;

    private LocalDate nascimento;
    private String cpf;
    private String rg;
    private String email;
    private String genero;
    private String telefone;
    private String celular;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, optional = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(mappedBy = "pessoa", targetEntity = Estudante.class, fetch = FetchType.LAZY)
    private List<Estudante> estudantes;

    @OneToMany(mappedBy = "pessoa", targetEntity = Associado.class, fetch = FetchType.LAZY)
    private List<Associado> associados;

    public Pessoa() {
        this.endereco = new Endereco();
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<Estudante> estudantes) {
        this.estudantes = estudantes;
    }

    public List<Associado> getAssociados() {
        return associados;
    }

    public void setAssociados(List<Associado> associados) {
        this.associados = associados;
    }

    @Override
    public String toString() {
        return nome + " - " + cpf ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(idPessoa, pessoa.idPessoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPessoa);
    }
}
