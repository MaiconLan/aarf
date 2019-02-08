package enumered;

public enum MatriculaSituacao {

    INSCRICAO("Inscricao"),
    EM_APROVACAO("Em Aprovação"),
    MATRICULADO("Matriculado"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private String descricao;

    MatriculaSituacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
