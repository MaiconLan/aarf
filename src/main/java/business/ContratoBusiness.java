package business;

import dao.ContratoDAO;
import exception.ContratoBusinessException;
import model.Contrato;
import utils.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
public class ContratoBusiness implements Serializable {

    private static final long serialVersionUID = 1561465494772700654L;

    @Inject
    private ContratoDAO contratoDAO;

    public Contrato obterContratoVigente(){
        return contratoDAO.obterContratoVigente();
    }

    public void salvar(Contrato contrato) throws ContratoBusinessException {
        validarContrato(contrato);

        if (contrato.getIdContrato() == null)
            contratoDAO.save(contrato);
        else
            contratoDAO.update(contrato);
    }

    private void validarContrato(Contrato contrato) throws ContratoBusinessException {
        Collection<String> detalhes = new ArrayList<>();
        if(contratoDAO.possuiContratoVigente(contrato))
            detalhes.add("Já há um contrato vigente cadastrado.");

        if(StringUtils.isBlank(contrato.getClausula()))
            detalhes.add("Cláusulá é de preenchimento obrigatório");

        if(!detalhes.isEmpty())
            throw new ContratoBusinessException(detalhes);
    }

    public List<Contrato> consultarContratos() {
        return contratoDAO.list();
    }

}
