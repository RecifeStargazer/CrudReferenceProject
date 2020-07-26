package com.compasso.teste.enums;

public enum SexoEnum {
	

	FEMININO(1, "FEMININO"), 
	MASCULINO(2, "MASCULINO"),
	;
	private int codigo;

	private String descricao;

	private SexoEnum(int codigo, String descricao) {
		
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static SexoEnum toEnum(Integer codigo) {
		if (codigo==null) {
			return null;
		}
		
		for (SexoEnum tp : SexoEnum.values()) {
			if(codigo.equals(tp.getCodigo())) {
				return tp;
			}
		}
		
		throw new IllegalArgumentException("Sexo Inválido : "+codigo);		
	}

}
