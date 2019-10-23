package controller;

import business.EditalBusiness;
import business.InstituicaoBusiness;
import business.RelatorioMatriculaBusiness;
import exception.MatriculaBusinessException;
import freemarker.relatorio.matricula.EstudanteFM;
import model.Edital;
import model.Estudante;
import model.Instituicao;
import org.omnifaces.util.Messages;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("relatorioMatriculaMB")
public class RelatorioMatriculaMB implements Serializable {

    private static final long serialVersionUID = -5232697993128407117L;

    @Inject
    private RelatorioMatriculaBusiness relatorioMatriculaBusiness;

    @Inject
    private InstituicaoBusiness instituicaoBusiness;

    @Inject
    private EditalBusiness editalBusiness;

    private List<Instituicao> instituicoes;

    private List<Edital> editais;

    private Long idEdital;
    private Long idInstituicao;

    @PostConstruct
    public void init() {
        instituicoes = instituicaoBusiness.obterInstituicoesEnsino();
        editais = editalBusiness.listarEditais();
    }

    public StreamedContent gerarRelatorio(){
        try {
            return relatorioMatriculaBusiness.gerarRelatorioPdf(idEdital, idInstituicao);
        } catch (MatriculaBusinessException e) {
            Messages.addError(null, e.getMessage());
        }
        return null;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public List<Edital> getEditais() {
        return editais;
    }

    public void setEditais(List<Edital> editais) {
        this.editais = editais;
    }

    public Long getIdEdital() {
        return idEdital;
    }

    public void setIdEdital(Long idEdital) {
        this.idEdital = idEdital;
    }

    public Long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }
}
