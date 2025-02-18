package model.entities;

public class Passageiro {
	private String nome;
	private int idade;
	private float tarifa;
	public static final float tarifaInteira = 5;

	public Passageiro(String nome, int idade, float tarifa) {
		this.nome = nome;
		this.idade = idade;
		this.tarifa = tarifa;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public float getTarifa(float tarifa) {
		return tarifa;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + ", idade: " + idade;
	}
	
	

}
