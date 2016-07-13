package br.com.jway.claudio.util;

import java.util.List;

import br.com.jway.claudio.service.ExtractorService;

public class MainTest {

	public static void main(String args[]) {

		ExtractorService extractorService = new ExtractorService();
		
		System.out.println("Lendo Associa��o Guias x Notas Fiscais");
		List<String> dadosList = extractorService.lerArquivo("GuiasNFS-e");
		extractorService.processaAssociacaoGuiasNotasFiscais(dadosList);
		System.out.println("--- Associa��o Guias x Notas Fiscais ---");

		
		
	}


	
}
