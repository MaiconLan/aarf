package business;

import dao.ConfiguracaoViagemDAO;
import dao.ViagemDAO;
import dto.FiltroViagemDTO;
import dto.ViagemDTO;
import enumered.DiaSemanaEnum;
import model.ConfiguracaoViagem;
import model.Viagem;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViagemBusiness implements Serializable {

    private static final long serialVersionUID = 1791186782801042327L;

    @Inject
    private ViagemDAO viagemDAO;

    @Inject
    private ConfiguracaoViagemDAO configuracaoViagemDAO;

    public List<ViagemDTO> buscarViagensDTO(Long idEdital, Long idInstituicao) {
        return viagemDAO.buscarViagensDTO(idEdital, idInstituicao);
    }

    public void gerarValores(ConfiguracaoViagem configuracaoViagem, List<ViagemDTO> viagensDTO) {
        Double valor = configuracaoViagem.getValor();
        Long quantidade = viagensDTO.stream().collect(Collectors.summingLong(viagemDTO -> viagemDTO.getTotalViagens()));

        Double valorPorViagem = valor / quantidade;

        List<Viagem> viagens = viagemDAO.buscarViagens(configuracaoViagem.getEdital().getIdEdital(), configuracaoViagem.getInstituicao().getIdInstituicao());
        for (Viagem viagem : viagens) {
            viagem.setValor(valorPorViagem);
            viagemDAO.save(viagem);
        }

        configuracaoViagemDAO.save(configuracaoViagem);
    }

    public void removerConfiguracao(ConfiguracaoViagem configuracaoViagem) throws Exception {
        configuracaoViagemDAO.remove(configuracaoViagem.getIdConfiguracaoViagem());
    }

    public ConfiguracaoViagem obterConfiguracaoViagem(Long idEdital, Long idInstituicao) {
        return configuracaoViagemDAO.obterConfiguracaoViagem(idEdital, idInstituicao);
    }
}
