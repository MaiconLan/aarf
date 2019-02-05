package service;

import model.Atualizacao;

import java.io.Serializable;

public interface AtualizacaoService extends Serializable {

    void concluir(Atualizacao atualizacao);

    boolean possuiAtualizacaoPendente();

    Atualizacao obterAtualizacaoPendente();

    void salvar(Atualizacao atualizacao);
}
