package br.unitins.provaa2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import br.unitins.provaa2.constraint.MySize;
public class Usuario {
	
	
	@NotBlank(message="deve ser informado")
	private String nome;
	
	@NotBlank(message="deve ser informado")
	private String sobrenome;
	
	private LocalDate datanascimento;
	
	@CPF(message="cpf invalido")
	private String cpf;
	
	@MySize(min=2,max=28,message = "tamanho incompativell")
	@Email(message="Email invalido")
	private String login;
	
	@MySize(min=2,max=50,message = "tamanho incompativel")
	private String senha;
	
	private Endereso endereso;
	private List<Cartao> cartoes;
	private List<Telefone> telefone;
	private List<ListaDeCompras> compras;
	
	public Usuario() {}
	
	
	public Usuario(String login,String senha,String nome, String sobrenome,LocalDate datanascimento, String cpf, Endereso endereso,
			List<Cartao> cartoes, List<Telefone> telefone) {
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.datanascimento = datanascimento;
		this.cpf = cpf;
		this.endereso = endereso;
		this.cartoes = cartoes;
		this.telefone = telefone;
	}

	
	public List<ListaDeCompras> getCompras() {
		return compras;
	}
	
	public void setCompras(List<ListaDeCompras> compras) {
		this.compras = compras;
	}
	
	public LocalDate getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(LocalDate datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Endereso getEndereso() {
		return endereso;
	}
	public void setEndereso(Endereso endereso) {
		this.endereso = endereso;
	}
	public List<Cartao> getCartoes() {
		if(cartoes == null) {
			cartoes = new ArrayList<Cartao>();
		}
		return cartoes;
	}
	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}
	public List<Telefone> getTelefone() {
		if(telefone == null) {
			telefone = new ArrayList<Telefone>();
		}
		return telefone;
	}
	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}
}
