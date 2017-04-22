package br.com.jway.claudio.util;

import java.util.ArrayList;
import java.util.List;

import br.com.jway.claudio.dao.Lc116Dao;
import br.com.jway.claudio.dao.PessoaDao;
import br.com.jway.claudio.entidadesOrigem.CnaeServicosContribuinte;
import br.com.jway.claudio.entidadesOrigem.Lc116;
import br.com.jway.claudio.model.Pessoa;
import br.com.jway.claudio.service.ExtractorService;

public class Conferelc116 {
	private Util util = new Util();
	
	public static void main(String args[]){
		new Conferelc116().processa();
	}

	public void processa(){
		ExtractorService service = new ExtractorService();
		List<String> dadosList = service.lerArquivosClaudio("cnae_servicos_contribuintes");
		Lc116Dao dao = new Lc116Dao();
		ArrayList<String> verificados = new ArrayList<>();
		for (String linha : dadosList){
			List<String> arrayAux = util.splitRegistro(linha);
			PessoaDao pessoaDao = new PessoaDao();
			try {

				CnaeServicosContribuinte cnae = new CnaeServicosContribuinte(arrayAux.get(0), arrayAux.get(1),
						arrayAux.get(2), arrayAux.get(3), arrayAux.get(4), arrayAux.get(5), arrayAux.get(6),
						arrayAux.get(7), arrayAux.get(8), arrayAux.get(9), arrayAux.get(10), arrayAux.get(11),
						arrayAux.get(12));
				if (cnae.getServicoCodigo().equals("7.17")) {
					cnae.setServicoCodigo("7.19");
				}
				if (cnae != null && cnae.getServicoCodigo() != null) {
					cnae.setServicoCodigo(cnae.getServicoCodigo().replace("a", "").replace("b", ""));
				}
				if (cnae.getServico().endsWith(".")){
					cnae.setServico(cnae.getServico().substring(0,cnae.getServico().length()-1));
				}
				
				if (!verificados.contains(cnae.getServico())){
					//verificados.add(cnae.getServico());
					Lc116 lc = dao.findByCodigo(cnae.getServicoCodigo());
					if (lc == null){
						System.out.println("Código não encontrado "+cnae.getServicoCodigo());
						continue;
					}
					
					if (!lc.getDescricao().equals(cnae.getServico())){
						if (!lc.getDescricao().substring(0,lc.getDescricao().indexOf(" ")).equals(cnae.getServico().substring(0,cnae.getServico().indexOf(" ")))){
							//System.out.println("Código Incorreto: "+cnae.getServicoCodigo()+" ");
							//System.out.println(cnae.getServico());
							//System.out.println(lc.getDescricao());
							Pessoa p = pessoaDao.findByPessoaId(cnae.getIdContribuinte());
							if (p!=null){
								System.out.println(p.getCnpjCpf());
								System.out.println();
							}
							
						}
						
						
					}
					else{
						//System.out.println("Código Ok: "+cnae.getServicoCodigo());
					}
					
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
}
