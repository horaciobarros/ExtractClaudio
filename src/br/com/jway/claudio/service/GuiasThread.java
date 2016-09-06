package br.com.jway.claudio.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.jway.claudio.dao.CompetenciasDao;
import br.com.jway.claudio.dao.GuiasDao;
import br.com.jway.claudio.dao.PagamentosDao;
import br.com.jway.claudio.dao.PessoaDao;
import br.com.jway.claudio.dao.PrestadoresDao;
import br.com.jway.claudio.entidadesOrigem.GuiaOrigem;
import br.com.jway.claudio.model.Competencias;
import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.model.Pagamentos;
import br.com.jway.claudio.model.Pessoa;
import br.com.jway.claudio.model.Prestadores;
import br.com.jway.claudio.util.FileLog;
import br.com.jway.claudio.util.Util;

public class GuiasThread implements Runnable{
	private Util util;
	private String linha;
	private FileLog log;
	private PessoaDao pessoaDao = new PessoaDao();
	private CompetenciasDao competenciasDao = new CompetenciasDao();
	private PrestadoresDao prestadoresDao = new PrestadoresDao();
	private GuiasDao guiasDao = new GuiasDao();
	private PagamentosDao pagamentosDao = new PagamentosDao();
	
	public GuiasThread(Util util, String linha, FileLog log) {
		this.util = util;
		this.linha = linha;
		this.log = log;
	}

	@Override
	public void run() {
		List<String> arrayAux = util.splitRegistro(linha);

		GuiaOrigem guiaOrigem = new GuiaOrigem(arrayAux.get(0), arrayAux.get(1), arrayAux.get(2), arrayAux.get(3),
				arrayAux.get(4), arrayAux.get(5), arrayAux.get(6), arrayAux.get(7), arrayAux.get(8),
				arrayAux.get(9), arrayAux.get(10), arrayAux.get(11), arrayAux.get(12), arrayAux.get(13),
				arrayAux.get(14), arrayAux.get(15), arrayAux.get(16), arrayAux.get(17));

		if (guiaOrigem.getNotaFiscalAvulsa().equalsIgnoreCase("t")) {
			log.fillError(linha, "Guias avulsa não gravada de acordo com definição da cmm");
			return;
		}

		String descricao = util.getNomeMes(guiaOrigem.getCompetencia().substring(5, 7)) + "/"
				+ guiaOrigem.getCompetencia().substring(0, 4);
		Competencias cp = competenciasDao.findByDescricao(descricao);

		try {
			if (cp == null || cp.getId() == 0) { // acertar datas
				cp = new Competencias();
				cp.setDescricao(descricao.trim());
				cp.setDataInicio(util.getFirstDayOfMonth(guiaOrigem.getCompetencia().substring(0, 4),
						guiaOrigem.getCompetencia().substring(5, 7)));
				cp.setDataFim(util.getLastDayOfMonth(guiaOrigem.getCompetencia().substring(0, 4),
						guiaOrigem.getCompetencia().substring(5, 7)));
				cp.setDataVencimento(util.getDecimoDiaMesPosterior(cp.getDataFim()));

				competenciasDao.save(cp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Guias guias = new Guias();
			guias.setCompetencias(cp);
			guias.setDataVencimento(util.getStringToDate(guiaOrigem.getDataDeVencimento(), "yyyy-MM-dd"));
			Pessoa p = pessoaDao.findByPessoaId(guiaOrigem.getIdContribuinte());
			guias.setInscricaoPrestador(p.getCnpjCpf());

			guias.setIntegrarGuia("N"); // TODO sanar dï¿½vida

			String numeroGuia = guiaOrigem.getId(); // TODO CONFIRMAR COM O SANDRO(NÃO HÁ N. GUIA NO TXT)
			guias.setNumeroGuiaOrigem(numeroGuia);
			int proximoNumeroGuia = 60000000 + Integer.parseInt(numeroGuia);
			guias.setNumeroGuia(Long.valueOf(proximoNumeroGuia));

			Prestadores prestadores = prestadoresDao.findByInscricao(guias.getInscricaoPrestador());
			guias.setPrestadores(prestadores);
			String situacao = "A";
			if (guiaOrigem.getStatus() != null && guiaOrigem.getStatus().equals("paid")){
				situacao = "P";
			} else if (guiaOrigem.getStatus()!=null && guiaOrigem.getStatus().equals("cancelled")){
				situacao = "C";
			}

			guias.setSituacao(situacao);
			
			guias.setTipo("P");

			guias.setValorDesconto(BigDecimal.valueOf(0.00));
			guias.setValorImposto(BigDecimal.valueOf(util.corrigeDouble(guiaOrigem.getValor())));
			guias.setValorGuia(BigDecimal.valueOf(util.corrigeDouble(guiaOrigem.getValorTotal())));
			guias.setIdGuiaRecolhimento(guiaOrigem.getId());
			guias.setIdNotasFiscais(guiaOrigem.getIdNotasFiscais().replaceAll("\"", ""));
			guias.setValorTaxaExpediente(BigDecimal.valueOf(util.corrigeDouble(guiaOrigem.getTaxaDeExpediente())));
			guiasDao.save(guias);

			// pagamentos
			if (guias.getSituacao().equals("P")) {
				try {
					Pagamentos pg = new Pagamentos();
					pg.setDataPagamento(util.getStringToDate(guiaOrigem.getDataDePagamento(), "yyyy-MM-dd"));
					pg.setGuias(guias);
					pg.setNumeroGuia(guias.getNumeroGuia());
					pg.setNumeroPagamento(guias.getNumeroGuia());
					pg.setTipoPagamento("N");
					pg.setValorCorrecao(BigDecimal.ZERO);
					pg.setValorJuro(BigDecimal.valueOf(util.corrigeDouble(guiaOrigem.getJuros())));
					pg.setValorMulta(BigDecimal.valueOf(util.corrigeDouble(guiaOrigem.getMulta())));
					pg.setValorPago(BigDecimal.valueOf(util.corrigeDouble(guiaOrigem.getValorTotal())));
					pg.setDataPagamento(util.getStringToDate(guiaOrigem.getDataDePagamento(), "yyyy-MM-dd"));
					pagamentosDao.save(pg);
				} catch (Exception e) {
					log.fillError(linha, "Pagamentos", e);
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			log.fillError(linha, "Guias", e);
			e.printStackTrace();
		}		
	}

}
