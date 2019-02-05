package business;

import dao.AtualizacaoDAO;
import model.Atualizacao;

import javax.inject.Inject;

public class AtualizacaoBusiness {

    @Inject
    private AtualizacaoDAO atualizacaoDAO;

    public boolean possuiAtualizacaoPendente(){
        return atualizacaoDAO.possuiAtualizacaoPendente();
    }

    public Atualizacao obterAtualizacaoPendente() {
        return atualizacaoDAO.obterAtualizacaoPendente();
    }

    public void concluir(Atualizacao atualizacao) {
        atualizacao.setConcluido(Boolean.TRUE);

        if(atualizacao.getIdAtualizacao() == null)
            atualizacaoDAO.save(atualizacao);
        else
            atualizacaoDAO.update(atualizacao);

    }

    public void salvar(Atualizacao atualizacao) {
        atualizacaoDAO.save(atualizacao);
    }
}
