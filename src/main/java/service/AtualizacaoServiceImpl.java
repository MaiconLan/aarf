package service;

import business.AtualizacaoBusiness;
import model.Atualizacao;

import javax.inject.Inject;

public class AtualizacaoServiceImpl implements AtualizacaoService {

    @Inject
    private AtualizacaoBusiness atualizacaoBusiness;

    @Override
    public void concluir(Atualizacao atualizacao) {
        atualizacaoBusiness.concluir(atualizacao);
    }

    @Override
    public boolean possuiAtualizacaoPendente() {
        return atualizacaoBusiness.possuiAtualizacaoPendente();
    }

    @Override
    public Atualizacao obterAtualizacaoPendente() {
        return atualizacaoBusiness.obterAtualizacaoPendente();
    }

    @Override
    public void salvar(Atualizacao atualizacao) {
        atualizacaoBusiness.salvar(atualizacao);
    }
}
