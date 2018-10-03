package business;

import dao.EstudanteDAO;
import exception.EstudanteBusinessException;
import exception.LoginException;
import model.Endereco;
import model.Estudante;
import model.Pessoa;
import service.UsuarioService;
import utils.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

@Stateless
public class EstudanteBusiness {

    @Inject
    private EstudanteDAO estudanteDAO;

    @Inject
    private UsuarioService usuarioService;

    public void salvarEstudante(Estudante estudante) throws EstudanteBusinessException, LoginException {
        validarEstudante(estudante);
        removerCaracteres(estudante.getPessoa());

        usuarioService.salvarUsuario(estudante.getUsuario());

        Endereco endereco = estudante.getPessoa().getEndereco();
        endereco.setPessoa(estudante.getPessoa());

        if(estudante.getIdEstudante() == null)
            estudanteDAO.save(estudante);
        else
            estudanteDAO.update(estudante);
    }

    private void validarEstudante(Estudante estudante) throws EstudanteBusinessException {
        Collection<String> detalhes = Collections.EMPTY_LIST;

        if(estudante.getPessoa().getNome() == null)
            detalhes.add("Nome é de preenchimento obrigatório");

        if(!detalhes.isEmpty())
            throw new EstudanteBusinessException(detalhes);
    }

    private void removerCaracteres(Pessoa pessoa){
        String cpf = StringUtils.removerCaracteres(pessoa.getCpf());
        String rg = StringUtils.removerCaracteres(pessoa.getRg());
        String celular = StringUtils.removerCaracteres(pessoa.getCelular());
        String telefone = StringUtils.removerCaracteres(pessoa.getTelefone());
        String cep = StringUtils.removerCaracteres(pessoa.getEndereco().getCep());

        pessoa.getEndereco().setCep(cep);
        pessoa.setCelular(celular);
        pessoa.setTelefone(telefone);
        pessoa.setCpf(cpf);
        pessoa.setRg(rg);
    }
}
