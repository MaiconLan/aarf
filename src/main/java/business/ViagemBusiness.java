package business;

import dao.ConfiguracaoViagemDAO;
import dao.ViagemDAO;
import dto.ViagemDTO;
import model.ConfiguracaoViagem;
import model.Viagem;

import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
        configuracaoViagemDAO.save(configuracaoViagem);

        BigDecimal valor = configuracaoViagem.getValor();
        Long quantidade = viagensDTO.stream().collect(Collectors.summingLong(viagemDTO -> viagemDTO.getTotalViagens()));

        BigDecimal valorPorViagem = valor.divide(new BigDecimal(quantidade));

        List<Viagem> viagens = viagemDAO.buscarViagens(configuracaoViagem.getEdital().getIdEdital(), configuracaoViagem.getInstituicao().getIdInstituicao());
        for (Viagem viagem : viagens) {
            viagem.setValor(valorPorViagem);
            viagem.setConfiguracaoViagem(configuracaoViagem);
            viagemDAO.save(viagem);
        }

    }

    public void removerConfiguracao(ConfiguracaoViagem configuracaoViagem) throws Exception {
        configuracaoViagemDAO.remove(configuracaoViagem.getIdConfiguracaoViagem());
    }

    public ConfiguracaoViagem obterConfiguracaoViagem(Long idEdital, Long idInstituicao) {
        return configuracaoViagemDAO.obterConfiguracaoViagem(idEdital, idInstituicao);
    }

    public Boolean isEditalFinalizadoByMatricula(Long idMatricula) {
        return configuracaoViagemDAO.isEditalFinalizadoByMatricula(idMatricula);
    }

    public boolean isMatriculaCancelada(Long idMatricula) {
        return configuracaoViagemDAO.isMatriculaCancelada(idMatricula);
    }

    public void atualizarValorViagem(ViagemDTO viagemDTO) {
        List<Viagem> viagens = viagemDAO.buscarViagensByMatriculaInstituicao(viagemDTO.getIdMatricula(), viagemDTO.getIdInstituciao());
        BigDecimal valorPorViagem = viagemDTO.getValor().divide(new BigDecimal(viagemDTO.getTotalViagens()), 2, RoundingMode.HALF_UP);

        for (Viagem viagem: viagens) {
            viagem.setValor(valorPorViagem);
            viagemDAO.save(viagem);
        }
    }
}
