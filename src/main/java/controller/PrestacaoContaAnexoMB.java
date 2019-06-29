package controller;

import business.AnexoBusiness;
import business.PrestacaoContaBusiness;
import enumered.TipoAnexoEnum;
import model.Anexo;
import model.PrestacaoConta;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@ViewScoped
@Named("prestacaoContaAnexoMB")
public class PrestacaoContaAnexoMB extends ArquivoAbstract implements Serializable {


    private static final long serialVersionUID = -1678022061386761858L;
    private static final String DIRETORIO_MODULO = "prestacao_contas\\";

    @Inject
    private PrestacaoContaBusiness prestacaoContaBusiness;

    @Inject
    private AnexoBusiness anexoBusiness;

    private PrestacaoConta prestacaoConta;

    @Override
    protected void salvarAnexos(Anexo anexo) {
        anexo.setTipo(TipoAnexoEnum.PRESTACAO_CONTA.getDescricao());
        prestacaoConta.getAnexos().add(anexo);
        anexoBusiness.salvarAnexo(anexo);
    }

    @Override
    protected void removerAnexo(Anexo anexo) {
        prestacaoConta.getAnexos().remove(anexo);
    }

    @Override
    protected String obterDiretorioModulo() {
        return DIRETORIO_MODULO;
    }

    public PrestacaoConta getPrestacaoConta(){
        return prestacaoConta;
    }

    public void setPrestacaoConta(PrestacaoConta prestacaoConta){
        this.prestacaoConta = prestacaoConta;
    }
}
