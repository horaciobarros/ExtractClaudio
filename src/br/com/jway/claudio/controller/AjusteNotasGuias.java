package br.com.jway.claudio.controller;

import java.util.List;

import br.com.jway.claudio.dao.GuiasDao;
import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.model.GuiasNotasFiscais;
import br.com.jway.claudio.model.NotasFiscais;

public class AjusteNotasGuias {
	
	private GuiasNotasFiscaisDao guiasNotasFiscaisDao = new GuiasNotasFiscaisDao();
	private List<GuiasNotasFiscais> listaGnf;
	private NotasFiscaisDao notasDao = new NotasFiscaisDao();
	
	public void init(){
		listaGnf = guiasNotasFiscaisDao.findAll();
		for (GuiasNotasFiscais g : listaGnf){
			NotasFiscais n = notasDao.findByNumeroDocumentoInscricaoPrestador(""+g.getNumeroNota(), g.getInscricaoPrestador());
			if (n!=null && n.getId()>0){
				n.setSituacao("E");
				notasDao.update(n);
				System.out.println(n.getId());
			}
		}
	}
	
	public static void main(String[] args) {
		new AjusteNotasGuias().init();
	}

}
