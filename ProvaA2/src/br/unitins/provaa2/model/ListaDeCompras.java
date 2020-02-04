package br.unitins.provaa2.model;

import java.util.List;

public class ListaDeCompras {
	
	private List<Produto> produtos;
	private String nome;
	
	public ListaDeCompras() {}
	public ListaDeCompras(List<Produto> produtos, String nome) {
		super();
		this.produtos = produtos;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
