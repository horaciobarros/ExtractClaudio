package br.com.jway.claudio.service;

import java.util.List;

import br.com.jway.claudio.dao.GuiasDao;
import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.PagamentosDao;
import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.model.GuiasNotasFiscais;

public class ExcluirGuiasThread implements Runnable{
	
	private Guias guias;
	GuiasNotasFiscaisDao guiasNotasFiscaisDao = new GuiasNotasFiscaisDao();
	PagamentosDao pagamentosDao = new PagamentosDao();
	GuiasDao guiasDao = new GuiasDao();
	
	public ExcluirGuiasThread(Guias guias) {
		this.guias = guias;
	}

	@Override
	public void run() {
		try {
			List<GuiasNotasFiscais> gnfValues = guiasNotasFiscaisDao.findPorNumeroGuia(guias.getNumeroGuia());
			if (gnfValues == null || gnfValues.size() == 0) {
				pagamentosDao.deleteByGuia(guias);
				guiasDao.delete(guias);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
