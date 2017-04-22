package br.com.jway.claudio.controller;

import java.util.List;

import br.com.jway.claudio.dao.GuiasDao;
import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.GuiasPagtoDao;
import br.com.jway.claudio.dao.PagamentosDao;
import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.model.GuiasNotasFiscais;
import br.com.jway.claudio.model.GuiasPagto;
import br.com.jway.claudio.model.Pagamentos;

/**
 * @author Fernando Werneck - 20/09/2016
 * Analista Desenvolvedor
 * fernandowtb@hotmail.com
 * www.jwaysistemas.com.br
 * (31) 98594-8242
 */
public class AjusteGuias {
	
	private GuiasNotasFiscaisDao guiasNotasFiscaisDao = new GuiasNotasFiscaisDao();
	private GuiasDao guiasDao = new GuiasDao();
	private List<GuiasNotasFiscais> listaGnf;
	private GuiasPagtoDao guiasPagtoDao = new GuiasPagtoDao();
	private PagamentosDao pagamentosDao = new PagamentosDao();
	
	public static void main(String args[]){
		new AjusteGuias().alteraNummeracaoDasGuiasPagas();
		//new AjusteGuias().excluiGuiasQueNaoDevemSerAlteradas();
	}
	
	/**
	 * Método para excluir as guias que não vão ter o número alterado, pra evitar qualquer problema
	 * Fazer o delete após este procedimento 
	 */
	public void excluiGuiasQueNaoDevemSerAlteradas(){
		listaGnf = guiasNotasFiscaisDao.findPorGuiasNaoPagas();
		for (GuiasNotasFiscais g : listaGnf){
			guiasNotasFiscaisDao.delete(g);
		}
		guiasDao.deleteGuiasNaoPagas();
	}

	/**
	 * Alteração do número das guias, fazer o load após este procedimento
	 */
	public void alteraNummeracaoDasGuiasPagas(){
		List<Guias> listaGuias = guiasDao.findPagas();
		int contador = 1;
		for (Guias g : listaGuias){
			Pagamentos pagamento = pagamentosDao.findPorIdGuia(g.getId());
			List<GuiasNotasFiscais> guiasNotasFiscais = guiasNotasFiscaisDao.findPorNumeroGuia(g.getNumeroGuia());
			GuiasPagto numeracaoNova = guiasPagtoDao.findById(Integer.parseInt(g.getIdGuiaRecolhimento()));
			if (numeracaoNova==null){
				System.out.println("Guia "+g.getIdGuiaRecolhimento()+" não encontrada");
				continue;
			}
			g.setNumeroGuiaOrigem(""+g.getNumeroGuia()); // Gravando numeração antiga
			g.setNumeroGuia(Long.parseLong(numeracaoNova.getNumeroGuia())); // Trocando numero da guia
			pagamento.setNumeroGuia(g.getNumeroGuia());// Trocando numero da guia em pagamentos
			pagamento.setNumeroPagamento(Long.parseLong(numeracaoNova.getNumeroPagamento())); // Trocando numero do pagamento
			for (GuiasNotasFiscais gnf : guiasNotasFiscais){
				gnf.setNumeroGuia(g.getNumeroGuia());
				guiasNotasFiscaisDao.update(gnf);
			}
			guiasDao.update(g);
			pagamentosDao.update(pagamento);
			System.out.println("Registro "+contador+" processado. Id: "+g.getId());
			contador++;
		}
	}
}
