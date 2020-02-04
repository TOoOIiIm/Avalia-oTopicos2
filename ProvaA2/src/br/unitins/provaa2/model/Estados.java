package br.unitins.provaa2.model;


public enum Estados {
	
	ACRE(1,1,"Acre"),
	AMAPA(2,1,"Amapá"),
	AMAZONAS(3,1,"Amazonas"),
	PARA(4,1,"Pará"),
	RONDONIA(5,1,"Rondônia"),
	RORAIMA(6,1,"Roraima"),
	TOCANTINS(7,1,"Tocantins"),
	ALAGOAS(8,2,"Alagoas"),
	BAHIA(9,2,"Bahia"),
	CEARA(10,2,"Ceará"),
	MARANHAO(11,2,"Maranhão"),
	PARAIBA(12,2,"Paraíba"),
	PERNANBUCO(13,2,"Pernanbuco"),
	PIAUI(14,2,"Piauí"),
	RIOGRANDEDONORTE(15,2,"Rio Grande do Norte"),
	SERGIPE(16,2,"Sergipe"),
	GOIAS(17,3,"Goiás"),
	MATOGROSSO(18,3,"Mato Grosso"),
	MATOGROSSODOSUL(19,3,"Mato Grosso do sul"),
	DISTRITOFEDERAL(20,3,"Distrito Federal"),
	ESPIRITOSANTO(21,4,"Espírito Santo"),
	MINASGERAIS(22,4,"Minas Gerais"),
	RIODEJANEIRO(23,4,"Rio de Janeiro"),
	SAOPAULO(24,4,"São Paulo"),
	PARANA(25,5,"Paraná"),
	RIOGRANDESOSUL(26,5,"Rio Grande do Sul"),
	SANTACATARINA(27,5,"Santa Catarina");
	
	private int id;
	private int gruporegiao;
	private String label;
	
	private Estados(int id,int gruporegiao ,String label) {
		this.id = id;
		this.gruporegiao = gruporegiao;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	public int getGruporegiao() {
		return gruporegiao;
	}
	public static Estados valueOf(int value) {
		for (Estados estado : Estados.values()) {
			if (estado.getId() == value) {
				return estado;
			}
		}
		return null;
	}
}
