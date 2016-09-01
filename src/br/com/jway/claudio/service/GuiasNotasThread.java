package br.com.jway.claudio.service;

import br.com.jway.claudio.dao.EscrituracoesOrigemDao;
import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.model.GuiasNotasFiscais;
import br.com.jway.claudio.model.NotasFiscais;
import br.com.jway.claudio.util.FileLog;
import br.com.jway.claudio.util.Util;

public class GuiasNotasThread implements Runnable {

	private GuiasNotasFiscaisDao guiasNotasFiscaisDao = new GuiasNotasFiscaisDao();
	private EscrituracoesOrigemDao escrituracoesDao = new EscrituracoesOrigemDao();
	private Guias guia;
	private FileLog log;
	private NotasFiscaisDao notasFiscaisDao = new NotasFiscaisDao();

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
					if (lista[i] == null || lista[i].trim().isEmpty()) {
						continue;
					} else {
						try{
							String idEscrituracao = lista[i].trim();
							EscrituracoesOrigem esc = escrituracoesDao.findById(Long.parseLong(idEscrituracao));
							// NotasFiscais nf =
							// notasFiscaisDao.findByIdOrigem(Long.parseLong(lista[i]));
							if (esc != null && !new Util().isEmptyOrNull(esc.getIdNotaFiscal())) {
								GuiasNotasFiscais gnf = new GuiasNotasFiscais();
								gnf.setGuias(guia);
								gnf.setInscricaoPrestador(guia.getInscricaoPrestador()); //
								gnf.setNumeroGuia(guia.getNumeroGuia());
								gnf.setNumeroNota(Long.parseLong(esc.getNumeroNotaFiscal()));
								gnf.setNumeroGuiaOrigem(guia.getNumeroGuiaOrigem());
								guiasNotasFiscaisDao.save(gnf);
							} else {
								log.fillError(guia.toString(), "Escrituração não encontrada para relação com Guias. ID origem nota: " + lista[i]);
							}
						}
						catch(Exception e){
							log.fillError(guia.toString(), "guias notas fiscais", e);
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			log.fillError(guia.toString(), "guias notas fiscais", e);
			e.printStackTrace();
		}

	}

}
