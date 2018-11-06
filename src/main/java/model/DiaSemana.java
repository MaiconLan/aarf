package model;

public enum DiaSemana {

	SegundaFeira("Segunda-Feira"),
	TercaFeira("Terça-Feira"),
	QuartaFeira("Quarta-Feira"),
	QuintaFeira("Quinta-Feira"),
	SextaFeira("Sexta-Feira");
	
	DiaSemana(String valor) {
		
	}
	
	private String valor;

	public String getValor() {
		return valor;
	}

	private void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
}
