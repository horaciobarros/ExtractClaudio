package br.com.jway.claudio.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.jway.claudio.dao.EscrituracoesOrigemDao;
import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.dao.PessoaDao;
import br.com.jway.claudio.dao.PrestadoresDao;
import br.com.jway.claudio.dao.TomadoresDao;
import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.entidadesOrigem.NotasFiscaisOrigem;
import br.com.jway.claudio.entidadesOrigem.ServicosNotasFiscaisOrigem;
import br.com.jway.claudio.model.NotasFiscais;
import br.com.jway.claudio.model.Pessoa;
import br.com.jway.claudio.model.Prestadores;
import br.com.jway.claudio.model.Tomadores;
import br.com.jway.claudio.service.ExtractorService;

public class MainPisClaudio {

	private Map<String, Prestadores> mapPrestadores;
	private Map<String, Pessoa> mapPessoa;
	NotasFiscais nf;
	Prestadores pr;
	Pessoa pessoa;
	EscrituracoesOrigem escrituracoes;
	String inscricaoTomador;
	NotasFiscaisOrigem nfOrigem;
	Tomadores t;
	EscrituracoesOrigem escrituracaoSubstituida;
	private Util util = new Util();
	private NotasFiscaisDao notasFiscaisDao = new NotasFiscaisDao();
	private PrestadoresDao prestadoresDao = new PrestadoresDao();
	private PessoaDao pessoaDao = new PessoaDao();

	public static void main(String args[]) {

		MainPisClaudio m = new MainPisClaudio();

		m.ajustaPis();

	}

	private void ajustaPis() {

		ExtractorService extractorService = new ExtractorService();

		System.out.println("Lendo Notas Fiscais  " + Util.getDataHoraAtual());
		List<String> dadosList = extractorService.lerArquivosClaudio("notas_fiscais");

		System.out.println("Corrigindo valores do pis em Notas Fiscais " + Util.getDataHoraAtual());
		try {
			corrigePis(dadosList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--- Fim de Notas Fiscais ---");

	}

	private void corrigePis(List<String> dadosList) {
		
		int contador = 0;
		int limite = 0;
		int ajustados = 0;
		ExecutorService executor = Executors.newFixedThreadPool(100);
		for (String linha : dadosList){
			ThreadAjustesClaudio thread = new ThreadAjustesClaudio(linha, util, nfOrigem, contador, limite, nf, pr);
			executor.execute(thread);
		}	
		executor.shutdown();
		while (!executor.isTerminated()){
		}

	}
}
