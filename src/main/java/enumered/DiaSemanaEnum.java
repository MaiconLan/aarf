package enumered;

public enum DiaSemanaEnum {

	SEGUNDA_FEIRA("Segunda-Feira"),
	TERCA_FEIRA("Terça-Feira"),
	QUARTA_FEIRA("Quarta-Feira"),
	QUINTA_FEIRA("Quinta-Feira"),
	SEXTA_FEIRA("Sexta-Feira");
	
	DiaSemanaEnum(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}
}
