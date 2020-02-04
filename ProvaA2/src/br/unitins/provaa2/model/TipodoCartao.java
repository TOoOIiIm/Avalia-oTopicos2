package br.unitins.provaa2.model;

public enum TipodoCartao {

	CREDITO(1,"Credito"),
	DEBITO(2,"Debito");
	
	private int id;
	private String label;
	
	private TipodoCartao(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	public static TipodoCartao valueOf(int value) {
		for (TipodoCartao tipo  : TipodoCartao.values()) {
			if (tipo.getId() == value) {
				return tipo;
			}
		}
		return null;
	}
}
