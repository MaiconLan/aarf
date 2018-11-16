package service;

import dto.FiltroViagemDTO;
import model.ConfiguracaoViagem;
import model.Viagem;

import java.util.List;

public interface ViagemService {

    List<ConfiguracaoViagem> obterConfiguracoesViagemEdital(Long idEdital);

    List<Viagem> buscarViagens(FiltroViagemDTO filtro);

    void gerarValores(ConfiguracaoViagem configuracaoViagem, List<Viagem> viagens);

    void removerConfiguracao(ConfiguracaoViagem configuracaoViagem) throws Exception;
}
