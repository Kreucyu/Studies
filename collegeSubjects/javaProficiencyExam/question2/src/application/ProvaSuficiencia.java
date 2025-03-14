package application;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import model.entities.Atleta;
import model.entities.Endereco;
import model.exception.EArquivoOrigemIncorreto;

public class ProvaSuficiencia {
	
	//Aluno: Elcio Cleiton Wippel

	public static void main(String[] args) throws EArquivoOrigemIncorreto {
		Scanner sc = new Scanner(System.in);
		System.out.print("Qual o caminho do arquivo de origem? ");
		String arqOrigem = sc.next();
		System.out.print("\nDigite o caminho onde o arquivo serializado será salvo: ");
		String arqDestino = sc.next();
		Locale.setDefault(Locale.US);
		serializar(arqOrigem, arqDestino);
	}

	public static void serializar(String arqOrigem, String arqDestino) throws EArquivoOrigemIncorreto {
		Set<Atleta> atletas = new TreeSet<>();
		try (BufferedReader br = new BufferedReader(new FileReader(arqOrigem))) {
			String linha = br.readLine();
			while (linha != null) {
				String[] vet = linha.split(",");
				String nome = vet[0];
				String cpf = vet[1];
				float altura = Float.parseFloat(vet[2]);
				float peso = Float.parseFloat(vet[3]);
				String rua = vet[4];
				String numero = vet[5];
				String complemento = vet[6];
				if (complemento == "") {
					complemento = "Não possui";
				}
				String CEP = vet[7];
				String cidade = vet[8];
				String estado = vet[9];
				Atleta atleta = new Atleta(nome, cpf, altura, peso, new Endereco(rua, numero, complemento, CEP, cidade, estado));
				atletas.add(atleta);
				linha = br.readLine();
			}
		} catch (IOException e) {
			throw new EArquivoOrigemIncorreto("Erro: " + e.getMessage());
		}
		System.out.println("\nDados a ser serializados em ordem de CPF:");
		Iterator<Atleta> iterator = atletas.iterator();
		while(iterator.hasNext()) {
			Atleta atleta = iterator.next();
			System.out.println(atleta.toString());
		}
		try (ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arqDestino + "\\arqDestino.ser"))) {
			saida.writeObject(atletas);
			System.out.println("\nArquivo serializado!");

		} catch (IOException e) {
			throw new EArquivoOrigemIncorreto("Erro: " + e.getMessage());
		} 
		
	}

}
