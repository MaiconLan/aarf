package business;

import dao.ConfiguracaoViagemDAO;
import dao.ViagemDAO;
import dto.FiltroViagemDTO;
import enumered.DiaSemanaEnum;
import model.ConfiguracaoViagem;
import model.Viagem;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class ViagemBusiness implements Serializable {

    private static final long serialVersionUID = 1791186782801042327L;

    @Inject
    private ViagemDAO viagemDAO;

    @Inject
    private ConfiguracaoViagemDAO configuracaoViagemDAO;

    public List<ConfiguracaoViagem> obterConfiguracoesViagemEdital(Long idEdital) {
        return configuracaoViagemDAO.obterConfiguracoesViagemEdital(idEdital);
    }

    public List<Viagem> buscarViagens(FiltroViagemDTO filtro) {
        return viagemDAO.buscarViagens(filtro);
    }

    public void gerarValores(ConfiguracaoViagem configuracaoViagem, List<Viagem> viagens) {
        if(configuracaoViagem.getDiaSemana().length == 0) {
            int i = 0;

            String[] diaSemana = new String[5];
            for (DiaSemanaEnum diaSemanaEnum: DiaSemanaEnum.values()) {
                diaSemana[i] = diaSemanaEnum.getDescricao();
                i++;
            }

            configuracaoViagem.setDiaSemana(diaSemana);
        }

        if(configuracaoViagem.getSentido().length == 0) {
            String[] sentido = new String[2];
            sentido[0] = "Ida";
            sentido[1] = "Volta";
            configuracaoViagem.setSentido(sentido);
        }

        for (Viagem viagem : viagens) {
            configuracaoViagemDAO.save(configuracaoViagem);
            viagem.setConfiguracaoViagem(configuracaoViagem);
            viagemDAO.save(viagem);
        }
    }

    public void removerConfiguracao(ConfiguracaoViagem configuracaoViagem) throws Exception {
        configuracaoViagemDAO.remove(configuracaoViagem.getIdConfiguracaoViagem());
    }
}
