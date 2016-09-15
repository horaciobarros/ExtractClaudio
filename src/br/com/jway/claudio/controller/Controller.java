package br.com.jway.claudio.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.jway.claudio.service.ExtractorService;
import br.com.jway.claudio.util.TrataTxts;
import br.com.jway.claudio.util.Util;

/**
 * 
 * @author jway
 *
 */
public class Controller {

	private ExtractorService extractorService = new ExtractorService();

	public void importaNfe() {

		int nivelProcessamento = 1;
		
		System.out.println("-- Iniciando o processo de extra��o de dados no n�vel " + nivelProcessamento);

		// limpando o banco
		System.out.println("Limpando o banco...");

		List<String> entidades = new ArrayList<String>();
		
		if (nivelProcessamento == 1) {
			entidades = extractorService.excluiParaProcessarNivel1();
		} else if (nivelProcessamento == 2) {
			entidades = extractorService.excluiParaProcessarNivel2();
		} else if (nivelProcessamento == 3) {
			entidades = extractorService.excluiParaProcessarNivel3();
		} else if (nivelProcessamento == 4) {
			entidades = extractorService.excluiParaProcessarNivel4();
		} else if (nivelProcessamento == 5) {
			entidades = extractorService.excluiParaProcessarNivel5();
		}

		for (String nomeEntidade : entidades) {
			try {
				extractorService.excluiDados(nomeEntidade);
			} catch (Exception e) {
				System.out.println("Entidade sendo excluida:" + nomeEntidade);
				e.printStackTrace();
			}
		}

		// Tratando os arquivos
		System.out.println("Tratando arquivos...");
		TrataTxts tratamento = new TrataTxts();
		tratamento.processa();

		// In�cio
		System.out.println("Importa��o de dados de arquivos txt - In�cio: " + Util.getDataHoraAtual());
		List<String> dadosList;

		if (nivelProcessamento == 1) {
			System.out.println("Lendo contribuintes");
			dadosList = extractorService.lerArquivosClaudio("contribuintes");
			System.out.println("Gravando contribuintes e prestadores");
			extractorService.processaDadosContribuinte(dadosList);
			System.out.println("--- Fim de contribuintes ---");

		}

		if (nivelProcessamento <= 2) {			
			System.out.println("Lendo Servi�os Origem");
			dadosList = extractorService.lerArquivosClaudio("Servicos");
			extractorService.processaDadosServicosArquivoOrigem(dadosList);
			System.out.println("--- Fim de Servi�os Origem ---");

			System.out.println("Lendo atividade de contribuintes e servicos");
			dadosList = extractorService.lerArquivosClaudio("cnae_servicos_contribuintes");
			System.out.println("Gravando servicos");
			extractorService.processaDadosServicosArquivoCnae(dadosList);
			System.out.println("--- Fim de servicos ---");

			System.out.println("Gravando atividades");
			extractorService.processaDadosAtividadeEconomicaContribuinte(dadosList);
			System.out.println("--- Fim de atividade de contribuintes ---");
			
		}
		if (nivelProcessamento <= 3) {
			System.out.println("Gravando competencias");
			extractorService.incluiCompetencias();
			System.out.println("--- Fim de competencias ---");

			System.out.println("Lendo guias "); // a ordem � essa mesma
			dadosList = extractorService.lerArquivosClaudio("Guias");
			System.out.println("Gravando guias e ajustando competencias");
			extractorService.processaDadosGuias(dadosList);
			System.out.println("--- Fim de guias paga ---");

		}

		if (nivelProcessamento <= 4) {
	
			System.out.println("Lendo escrituracoes");
			dadosList = extractorService.lerArquivosClaudio("Escrituracoes");
			extractorService.processaDadosEscrituracoes(dadosList);
			System.out.println("--- Fim de Escrituracoes ---");
			
			System.out.println("Lendo Servi�os Notas Fiscais");
			dadosList = extractorService.lerArquivosClaudio("servicos_notas_fiscais");
			System.out.println("Guardando Servi�os Notas Fiscais em Map");
			extractorService.processaDadosServicosNotasFiscais(dadosList);
			System.out.println("--- Fim de Servi�os Notas fiscais ---");

			System.out.println("Lendo Notas Fiscais  " + Util.getDataHoraAtual());
			dadosList = extractorService.lerArquivosClaudio("notas_fiscais");
			System.out.println("Gravando Notas Fiscais" + Util.getDataHoraAtual());
			try {
				extractorService.processaDadosNotasFiscais(dadosList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("--- Fim de Notas Fiscais ---");

		}

		if (nivelProcessamento <= 5) {
			System.out.println("Gravando Notas Fiscais substituidas");
			extractorService.processaDadosNotasFiscaisSubstituidas();
			System.out.println("--- Fim de Notas Fiscais substituidas ---");

			System.out.println("Lendo guias notas fiscais");
			extractorService.processaDadosGuiasNotasFiscais();
			System.out.println("--- Fim de guias notas fiscais ---");

		}
		
		if (nivelProcessamento <= 6) {
			
			System.out.println("Iniciando processo de excluir guias sem notas... ");
			extractorService.excluiGuiasSemNotas();
			
			System.out.println("Limpando Prestadores Sem Notas");
			extractorService.processaExclusaoPrestadoresSemNotas();
		}

		System.out.println("--- Processo encerrado. " + Util.getDataHoraAtual() + " Registros gravados: ");

		for (String nomeEntidade : entidades) {
			try {
				System.out.println(nomeEntidade + " -->" + extractorService.count(nomeEntidade));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Util.desligarComputador();

	}

	public Controller() {

	}

}
