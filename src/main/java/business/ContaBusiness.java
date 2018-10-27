package business;

import dao.ContaDAO;
import exception.ContaBusinessException;
import model.Conta;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Stateless
public class ContaBusiness {

    @Inject
    private ContaDAO contaDAO;

    public void salvarConta(Conta conta) throws ContaBusinessException {
        // validarConta(conta);

        if(conta.getIdConta() == null)
            contaDAO.save(conta);
        else
            contaDAO.update(conta);
    }

    private void validarConta(Conta conta) throws ContaBusinessException {
        Collection<String> detalhes = new ArrayList<>();

        if(conta.getBanco() == null)
            detalhes.add("Banco é de preenchimento obrigatório");

        if(!detalhes.isEmpty())
            throw new ContaBusinessException(detalhes);
    }

    public void inativarConta(Conta conta) throws ContaBusinessException {
        conta.setInativo(Boolean.TRUE);
        salvarConta(conta);
    }
}
