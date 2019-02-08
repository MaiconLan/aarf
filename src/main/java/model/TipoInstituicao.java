package model;

public enum TipoInstituicao {

    FINANCEIRA("Financeira"),
    ENSINO("Ensino");

    String descricao;

    TipoInstituicao(String descricao) {
        this.descricao = descricao;
    }

    public boolean equals(TipoInstituicao instituicao){
        return this.descricao.equals(instituicao.descricao);
    }

    public String getDescricao() {
        return descricao;
    }
}