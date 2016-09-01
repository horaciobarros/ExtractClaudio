package br.com.jway.claudio.service;

import java.util.List;

import br.com.jway.claudio.dao.EscrituracoesOrigemDao;
import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.util.FileLog;
import br.com.jway.claudio.util.Util;

public class EscrituracoesThread implements Runnable{
	
	private EscrituracoesOrigemDao escrituracoesDao = new EscrituracoesOrigemDao();
	private Util util;
	private String linha;
	private FileLog log;
	
	public EscrituracoesThread(Util util, String linha, FileLog log) {
		this.util = util;
		this.linha = linha;
		this.log = log;
	}

	@Override
	public void run() {
		try {
			List<String> arrayAux = util.splitRegistro(linha);
	
			EscrituracoesOrigem escrituracoes = new EscrituracoesOrigem(Long.parseLong(arrayAux.get(0)), arrayAux.get(1),
					arrayAux.get(2), arrayAux.get(3), arrayAux.get(4), arrayAux.get(5), arrayAux.get(6),
					arrayAux.get(7), arrayAux.get(8), arrayAux.get(9), arrayAux.get(10), arrayAux.get(11),
					arrayAux.get(12), arrayAux.get(13), arrayAux.get(14), arrayAux.get(15), arrayAux.get(16),
					arrayAux.get(17), arrayAux.get(18), arrayAux.get(19), arrayAux.get(20));	
			
			escrituracoes.setAutorCpf(util.getCpfCnpj(escrituracoes.getAutorCpf()));
			escrituracoes.setCpfCnpjContribuinte(util.getCpfCnpj(escrituracoes.getCpfCnpjContribuinte()));
			escrituracoesDao.save(escrituracoes);
			escrituracoesDao=null;
			escrituracoes = null;
			arrayAux = null;
		} catch (Exception e) {
			log.fillError(linha,"Escrituracoes", e);
			e.printStackTrace();
		}
	}

}
