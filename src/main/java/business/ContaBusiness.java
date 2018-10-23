package business;

import dao.EstudanteDAO;
import exception.ContaBusinessException;
import exception.LoginException;
import model.Conta;
import service.UsuarioService;
import utils.StringUtils;
import dao.ContaDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

@Stateless
public class ContaBusiness {

    @Inject
    private ContaDAO contaDAO;

    @Inject
    private UsuarioService usuarioService;

    public void salvarConta(Conta conta) throws ContaBusinessException {
        validarConta(conta);

        if(conta.getIdConta() == null)
            contaDAO.save(conta);
        else
            contaDAO.update(conta);
    }

    private void validarConta(Conta conta) throws ContaBusinessException {
        Collection<String> detalhes = Collections.EMPTY_LIST;

        if(conta.getIdConta() == null)
            detalhes.add("Código da conta é de preenchimento obrigatório");

        if(!detalhes.isEmpty())
            throw new ContaBusinessException(detalhes);
    }

    /* private void removerCaracteres(Pessoa pessoa){
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
    }*/
}
