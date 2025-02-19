package model.entities;

public class Idoso extends Passageiro {
	private String RG;

	public Idoso(String nome, int idade, float tarifa, String RG) {
		super(nome, idade, tarifa);
		this.RG = RG;
	}

	public String getRG() {
		return RG;
	}

	@Override
	public float getTarifa(float tarifa) {
		return tarifa;
	}

}
