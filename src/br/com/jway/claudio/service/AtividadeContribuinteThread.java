package br.com.jway.claudio.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.jway.claudio.dao.PessoaDao;
import br.com.jway.claudio.dao.PrestadoresAtividadesDao;
import br.com.jway.claudio.dao.PrestadoresDao;
import br.com.jway.claudio.dao.ServicosDao;
import br.com.jway.claudio.dao.ServicosOrigemDao;
import br.com.jway.claudio.entidadesOrigem.CnaeServicosContribuinte;
import br.com.jway.claudio.entidadesOrigem.Servicos;
import br.com.jway.claudio.entidadesOrigem.ServicosOrigem;
import br.com.jway.claudio.model.Pessoa;
import br.com.jway.claudio.model.Prestadores;
import br.com.jway.claudio.model.PrestadoresAtividades;
import br.com.jway.claudio.util.FileLog;
import br.com.jway.claudio.util.Util;

public class AtividadeContribuinteThread implements Runnable{
	private Util util;
	private String linha;
	private FileLog log;
	private PessoaDao pessoaDao = new PessoaDao();
	private ServicosOrigemDao servicosOrigemDao = new ServicosOrigemDao();
	private ServicosDao servicosDao = new ServicosDao();
	private PrestadoresDao prestadoresDao = new PrestadoresDao();
	private PrestadoresAtividadesDao prestadoresAtividadesDao = new PrestadoresAtividadesDao();
	
	public AtividadeContribuinteThread(Util util, String linha, FileLog log) {
		this.util = util;
		this.linha = linha;
		this.log = log;
	}

	@Override
	public void run() {
		List<String> arrayAux = util.splitRegistro(linha);

		try {

			CnaeServicosContribuinte cnae = new CnaeServicosContribuinte(arrayAux.get(0), arrayAux.get(1),
					arrayAux.get(2), arrayAux.get(3), arrayAux.get(4), arrayAux.get(5), arrayAux.get(6),
					arrayAux.get(7), arrayAux.get(8), arrayAux.get(9), arrayAux.get(10), arrayAux.get(11),
					arrayAux.get(12));
			if (cnae.getServicoCodigo().equals("7.17")) {
				cnae.setServicoCodigo("7.19");
			}
			if (cnae != null && cnae.getServicoCodigo() != null) {
				cnae.setServicoCodigo(cnae.getServicoCodigo().replace("a", "").replace("b", "").replace(".", ""));
			}
			Pessoa pessoa = pessoaDao.findByPessoaId(cnae.getIdContribuinte());
			if (pessoa == null) {
				try {
				throw new Exception("Id de Pessoa n�o encontrada:" + cnae.getIdContribuinte());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Prestadores pr = prestadoresDao.findByInscricao(pessoa.getCnpjCpf());
			String codigoServico = cnae.getServicoCodigo().replace(".", "").replace("a", "").replace("b", "").replace("/", "").replace("-", "");
			codigoServico = util.completarZerosEsquerda(codigoServico, 4);
			ServicosOrigem servico = servicosOrigemDao.findByCodigoServicoCodigoCnae(codigoServico, cnae.getCnaeCodigo().replace(".", "").replace("a", "").replace("b", "").replace("/", "").replace("-", ""));
			if (servico==null){
				servico = servicosOrigemDao.findByCodigo(codigoServico);
			}
			try {
				PrestadoresAtividades pa = new PrestadoresAtividades();
				pa.setAliquota(BigDecimal.valueOf(util.corrigeDouble(servico.getAliquota())));
				if (pa.getAliquota().compareTo(BigDecimal.ZERO) == 0) {
					Servicos s = servicosDao.findByCodigoServicoCodigoCnae(codigoServico, cnae.getCnaeCodigo());
					if (s == null){
						s = servicosDao.findByCodigo(codigoServico);
					}
					pa.setAliquota(BigDecimal.valueOf(util.corrigeDouble(s.getAliquota())));
				}
				if (pa.getAliquota().compareTo(BigDecimal.ZERO) == 0) {
					pa.setAliquota(BigDecimal.valueOf(3));
				}
				pa.setCodigoAtividade(cnae.getCnaeCodigo().replace("-", "").replace("/", "").substring(0, 5));
				pa.setIcnaes(cnae.getCnaeCodigo().replace("-", "").replace("/", ""));
				pa.setIlistaservicos(util.completarZerosEsquerda(
						servico.getCodigo().replace(".", "").replace("a", "").replace("b", ""), 4));
				pa.setInscricaoPrestador(pr.getInscricaoPrestador());
				pa.setPrestadores(pr);
				prestadoresAtividadesDao.save(pa);
			} catch (Exception e) {
				System.out.println(codigoServico+ " - "+cnae.getCnaeCodigo());
				log.fillError(linha, "Prestadores atividades ", e);
				e.printStackTrace();
			}

		} catch (Exception e) {
			log.fillError(linha, "Prestadores atividades ", e);
			e.printStackTrace();
		}		
	}

}