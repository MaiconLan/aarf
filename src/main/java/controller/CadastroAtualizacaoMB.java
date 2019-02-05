package controller;

import model.Atualizacao;
import org.omnifaces.util.Messages;
import service.AtualizacaoService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named("cadastroAtualizacaoMB")
public class CadastroAtualizacaoMB implements Serializable {

    private static final long serialVersionUID = -5319505388371020513L;

    @Inject
    private AtualizacaoService atualizacaoService;

    private Atualizacao atualizacao;

    @PostConstruct
    public void init(){
        atualizacao = new Atualizacao();
        atualizacao.setObservacao("Está sendo implantada a versão mais recente do sistema.");
    }

    public void salvar(){
        atualizacaoService.salvar(atualizacao);
        Messages.addInfo(null, "Atualização salva com sucesso!");
    }


    public Atualizacao getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(Atualizacao atualizacao) {
        this.atualizacao = atualizacao;
    }
}
