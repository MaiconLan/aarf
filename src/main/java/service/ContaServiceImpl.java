package service;

import business.ContaBusiness;
import dto.ContaDTO;
import exception.ContaBusinessException;
import model.Conta;

import javax.inject.Inject;
import java.util.List;

public class ContaServiceImpl implements ContaService {

    private static final long serialVersionUID = 1830819295361181977L;

    @Inject
    private ContaBusiness contaBusiness;

    @Override
    public void salvarConta(Conta conta) throws ContaBusinessException {
        contaBusiness.salvarConta(conta);
    }

    @Override
    public Conta obterConta(Long idConta) {
        return null;
    }

    @Override
    public void inativarConta(Conta conta) throws ContaBusinessException {
        contaBusiness.inativarConta(conta);
    }

    @Override
    public List<Conta> consultarConta(ContaDTO contaDTO) {
        return contaBusiness.consultarConta(contaDTO);
    }


}
