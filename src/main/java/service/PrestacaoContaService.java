package service;

import dto.EstudanteDTO;
import dto.PrestacaoContaDTO;
import model.Estudante;
import model.PrestacaoConta;

import java.util.List;

public interface PrestacaoContaService {

    void salvarPrestacao(PrestacaoConta prestacaoConta);
    PrestacaoConta obterPrestacao(Long id_prestacaoConta);
    List<Estudante> consultarEstudantes(PrestacaoContaDTO prestacaoContaDTO);


}
