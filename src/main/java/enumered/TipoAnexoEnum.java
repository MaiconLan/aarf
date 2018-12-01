package enumered;

public enum TipoAnexoEnum {

    COMPROVANTE_MATRICULA("Comprovante de Matrícula"),
    PRESTACAO_CONTA("Prestação de Conta");

    private String descricao;

    TipoAnexoEnum(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
