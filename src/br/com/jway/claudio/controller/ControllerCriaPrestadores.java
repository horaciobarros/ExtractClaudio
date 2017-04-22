package br.com.jway.claudio.controller;

import java.util.List;

import br.com.jway.claudio.dao.PrestadoresDao;
import br.com.jway.claudio.dao.TomadoresDao;
import br.com.jway.claudio.model.Tomadores;
import br.com.jway.claudio.service.ExtractorService;

public class ControllerCriaPrestadores {
	
	private ExtractorService extractorService = new ExtractorService();
	private TomadoresDao dao = new TomadoresDao();
	private PrestadoresDao presDao =  new PrestadoresDao();
	public void init(){
		List<String> dadosList;
		dadosList = extractorService.lerArquivosClaudio("tomadores");
		String nome = "Não informado.";
		for (String linha : dadosList){
			String inscricaoPrestador = linha.substring(linha.indexOf("inscricao_prestador")+22);
			inscricaoPrestador = inscricaoPrestador.substring(0,inscricaoPrestador.indexOf("\""));
			String inscricaoTomador = linha.substring(linha.indexOf("inscricao_tomador")+20);
			inscricaoTomador = inscricaoTomador.substring(0,inscricaoTomador.indexOf("\""));
			Tomadores t = new Tomadores();
			t.setNome(nome);
			t.setNomeFantasia(nome);
			t.setOptanteSimples("N");
			t.setTipoPessoa("O");
			t.setInscricaoTomador(inscricaoTomador);
			t.setPrestadores(presDao.findByInscricao(inscricaoPrestador));
			dao.save(t);
		}
	}
	
	public static void main(String[] args) {
		new ControllerCriaPrestadores().init();
	}
	

}
