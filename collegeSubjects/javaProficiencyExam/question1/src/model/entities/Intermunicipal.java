	package model.entities;
	
	import java.util.Date;
	
	import model.exceptions.ExcecoesViagem;
	
	public class Intermunicipal extends Viagem {
	
		public Intermunicipal(String placaOnibus, String nomeMotorista, Date dataViagem, int limiteViagem) {
			super(placaOnibus, nomeMotorista, dataViagem, limiteViagem);
		}
	
		@Override
		public void addPassageiro(Passageiro passageiro, int limiteViagem) {
			if (limiteViagem == 0) {
				System.out.println("\nViagem inválida!");
			} else {
				if (passageiros.size() >= limiteViagem)
					throw new ExcecoesViagem("\nÔnibus cheio!");
				else {
					passageiros.add(passageiro);
					
				}
			}
		}
	
		@Override
		public float getValorTotal(float tarifa) {
			float total = 0;
			recebidos.add(tarifa);
			for (float tarifas : recebidos) {
				total += tarifas;
			}
			for (Passageiro p : passageiros) {
				total += 3.15;
			}
			return total;
		}
	
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("(intermunicipal) R$ " + String.format("%.2f", getValorTotal(0)));
			return sb.toString();
	
		}
	}
