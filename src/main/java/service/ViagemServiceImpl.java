package service;

import business.ViagemBusiness;
import dto.FiltroViagemDTO;
import model.ConfiguracaoViagem;
import model.Viagem;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class ViagemServiceImpl implements ViagemService, Serializable {

    private static final long serialVersionUID = -5245068140055415654L;

    @Inject
    private ViagemBusiness viagemBusiness;

    @Override
    public List<ConfiguracaoViagem> obterConfiguracoesViagemEdital(Long idEdital) {
        return viagemBusiness.obterConfiguracoesViagemEdital(idEdital);
    }

    @Override
    public List<Viagem> buscarViagens(FiltroViagemDTO filtro) {
        return viagemBusiness.buscarViagens(filtro);
    }

    @Override
    public void gerarValores(ConfiguracaoViagem configuracaoViagem, List<Viagem> viagens) {
        viagemBusiness.gerarValores(configuracaoViagem, viagens);
    }

    @Override
    public void removerConfiguracao(ConfiguracaoViagem configuracaoViagem) throws Exception {
        viagemBusiness.removerConfiguracao(configuracaoViagem);
    }
}
