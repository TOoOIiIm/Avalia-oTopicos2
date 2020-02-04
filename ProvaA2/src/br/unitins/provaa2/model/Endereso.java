package br.unitins.provaa2.model;

import javax.validation.constraints.NotEmpty;

import br.unitins.provaa2.constraint.NaoNulo;

public class Endereso {

	@NaoNulo(message="deve ser preenchido")
	private Estados estado;
	
	@NotEmpty(message="deve ser preenchido")
	private String cep;
	
	@NotEmpty(message="deve ser preenchido")
	private String rua;
	
	@NotEmpty(message="deve ser preenchido")
	private String numero;
	
	public Endereso() {}
	
	public Endereso(String cep, String rua, String numero, Estados estado) {
		super();
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Estados getEstado() {
		return estado;
	}
	public void setEstado(Estados estado) {
		this.estado = estado;
	}

}
