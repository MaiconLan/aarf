package enumered;

public enum SerevidadeEnum {
  	ALTA("Alta"),
    NORMAL("Normal"),
    BAIXA("Baixa");
	private String descricao;
	
	SerevidadeEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

