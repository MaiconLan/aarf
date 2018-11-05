package service;

import business.BancoBusiness;
import dao.BancoDAO;
import dto.BancoDTO;
import model.Banco;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class BancoServiceImpl implements BancoService, Serializable {

    @Inject
    private BancoBusiness bancoBusiness;

    @Inject
    private BancoDAO bancoDAO;

    @Override
    public void salvar(Banco banco) {
        bancoBusiness.salvar(banco);
    }

    @Override
    public List<Banco> consultaBanco(BancoDTO bancoDTO) {
        return bancoBusiness.consultaBanco(bancoDTO);
    }

    @Override
    public List<Banco> listarBancos() {
        return bancoDAO.list();
    }
}
