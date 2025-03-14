package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Atleta implements Serializable, Comparable<Atleta> {
	private static final long serialVersionUID = 1L;
	private String nome;
	private String cpf;
	private float altura;
	private float peso;
	private Endereco endereco;

	public Atleta(String nome, String cpf, float altura, float peso, Endereco endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.altura = altura;
		this.peso = peso;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public float getAltura() {
		return altura;
	}

	public float getPeso() {
		return peso;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	

	@Override
	public int compareTo(Atleta outro) {
		return this.cpf.compareTo(outro.cpf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atleta other = (Atleta) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return "Nome: " + nome + ", CPF: " + cpf + ", Altura: " + altura + ", Peso: " + peso + ", Endereço: "+ endereco;
	}

	
	
	
	
	

}
