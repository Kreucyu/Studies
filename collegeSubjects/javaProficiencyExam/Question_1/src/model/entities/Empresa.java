package model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Empresa {
	private List<Viagem> viagens;

	public Empresa() {	
		this.viagens = new ArrayList<>();
	}
	
	public List<Viagem> getViagens() {
		return viagens;
	}
	
	public void addViagem(Viagem viagem) {
		viagens.add(viagem);
	}

	public List<Passageiro> getPassageirosMaisIdosos() {
		List<Passageiro> todos = new ArrayList<>();
		for (Viagem v : viagens) {
			todos.addAll(v.getPassageiros());
		}
		List<Passageiro> passageirosMaisIdosos = todos.stream().filter(p -> p.getIdade() >= 60).collect(Collectors.toList());
		return passageirosMaisIdosos;

	}

}
