package br.unitins.provaa2.model;

import javax.validation.constraints.NotBlank;

public class Telefone {
	
	@NotBlank(message = "campo nescessário")
	private String ddd;
	@NotBlank(message = "campo nescessário")
	private String codigoarea;
	
	public Telefone() {}
	
	public Telefone(@NotBlank(message = "campo nescessário") String ddd,
			@NotBlank(message = "campo nescessário") String codigoarea) {
		super();
		this.ddd = ddd;
		this.codigoarea = codigoarea;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getCodigoarea() {
		return codigoarea;
	}
	public void setCodigoarea(String codigoarea) {
		this.codigoarea = codigoarea;
	}
	
	

}
