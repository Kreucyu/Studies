package model.entities;

public class Estudante extends Passageiro {
	private String escola;

	public Estudante(String nome, int idade, float tarifa, String escola) {
		super(nome, idade, tarifa);
		this.escola = escola;
	}

	public String getEscola() {
		return escola;
	}

	@Override
	public float getTarifa(float tarifa) {
		return tarifa;
	}

}
