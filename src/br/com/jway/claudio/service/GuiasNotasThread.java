package br.com.jway.claudio.service;

import br.com.jway.claudio.dao.EscrituracoesOrigemDao;
import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.model.GuiasNotasFiscais;
import br.com.jway.claudio.model.NotasFiscais;
import br.com.jway.claudio.util.FileLog;

public class GuiasNotasThread implements Runnable{

	private NotasFiscaisDao notasFiscaisDao = new NotasFiscaisDao();
	private GuiasNotasFiscaisDao guiasNotasFiscaisDao = new GuiasNotasFiscaisDao();
	private EscrituracoesOrigemDao escrituracoesDao = new EscrituracoesOrigemDao();
	private Guias guia;
	private FileLog log;
	
	public GuiasNotasThread(Guias guia, FileLog log) {
		this.guia = guia;
		this.log = log;
	}

	@Override
	public void run() {
		try {
			if (guia.getIdNotasFiscais() != null && !guia.getIdNotasFiscais().isEmpty()) {
				String ids = guia.getIdNotasFiscais().replaceAll("\"", "");
				String[] lista = ids.split(";");
				for (int i = 0; i < lista.length; i++) {
					if (lista[i] == null || lista[i].trim().isEmpty()){
						continue;
					}
					else{
						EscrituracoesOrigem esc = escrituracoesDao.findById(lista[i]);
						//NotasFiscais nf = notasFiscaisDao.findByIdOrigem(Long.parseLong(lista[i]));
						if (esc != null) {
							GuiasNotasFiscais gnf = new GuiasNotasFiscais();
							gnf.setGuias(guia);
							gnf.setInscricaoPrestador(guia.getInscricaoPrestador()); //
							gnf.setNumeroGuia(guia.getNumeroGuia());
							gnf.setNumeroNota(Long.parseLong(esc.getNumeroNotaFiscal()));
							gnf.setNumeroGuiaOrigem(guia.getNumeroGuiaOrigem());
							guiasNotasFiscaisDao.save(gnf);
						} else {
							log.fillError(guia.toString(), "Escritura��o n�o encontrada para rela��o com Guias. ID origem nota: " + lista[i]);
						}
					}
				}
			}
		} catch (Exception e) {
			log.fillError(guia.toString(), "guias notas fiscais", e);
		}
		
	}

}
