package dto;

import java.io.Serializable;

public class BancoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idBanco;
	private String nome;

	public Long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
}
