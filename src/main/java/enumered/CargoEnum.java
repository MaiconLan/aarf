package enumered;

public enum CargoEnum {
  	ADMINISTRADOR("Administrador"),
  	PRESIDENTE("Presidente"),
    VICE_PRECIDENTE("Vice Presidente"),
    SECRETARIO("Secretário"),
    TESOUREIRO("Tesoureiro");
 
	private String descricao;
	
	CargoEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

