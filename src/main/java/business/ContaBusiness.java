package business;

import dao.ContaDAO;
import dto.ContaDTO;
import exception.ContaBusinessException;
import model.Conta;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Stateless
public class ContaBusiness implements Serializable {

    private static final long serialVersionUID = 2300287425942998599L;

    @Inject
    private ContaDAO contaDAO;

    public void salvarConta(Conta conta) throws ContaBusinessException {
        // validarConta(conta);

        contaDAO.save(conta);
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

    public List<Conta> consultarConta(ContaDTO contaDTO) {
        return contaDAO.consultarConta(contaDTO);
    }
}
