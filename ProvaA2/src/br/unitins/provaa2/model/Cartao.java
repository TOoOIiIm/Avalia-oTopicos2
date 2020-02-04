package br.unitins.provaa2.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.br.CPF;

import br.unitins.provaa2.constraint.DateNull;
import br.unitins.provaa2.constraint.NaoNulo;

public class Cartao {
	
	@NaoNulo(message="deve ser preenchido")
	private Bandeira bandeira;
	
	@NaoNulo(message="deve ser preenchido")
	private TipodoCartao tipo;
	
	@CreditCardNumber(message="numero invalido")
	@NotBlank(message = "o campo deve ser preenchido")
	private String numerocartao;
	
	@DateNull(message="necessario data")
	private LocalDate validadecartao;
	
	private int cvv;
	
	@NotBlank(message = "o campo n pode ser vazio")
	private String nomenocartao;
	
	@CPF(message="cpf invalido")
	@NotBlank(message = "o campo n pode ser vazio")
	private String cpf;
	
	public Cartao() {}
	public Cartao(Bandeira bandeira, TipodoCartao tipo, String numerocartao,LocalDate validadecartao, int cvv, String nomenocartao,
			String cpf) {
		super();
		this.bandeira = bandeira;
		this.tipo = tipo;
		this.numerocartao = numerocartao;
		this.validadecartao = validadecartao;
		this.cvv = cvv;
		this.nomenocartao = nomenocartao;
		this.cpf = cpf;
	}
	
	public String getNumerocartao() {
		return numerocartao;
	}
	public void setNumerocartao(String numerocartao) {
		this.numerocartao = numerocartao;
	}
	public Bandeira getBandeira() {
		return bandeira;
	}
	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}
	public TipodoCartao getTipo() {
		return tipo;
	}
	public void setTipo(TipodoCartao tipo) {
		this.tipo = tipo;
	}
	public LocalDate getValidadecartao() {
		return validadecartao;
	}
	public void setValidadecartao(LocalDate validadecartao) {
		this.validadecartao = validadecartao;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getNomenocartao() {
		return nomenocartao;
	}
	public void setNomenocartao(String nomenocartao) {
		this.nomenocartao = nomenocartao;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
