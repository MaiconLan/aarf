package service;

import dto.EstudanteDTO;
import dto.PrestacaoContaDTO;
import model.Estudante;
import model.Instituicao;
import model.PrestacaoConta;

import java.util.List;

public interface PrestacaoContaService {

    void salvarPrestacao(PrestacaoConta prestacaoConta);
    List<PrestacaoConta>obterPrestacao();
    List<Estudante> consultarEstudantes(PrestacaoContaDTO prestacaoContaDTO);

}
