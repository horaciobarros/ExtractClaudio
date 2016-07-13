package br.com.jway.claudio.controller;

import java.util.List;

import br.com.jway.claudio.service.ExtractorService;

/**
 * 
 * @author jway
 *
 */
public class Controller {

	private ExtractorService extractorService = new ExtractorService();

	public void importaNfe() {

		int nivelProcessamento = 4;

		// limpando o banco
		System.out.println("Limpando o banco...");

		List<String> entidades = null;
		if (nivelProcessamento == 2) {
			entidades = extractorService.excluiParaProcessarNivel2();
		} else if (nivelProcessamento == 3) {
			entidades = extractorService.excluiParaProcessarNivel3();
		} else if (nivelProcessamento == 1) {
			entidades = extractorService.excluiParaProcessarNivel1();
		} else if (nivelProcessamento == 4) {
			entidades = extractorService.excluiParaProcessarNivel4();
		}

		for (String nomeEntidade : entidades) {
			try {
				extractorService.excluiDados(nomeEntidade);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Inï¿½cio
		System.out.println("Importação de dados de arquivos txt - Início");
		List<String> dadosList;

		if (nivelProcessamento == 1) {
			System.out.println("Lendo contribuintes");
			dadosList = extractorService.lerArquivo("contribuinte");
			System.out.println("Gravando contribuintes");
			extractorService.processaDadosContribuinte(dadosList);
			System.out.println("--- Fim de contribuintes ---");

			System.out.println("Lendo atividade de contribuintes");
			dadosList = extractorService.lerArquivo("AtvEconomica");
			System.out.println("Gravando atividades");
			extractorService.processaDadosAtividadeEconomicaContribuinte(dadosList);
			System.out.println("--- Fim de atividade de contribuintes ---");
		}

		if (nivelProcessamento <= 2) {
			System.out.println("Lendo guias pagas"); // a ordem é essa mesma
			dadosList = extractorService.lerArquivo("GuiasPagas");

			extractorService.processaDadosGuiasPagas(dadosList);
			System.out.println("--- Fim de guias paga ---");

			System.out.println("Lendo guias geradas");
			dadosList = extractorService.lerArquivo("GuiasGeradas");
			System.out.println("Gravando competencias e guias");
			extractorService.processaDadosGuiasGeradas(dadosList);
			System.out.println("--- Fim de guias geradas ---");

			System.out.println("Lendo guias DA");
			dadosList = extractorService.lerArquivo("GuiasDA");
			extractorService.processaDadosGuiasDA(dadosList);
			System.out.println("--- Fim de guias DA ---");

			System.out.println("Lendo guias suspensas");
			dadosList = extractorService.lerArquivo("GuiasSuspensas");
			extractorService.processaDadosGuiaSuspensas(dadosList);
			System.out.println("--- Fim de guias suspensas ---");

			System.out.println("Lendo guias canceladas");
			dadosList = extractorService.lerArquivo("GuiasCanceladas");
			extractorService.processaDadosGuiaCanceladas(dadosList);
			System.out.println("--- Fim de guias canceladas ---");
		}

		if (nivelProcessamento <= 3) {

			System.out.println("Lendo Notas Fiscais");
			dadosList = extractorService.lerArquivo("NotasFiscais");
			System.out.println("Gravando Notas Fiscais");
			extractorService.processaDadosNotasFiscais(dadosList);
			System.out.println("--- Fim de Notas Fiscais ---");
		}

		System.out.println("Lendo Associação Guias x Notas Fiscais");
		dadosList = extractorService.lerArquivo("GuiasNFS-e");
		extractorService.processaAssociacaoGuiasNotasFiscais(dadosList);
		System.out.println("--- Fim de Associação Guias x Notas Fiscais ---");

		System.out.println("--- Processo encerrado. Registros gravados: ");

		for (String nomeEntidade : entidades) {
			try {
				System.out.println(nomeEntidade + " -->" + extractorService.count(nomeEntidade));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
