package br.unitins.provaa2.model;


public enum TipoProduto {
	
	VERDURA(1,"Verdura"),
	FRUTA(2,"Fruta"),
	CARNES(3,"Carne"),
	DOCES(4,"Doce"),
	BEBIDAS(5,"Bebida");
	
	private int id;
	private String label;
	
	private TipoProduto(int id, String label) {
		this.id = id;
		this.label = label;
	}
	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	public static TipoProduto valueOf(int value) {
		for (TipoProduto tipoproduto : TipoProduto.values()) {
			if (tipoproduto.getId() == value) {
				return tipoproduto;
			}
		}
		return null;
	}
	
}
