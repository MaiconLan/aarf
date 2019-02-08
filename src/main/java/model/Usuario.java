package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(schema = "cadastro", name = "usuario")
@NamedQueries(
        {
                @NamedQuery(name = Usuario.BUSCAR_USUARIOS, query = "SELECT u FROM Usuario u")
        }
)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 2603077537569479837L;
    public static final String POSSUI_USUARIO_BY_LOGIN = "POSSUI_USUARIO_BY_LOGIN";
    public static final String BUSCAR_USUARIOS = "BUSCAR_USUARIOS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    private String login;
    private String senha;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "usuario", optional = true)
    private Estudante estudante;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "usuario", optional = true)
    private Associado associado;

    @Transient
    private boolean alterarLogin;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public boolean isAlterarLogin() {
        return alterarLogin;
    }

    public void setAlterarLogin(boolean alterarLogin) {
        this.alterarLogin = alterarLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    @Override
    public String toString() {
        return "Estudante: " + estudante +
                "\nAssociado: " + associado;
    }
}
