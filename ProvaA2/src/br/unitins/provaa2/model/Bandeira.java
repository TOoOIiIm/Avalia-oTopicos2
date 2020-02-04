package br.unitins.provaa2.model;

public enum Bandeira {

	MASTERCARD(1,"Master Card"),
	VISA(2,"Visa"),
	AMERICANEXPRESS(3,"American Express"),
	ELO(4,"Elo");
	
	private int id;
	private String label;
	
	private Bandeira(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	public static Bandeira valueOf(int value) {
		for (Bandeira bandeira : Bandeira.values()) {
			if (bandeira.getId() == value) {
				return bandeira;
			}
		}
		return null;
	}
	
}
