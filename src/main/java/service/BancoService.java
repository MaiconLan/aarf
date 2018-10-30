package service;

import dto.BancoDTO;
import model.Banco;

import java.util.List;

public interface BancoService {

	void salvar(Banco banco);
	
	List<Banco> consultaBanco(BancoDTO bancoDTO);

    List<Banco> listarBancos();
}
