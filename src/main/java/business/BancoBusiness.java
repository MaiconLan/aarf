package business;

import dao.BancoDAO;
import dto.BancoDTO;
import model.Banco;

import javax.inject.Inject;
import java.util.List;

public class BancoBusiness {

	@Inject
	private BancoDAO bancoDAO;
	
	public void salvar(Banco banco) {
		if(banco.getIdBanco() == null)
			bancoDAO.save(banco);
		else
			bancoDAO.update(banco);
	}

	public List<Banco> consultaBanco(BancoDTO bancoDTO) {
		return bancoDAO.consultaBanco(bancoDTO);
	}
}
