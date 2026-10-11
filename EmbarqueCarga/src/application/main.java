package application;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		ArrayList<String> Mercadoria = new ArrayList<>();
		ArrayList<Double> Peso = new ArrayList<>();

		double pesoTotal = 0;
		String produto = null;
		double pesoProduto = 0;

		System.out.println("=== SISTEMA DE CONTROLE DE MERCADORIAS ===");
		System.out.println("Qual peso maximo suportado pelo caminhão? (em KG) ");
		double pesoMaximo = sc.nextDouble();

		while (pesoMaximo <= 0) {
			System.out.println("Valor inválido, insira novamente o valor: ");
			pesoMaximo = sc.nextInt();
			sc.nextLine();
		}

		System.out.println("Quantos produtos será inserido no caminhão? ");
		int quantidade = sc.nextInt();
		sc.nextLine();

		while (quantidade <= 0) {
			System.out.println("Valor inválido, insira novamente o valor da quantidade de produtos: ");
			quantidade = sc.nextInt();
			sc.nextLine();
		}
		for (int i = 0; i < quantidade; i++) {
			System.out.println("-------------------------------------");
			System.out.println("Mercadoria " + (i + 1) + " // Nome:");
			produto = sc.nextLine();
			System.out.println("Mercadoria " + (i + 1) + " // Peso:");
			pesoProduto = sc.nextDouble();
			sc.nextLine();

			while (pesoProduto <= 0) {
				System.out.println("Valor inválido, insira novamente o valor do peso: ");
				pesoProduto = sc.nextDouble();
				sc.nextLine();
			}

			if (pesoTotal + pesoProduto <= pesoMaximo) {
				Mercadoria.add(produto);
				Peso.add(pesoProduto);
				pesoTotal += pesoProduto;
				System.out.println("✅ Mercadoria aceita e colocada no caminhão!");

			} else {
				System.out.println("⚠️ Operação Negada! O caminhão não suporta este peso extra.");
			}
		}

		System.out.println("======= RELATÓRIO =========");
		for (int i = 0; i < Mercadoria.size(); i++) {
			String NomeMercadoria = Mercadoria.get(i);
			double PesoMercadoria = Peso.get(i);

			System.out.printf("- Item %d: %-20s | %.2f KG\n", (i + 1), NomeMercadoria, PesoMercadoria);
		}

		System.out.println("-----------------------------------------------");
		System.out.printf("> Total de itens carregados: %d\n", Mercadoria.size());
		System.out.printf("> Peso Total Final Acumulado: %.2f / %.2f KG\n", pesoTotal, pesoMaximo);
		System.out.printf("> Espaço Disponível Restante: %.2f KG\n", (pesoMaximo - pesoTotal));
		System.out.println("===============================================");

		sc.close();

	}

}
