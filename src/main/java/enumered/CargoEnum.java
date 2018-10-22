package enumered;

public enum CargoEnum {
  	PRESIDENTE("Presidente"),
    VICE_PRECIDENTE("Vice Precidente"),
    SECRETARIO("Secret�rio"),
    TESOUREIRO("Tesoureiro");
 
	private String descricao;
	
	CargoEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

