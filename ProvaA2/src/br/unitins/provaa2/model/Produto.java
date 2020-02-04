package br.unitins.provaa2.model;

public class Produto {
	
	private String nome;
	private Double valor;
	private String descrisao;
	private TipoProduto produto;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getDescrisao() {
		return descrisao;
	}
	public void setDescrisao(String descrisao) {
		this.descrisao = descrisao;
	}
	public TipoProduto getProduto() {
		return produto;
	}
	public void setProduto(TipoProduto produto) {
		this.produto = produto;
	}
}
