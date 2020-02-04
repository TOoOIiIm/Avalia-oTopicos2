package br.unitins.provaa2.model;

import javax.validation.constraints.NotBlank;

public class Telefone {
	
	@NotBlank(message = "campo nescess�rio")
	private String ddd;
	@NotBlank(message = "campo nescess�rio")
	private String codigoarea;
	
	public Telefone() {}
	
	public Telefone(@NotBlank(message = "campo nescess�rio") String ddd,
			@NotBlank(message = "campo nescess�rio") String codigoarea) {
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
