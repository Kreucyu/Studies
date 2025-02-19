package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	private String rua;
	private String numero;
	private String complemento;
	private String CEP;
	private String cidade;
	private String estado;

	public Endereco(String rua, String numero, String complemento, String cEP, String cidade, String estado) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		CEP = cEP;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCEP() {
		return CEP;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CEP, cidade, complemento, estado, numero, rua);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(CEP, other.CEP) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(complemento, other.complemento) && Objects.equals(estado, other.estado)
				&& Objects.equals(numero, other.numero) && Objects.equals(rua, other.rua);
	}

	@Override
	public String toString() {
		return "Rua: " + rua + ", Número: " + numero + ", Complemento: " + complemento + ", CEP: " + CEP
				+ ", Cidade: " + cidade + ", Estado: " + estado;
	}
	
	
	
	

	

}
