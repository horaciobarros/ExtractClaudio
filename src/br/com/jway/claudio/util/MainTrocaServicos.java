package br.com.jway.claudio.util;

import java.util.List;

import br.com.jway.claudio.dao.Dao;
import br.com.jway.claudio.dao.NotasFiscaisServicosDao;
import br.com.jway.claudio.dao.PrestadoresAtividadesDao;
import br.com.jway.claudio.entidadesOrigem.DeParaServicos;
import br.com.jway.claudio.model.NotasFiscaisServicos;
import br.com.jway.claudio.model.PrestadoresAtividades;
import br.com.jway.claudio.service.ExtractorService;

public class MainTrocaServicos {
	
	private NotasFiscaisServicosDao nfsDao = new NotasFiscaisServicosDao();
	private PrestadoresAtividadesDao paDao = new PrestadoresAtividadesDao();
	private Util util = new Util();
	
	public void processa(){
		ExtractorService service = new ExtractorService();
		List<String> dadosList = service.lerArquivosClaudio("DE_PARA_ATV_Claudio");
		dadosList.remove(0);//retirar cabeçalho
		String excecao = "10553197000140;1304;1305";
		dadosList.add(excecao);
		excecao = "13020869000140;1304;1305";
		dadosList.add(excecao);
		for (String linha : dadosList){
			String[] split = linha.split(";");
			DeParaServicos dados = new DeParaServicos(split[0],util.completarZerosEsquerda(split[1], 4), util.completarZerosEsquerda(split[2], 4));
			processaNotas(dados);
			processaAtividades(dados);
		}
		ajustaDescricaoCnae();
	}

	private void ajustaDescricaoCnae() {
		new Dao().ajustaDescricaoCnaeNotas();		
	}

	private void processaAtividades(DeParaServicos dados) {
		List<PrestadoresAtividades> listaAtividades = paDao.findByPrestadorCodigo(dados.getCnpj(), util.completarZerosEsquerda(dados.getCodigoAtual(), 4));
		for (PrestadoresAtividades pa : listaAtividades){
			if (pa.getInscricaoPrestador().equals("10553197000140")){
				if (pa.getIcnaes().equals("5819100")){
					pa.setIlistaservicos(util.completarZerosEsquerda(dados.getCodigoNovo(), 4));
					paDao.update(pa);
				}
			}
			else if (pa.getInscricaoPrestador().equals("13020869000140")){
				if (pa.getIcnaes().equals("7420001")||pa.getIcnaes().equals("7420002")||pa.getIcnaes().equals("7420004")){
					pa.setIlistaservicos(util.completarZerosEsquerda(dados.getCodigoNovo(), 4));
					paDao.update(pa);
				}
			}
			else{
				pa.setIlistaservicos(util.completarZerosEsquerda(dados.getCodigoNovo(), 4));
				paDao.update(pa);
			}			
		}
	}

	private void processaNotas(DeParaServicos dados) {
		List<NotasFiscaisServicos> listaNfs = nfsDao.findByPrestadorCodigo(dados.getCnpj(),util.completarZerosEsquerda(dados.getCodigoAtual(), 4));
		for (NotasFiscaisServicos nfs : listaNfs){
			if (nfs.getInscricaoPrestador().equals("10553197000140")){
				if (nfs.getIcnaes().equals("5819100")){
					nfs.setItemListaServico(util.completarZerosEsquerda(dados.getCodigoNovo(), 4));
					nfsDao.update(nfs);
				}
			}
			else if (nfs.getInscricaoPrestador().equals("13020869000140")){
				if (nfs.getIcnaes().equals("7420001")||nfs.getIcnaes().equals("7420002")||nfs.getIcnaes().equals("7420004")){
					nfs.setItemListaServico(util.completarZerosEsquerda(dados.getCodigoNovo(), 4));
					nfsDao.update(nfs);
				}
			}
			else{
				nfs.setItemListaServico(util.completarZerosEsquerda(dados.getCodigoNovo(), 4));
				nfsDao.update(nfs);
			}			
		}
	}
	
	public static void main(String args[]){
		MainTrocaServicos main = new MainTrocaServicos();
		main.processa();
	}
}
