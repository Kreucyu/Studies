package model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Viagem {
	private String placaOnibus;
	private String nomeMotorista;
	private Date dataViagem;
	private int limiteViagem;
	protected List<Passageiro> passageiros;
	protected List<Float> recebidos;

	public Viagem(String placaOnibus, String nomeMotorista, Date dataViagem, int limiteViagem) {
		this.placaOnibus = placaOnibus;
		this.nomeMotorista = nomeMotorista;
		this.dataViagem = dataViagem;
		this.limiteViagem = limiteViagem;
		this.passageiros = new ArrayList<>();
		this.recebidos = new ArrayList<>();
	}

	public String getPlacaOnibus() {
		return placaOnibus;
	}

	public String getNomeMotorista() {
		return nomeMotorista;
	}

	public Date getDataViagem() {
		return dataViagem;
	}

	public List<Passageiro> getPassageiros() {
		return passageiros;
	}

	public abstract void addPassageiro(Passageiro passageiro, int limiteViagem);

	public abstract float getValorTotal(float tarifa);

	public abstract String toString();
}
