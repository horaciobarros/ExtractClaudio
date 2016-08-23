package br.com.jway.claudio.service;

import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisSubstDao;
import br.com.jway.claudio.model.NotasFiscais;
import br.com.jway.claudio.model.NotasFiscaisSubst;
import br.com.jway.claudio.util.FileLog;

public class NotasSubstituidasThread implements Runnable {
	
	private NotasFiscais nfSubstituida;
	private NotasFiscaisDao notasFiscaisDao = new NotasFiscaisDao();
	private NotasFiscaisSubst nfsub;
	private NotasFiscaisSubstDao notasFiscaisSubstDao = new NotasFiscaisSubstDao();
	private NotasFiscais nf;
	private FileLog log;
	
	public NotasSubstituidasThread(NotasFiscais nf, FileLog log) {
		this.nf = nf;
		this.log = log;
	}

	@Override
	public void run() {
		// buscando a nota que foi substituida
		nfSubstituida = notasFiscaisDao.findByIdOrigem(Long.parseLong(nf.getIdNotaFiscalSubstituida()));
		if (nfSubstituida != null) {
			try {
				nfsub = new NotasFiscaisSubst();
				nfsub.setDatahorasubstituicao(nfSubstituida.getDataHoraEmissao());
				nfsub.setInscricaoPrestador(nfSubstituida.getInscricaoPrestador());
				nfsub.setNumeroNota(Long.valueOf(nfSubstituida.getNumeroNota()));
				nfsub.setNumeroNotaSubstituta(nf.getNumeroNota());
				nfsub.setMotivo("Dados incorretos");
				nfsub.setNotasFiscais(nf);
				notasFiscaisSubstDao.save(nfsub);
				nfsub = null;
			} catch (Exception e) {
				e.printStackTrace();
				log.fillError(nfSubstituida.getNumeroNota().toString() + nfSubstituida.getNomePrestador(), "Nota Fiscal Substituida", e);
			}
			nfSubstituida = null;
		} else {
			log.fillError("Nota Fiscal substituida não encontrada:", nf.getIdNotaFiscalSubstituida());
		}

	}

}
