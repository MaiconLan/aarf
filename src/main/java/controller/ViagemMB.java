package controller;

import business.EditalBusiness;
import business.InstituicaoBusiness;
import business.ViagemBusiness;
import dto.EditalDTO;
import dto.FiltroViagemDTO;
import enumered.DiaSemanaEnum;
import model.ConfiguracaoViagem;
import model.Edital;
import model.Instituicao;
import model.Viagem;
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

    private List<ConfiguracaoViagem> configuracoesEdital = new ArrayList();

    private List<Instituicao> instituicoes = new ArrayList();

    private List<Viagem> viagens = new ArrayList<>();

    private ConfiguracaoViagem configuracaoViagem;

    private Double valor;

    @Inject @Param
    private Long idEdital;

    private Edital edital;

    @PostConstruct
    public void init(){
        if(idEdital == null) {
            carregarListaEditais();
            abrirModalEditais();

        } else {
            filtro = new FiltroViagemDTO();
            carregarEdital();
            listarInstituicoes();
            novaConfiguracaoViagem();
            carregarConfiguracoesEdital();
        }
    }

    private void carregarListaEditais() {
        listaEditais = editalBusiness.consultarEdital(new EditalDTO());
    }

    private void abrirModalEditais(){
        RequestContext.getCurrentInstance().execute("PF('modalConsultaEditais').show();");
    }

    private void novaConfiguracaoViagem(){
        configuracaoViagem = new ConfiguracaoViagem();
    }

    public void removerConfiguracao(ConfiguracaoViagem configuracaoViagem){
        try {
            viagemBusiness.removerConfiguracao(configuracaoViagem);
            Messages.addInfo(null, "Configuracao removida com sucesso!");
            carregarConfiguracoesEdital();
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addError(null, "Não foi possível remover a configuração!");
        }
    }

    public void gerarValores(){
        configuracaoViagem.setDiaSemana(filtro.getDiaSemana());
        configuracaoViagem.setSentido(filtro.getSentido());
        configuracaoViagem.setEdital(edital);
        configuracaoViagem.setInstituicao(filtro.getInstituicao());
        configuracaoViagem.setValor(valor);

        viagemBusiness.gerarValores(configuracaoViagem, viagens);

        Messages.addInfo(null, "Valores gerados com sucesso!");
        viagens = new ArrayList<>();
        carregarConfiguracoesEdital();
    }

    public void buscarEstudantes(){
        filtro.setIdEdital(idEdital);
        viagens = viagemBusiness.buscarViagens(filtro);
    }

    private void carregarConfiguracoesEdital() {
        if(idEdital != null)
            configuracoesEdital = viagemBusiness.obterConfiguracoesViagemEdital(idEdital);
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

    public String obterPeriodo(Edital edital) {
        if (edital == null) {
            return "Edital Não Carregado";
        }
        String aberto = edital.getFinalizado() == null ? " (Aberto)" : " (Fechado)";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Período de inscrição: " + formato.format(edital.getInicio()) + " à "
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<ConfiguracaoViagem> getConfiguracoesEdital() {
        return configuracoesEdital;
    }

    public void setConfiguracoesEdital(List<ConfiguracaoViagem> configuracoesEdital) {
        this.configuracoesEdital = configuracoesEdital;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
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
