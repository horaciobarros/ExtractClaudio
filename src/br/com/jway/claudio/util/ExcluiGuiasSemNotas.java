package br.com.jway.claudio.util;

import br.com.jway.claudio.service.ExtractorService;

public class ExcluiGuiasSemNotas {


	private ExtractorService extractorService = new ExtractorService();
	
	public static void main(String args[]) {
		ExcluiGuiasSemNotas exclui = new ExcluiGuiasSemNotas();
		exclui.extractorService.excluiGuiasSemNotas();
	}

	
}
