package business;

import dao.BancoDAO;
import dto.BancoDTO;
import model.Banco;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class BancoBusiness implements Serializable {

    private static final long serialVersionUID = -6664683790549602260L;

    @Inject
    private BancoDAO bancoDAO;

    public void salvar(Banco banco) {
        if (banco.getIdBanco() == null)
            bancoDAO.save(banco);
        else
            bancoDAO.update(banco);
    }

    public List<Banco> consultaBanco(BancoDTO bancoDTO) {
        return bancoDAO.consultaBanco(bancoDTO);
    }

    public List<Banco> listarBancos() {
        return bancoDAO.list();
    }
}
