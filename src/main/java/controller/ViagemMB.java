package controller;

import business.EditalBusiness;
import business.InstituicaoBusiness;
import business.ViagemBusiness;
import dto.EditalDTO;
import dto.FiltroViagemDTO;
import dto.ViagemDTO;
import enumered.DiaSemanaEnum;
import model.ConfiguracaoViagem;
import model.Edital;
import model.Instituicao;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ViewScoped
@Named("viagemMB")
public class ViagemMB implements Serializable {

    private static final long serialVersionUID = 8896076229004605469L;

    @Inject
    private EditalBusiness editalBusiness;

    @Inject
    private InstituicaoBusiness instituicaoBusiness;

    @Inject
    private ViagemBusiness viagemBusiness;

    private FiltroViagemDTO filtro;

    private List<Edital> listaEditais = new ArrayList();

    private List<Instituicao> instituicoes = new ArrayList();

    private List<ViagemDTO> viagens = new ArrayList<>();

    private ConfiguracaoViagem configuracaoViagem;

    private Double valor;

    @Inject
    @Param
    private Long idEdital;

    private Edital edital;

    @PostConstruct
    public void init() {
        if (idEdital == null) {
            carregarListaEditais();
            abrirModalEditais();

        } else {
            filtro = new FiltroViagemDTO();
            carregarEdital();
            listarInstituicoes();
            novaConfiguracaoViagem();
            carregarConfiguracaoViagem();
        }
    }

    private void carregarListaEditais() {
        listaEditais = editalBusiness.consultarEdital(new EditalDTO());
    }

    private void abrirModalEditais() {
        RequestContext.getCurrentInstance().execute("PF('modalConsultaEditais').show();");
    }

    private void novaConfiguracaoViagem() {
        configuracaoViagem = new ConfiguracaoViagem();
    }

    public void removerConfiguracao(ConfiguracaoViagem configuracaoViagem) {
        try {
            viagemBusiness.removerConfiguracao(configuracaoViagem);
            Messages.addInfo(null, "Configuracao removida com sucesso!");
            carregarConfiguracaoViagem();
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addError(null, "Não foi possível remover a configuração!");
        }
    }

    public void gerarValores() {
        configuracaoViagem.setEdital(edital);
        configuracaoViagem.setInstituicao(filtro.getInstituicao());

        viagemBusiness.gerarValores(configuracaoViagem, viagens);

        Messages.addInfo(null, "Valores gerados com sucesso!");

        buscarEstudantesSemConfiguracaoViagem();
    }

    public void buscarEstudantes() {
        if(filtro.getInstituicao() != null) {
            carregarConfiguracaoViagem();
            viagens = viagemBusiness.buscarViagensDTO(idEdital, filtro.getInstituicao().getIdInstituicao());
        } else {
            novaConfiguracaoViagem();
            viagens.clear();
        }
    }

    public void buscarEstudantesSemConfiguracaoViagem() {
        if(filtro.getInstituicao() != null) {
            viagens = viagemBusiness.buscarViagensDTO(idEdital, filtro.getInstituicao().getIdInstituicao());
        } else {
            novaConfiguracaoViagem();
            viagens.clear();
        }
    }

    private void carregarConfiguracaoViagem() {
        if (idEdital != null && filtro.getInstituicao() != null) {
            configuracaoViagem = viagemBusiness.obterConfiguracaoViagem(idEdital, filtro.getInstituicao().getIdInstituicao());

            if (configuracaoViagem == null) {
                novaConfiguracaoViagem();
                configuracaoViagem.setInstituicao(instituicaoBusiness.obterInstituicao(filtro.getInstituicao().getIdInstituicao()));
                configuracaoViagem.setEdital(edital);
            }
        }
    }

    public Long getTotalViagens(){
        if(configuracaoViagem != null && !viagens.isEmpty())
            return viagens.stream().collect(Collectors.summingLong(viagem -> viagem.getTotalViagens()));
       return 0L;
    }

    public Double getValorTotal(){
        if(configuracaoViagem != null && !viagens.isEmpty())
            return viagens.stream().collect(Collectors.summingDouble(viagem -> viagem.getValor() != null ? viagem.getValor() : 0D));
        return 0D;
    }

    public Double getValorPorViagem(){
        if(configuracaoViagem != null && !viagens.isEmpty()) {
            Double valor = configuracaoViagem.getValor();
            Long quantidade = viagens.stream().collect(Collectors.summingLong(viagemDTO -> viagemDTO.getTotalViagens()));

            return valor != null ? valor / quantidade : 0D;
        }

        return 0D;
    }

    public void listarInstituicoes() {
        instituicoes = instituicaoBusiness.obterInstituicoesEnsino();
    }

    public DiaSemanaEnum[] obterDiaSemana() {
        return DiaSemanaEnum.values();
    }

    public String obterPeriodo() {
        if (edital == null) {
            return "Edital Não Carregado";
        }
        String aberto = edital.getFinalizado() == null ? " (Aberto)" : " (Fechado)";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Período de inscrição: " + formato.format(edital.getInicio()) + " à "
                + formato.format(edital.getTermino()) + aberto;
    }

    public boolean isPeriodoAberto() {
        return edital != null && edital.getFinalizado() == null;
    }

    public boolean isDesabilitarValor() {
        return filtro != null && filtro.getInstituicao() == null;
    }

    public String obterPeriodo(Edital edital) {
        if (edital == null) {
            return "Edital não carregado";
        }
        String aberto = edital.getFinalizado() == null ? " (Aberto)" : " (Fechado)";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formato.format(edital.getInicio()) + " à "
                + formato.format(edital.getTermino()) + aberto;
    }

    private void carregarEdital() {
        if (idEdital != null) {
            edital = editalBusiness.listarEdital(idEdital);
        }
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public List<ViagemDTO> getViagens() {
        return viagens;
    }

    public void setViagens(List<ViagemDTO> viagens) {
        this.viagens = viagens;
    }

    public FiltroViagemDTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroViagemDTO filtro) {
        this.filtro = filtro;
    }

    public List<Edital> getListaEditais() {
        return listaEditais;
    }

    public void setListaEditais(List<Edital> listaEditais) {
        this.listaEditais = listaEditais;
    }

    public ConfiguracaoViagem getConfiguracaoViagem() {
        return configuracaoViagem;
    }

    public void setConfiguracaoViagem(ConfiguracaoViagem configuracaoViagem) {
        this.configuracaoViagem = configuracaoViagem;
    }


}
