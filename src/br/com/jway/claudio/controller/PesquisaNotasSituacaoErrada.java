package br.com.jway.claudio.controller;

import java.util.List;

import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.model.GuiasNotasFiscais;
import br.com.jway.claudio.model.NotasFiscais;

public class PesquisaNotasSituacaoErrada {
	
	private NotasFiscaisDao dao = new NotasFiscaisDao();
	private GuiasNotasFiscaisDao gnfDao = new GuiasNotasFiscaisDao();
	
	public void processa(){
		List<NotasFiscais> lista = dao.findGuiasEmitidas();
		for (NotasFiscais n : lista){
			List<GuiasNotasFiscais> gnf = gnfDao.findPorPrestadorNumero(n.getInscricaoPrestador(), n.getNumeroNota()+"");
			if (gnf == null || gnf.size()==0){
				System.out.println(n.getId());
			}
		}
		
		
	}
	
	public static void main(String args[]){
		new PesquisaNotasSituacaoErrada().processa();
	}

}
