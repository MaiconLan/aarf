package controller;

import model.Anexo;
import model.PrestacaoConta;
import service.AnexoService;
import service.PrestacaoContaService;

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
    private PrestacaoContaService prestacaoContaService;

    @Inject
    private AnexoService anexoService;

    private PrestacaoConta prestacaoConta;


    @Override
    protected void salvarAnexos(String caminho, String arquivo) {
        Anexo anexo = new Anexo();
        anexo.setCaminho(caminho);
        anexo.setNome(arquivo);
        anexo.setTipo("Prestaçao Conta");
        prestacaoConta.getAnexos().add(anexo);
        anexoService.salvarAnexo(anexo);
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
