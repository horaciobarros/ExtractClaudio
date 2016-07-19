package br.com.jway.claudio.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import br.com.jway.claudio.dao.CompetenciasDao;
import br.com.jway.claudio.dao.Dao;
import br.com.jway.claudio.dao.GuiasDao;
import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.dao.PagamentosDao;
import br.com.jway.claudio.dao.PessoaDao;
import br.com.jway.claudio.dao.PrestadoresAtividadesDao;
import br.com.jway.claudio.dao.PrestadoresDao;
import br.com.jway.claudio.dao.PrestadoresOptanteSimplesDao;
import br.com.jway.claudio.dao.TomadoresDao;
import br.com.jway.claudio.entidadesOrigem.CnaeServicosContribuinte;
import br.com.jway.claudio.entidadesOrigem.ContribuinteOrigem;
import br.com.jway.claudio.entidadesOrigem.GuiaOrigem;
import br.com.jway.claudio.entidadesOrigem.NotasFiscaisOrigem;
import br.com.jway.claudio.entidadesOrigem.ServicosOrigem;
import br.com.jway.claudio.model.Competencias;
import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.model.GuiasNotasFiscais;
import br.com.jway.claudio.model.NotasFiscais;
import br.com.jway.claudio.model.Pagamentos;
import br.com.jway.claudio.model.Pessoa;
import br.com.jway.claudio.model.Prestadores;
import br.com.jway.claudio.model.PrestadoresAtividades;
import br.com.jway.claudio.model.Tomadores;
import br.com.jway.claudio.util.FileLog;
import br.com.jway.claudio.util.Util;
import br.com.jway.claudio.dao.MunicipiosIbgeDao;

public class ExtractorService {

	private Util util = new Util();
	private CompetenciasDao competenciasDao = new CompetenciasDao();
	private PrestadoresDao prestadoresDao = new PrestadoresDao();
	private TomadoresDao tomadoresDao = new TomadoresDao();
	private Dao dao = new Dao();
	private GuiasDao guiasDao = new GuiasDao();
	private NotasFiscaisDao notasFiscaisDao = new NotasFiscaisDao();
	private PagamentosDao pagamentosDao = new PagamentosDao();
	private PrestadoresAtividadesDao prestadoresAtividadesDao = new PrestadoresAtividadesDao();
	private PrestadoresOptanteSimplesDao prestadoresOptanteSimplesDao = new PrestadoresOptanteSimplesDao();
	private Map<String, Tomadores> mapTomadores = new Hashtable<String, Tomadores>();
	private Map<String, Prestadores> mapPrestadores = new Hashtable<String, Prestadores>();
	private MunicipiosIbgeDao municipiosIbgeDao = new MunicipiosIbgeDao();
	private PessoaDao pessoaDao = new PessoaDao();
	private GuiasNotasFiscaisDao guiasNotasFiscaisDao = new GuiasNotasFiscaisDao();
	private Map<String, ServicosOrigem> mapServicos = new Hashtable<String, ServicosOrigem>();

	public List<String> excluiParaProcessarNivel1() {
		return Arrays.asList("GuiasNotasFiscais", "NotasFiscaisCanceladas", "NotasFiscaisCondPagamentos",
				"NotasFiscaisEmails", "NotasFiscaisObras", "NotasFiscaisPrestadores", "NotasFiscaisServicos",
				"NotasFiscaisSubst", "NotasFiscaisTomadores", "NotasFiscaisXml", "Pagamentos", "PrestadoresAtividades",
				"" + "PrestadoresOptanteSimples", "Guias", "Competencias", "NotasFiscais", "Tomadores", "Prestadores",
				"Pessoa");

	}

	public List<String> excluiParaProcessarNivel2() {
		return Arrays.asList("GuiasNotasFiscais", "NotasFiscaisCanceladas", "NotasFiscaisCondPagamentos",
				"NotasFiscaisEmails", "NotasFiscaisObras", "NotasFiscaisPrestadores", "NotasFiscaisServicos",
				"NotasFiscaisSubst", "NotasFiscaisTomadores", "NotasFiscaisXml", "Pagamentos", "PrestadoresAtividades",
				"PrestadoresOptanteSimples", "Guias", "Competencias");

	}

	public List<String> excluiParaProcessarNivel3() {
		return Arrays.asList("GuiasNotasFiscais", "NotasFiscaisCanceladas", "NotasFiscaisCondPagamentos",
				"NotasFiscaisEmails", "NotasFiscaisObras", "NotasFiscaisPrestadores", "NotasFiscaisServicos",
				"NotasFiscaisSubst", "NotasFiscaisTomadores", "NotasFiscaisXml", "NotasFiscais");
	}

	public List<String> excluiParaProcessarNivel4() {
		return Arrays.asList("GuiasNotasFiscais");
	}

	public void excluiDados(String nomeEntidade) {

		dao.excluiDados(nomeEntidade);
	}

	public List<String> lerArquivosClaudio(String arquivoIn) {
		BufferedReader br;
		List<String> dadosList = new ArrayList<String>();
		try {
			br = new BufferedReader(
					new InputStreamReader(new FileInputStream("c:/TEMP/claudio/" + arquivoIn + ".csv"), "utf-8"));
			br.readLine();
			while (br.ready()) {
				String linha = br.readLine();
				while (linha.contains("\"\"")) {
					linha = linha.replaceAll("\"\"", "\"");
				}
				while (linha.contains(";")) {
					linha = linha.replaceAll(";", "");
				}

				while (linha.contains(",,")) {
					linha = linha.replaceAll(",,", ", ,");
				}
				while (linha.contains(",,,")) {
					linha = linha.replaceAll(",,,", ", , ,");
				}
				while (linha.contains(";")) {
					linha = linha.replaceAll(";", "");
				}
				linha = linha.trim();
				if (!linha.isEmpty() && linha.substring(linha.length() - 1).equals(",")) {
					linha = linha + " ";
				}
				dadosList.add(linha);
			}
			br.close();
			return dadosList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void processaDadosNotasFiscais(List<String> dadosList) {

	}

	private void processaDemaisTiposNotas(Prestadores pr, NotasFiscais nf, NotasFiscaisOrigem nfo, FileLog log,
			String linha, Tomadores t) {

	}

	public List<String> lerArquivoXml(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void processaDadosContribuinte(List<String> dadosList) {
		int count = 0;

		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				break;
			}

			String[] arrayAux = linha.split(",");

			try {
				ContribuinteOrigem c = new ContribuinteOrigem(arrayAux[0], arrayAux[1], arrayAux[2], arrayAux[3],
						arrayAux[4], arrayAux[5], arrayAux[6], arrayAux[7], arrayAux[8], arrayAux[9], arrayAux[10],
						arrayAux[11], arrayAux[12], arrayAux[13], arrayAux[14], arrayAux[15], arrayAux[16],
						arrayAux[17], arrayAux[18], arrayAux[19], arrayAux[20], arrayAux[21], arrayAux[22],
						arrayAux[23], arrayAux[24], arrayAux[25]);
				count++;

				Pessoa p = new Pessoa();
				p.setBairro(c.getBairro());
				p.setCelular(c.getTelefoneCelular());
				p.setCep(util.trataCep(c.getCep().trim()));
				p.setCnpjCpf(util.getCpfCnpj(c.getCpfCnpj()));
				p.setComplemento(c.getComplemento());
				p.setEmail(c.getEmail());
				p.setEndereco(c.getLogradouro());
				p.setInscricaoEstadual(c.getInscricaoEstadual());
				p.setInscricaoMunicipal(c.getInscricaoMunicipal());
				p.setMunicipio(c.getCidade());
				if (c.getEstado() == null || c.getEstado().isEmpty()) {
					c.setEstado("MG");
				} else {
					if (c.getEstado().contains("\"")) {
						c.setEstado(c.getEstado().replace("\"", ""));
					}
				}
				try {
					p.setMunicipioIbge(Long.valueOf(municipiosIbgeDao.getCodigoIbge(c.getCidade(), c.getEstado())));
				} catch (Exception e) {
					System.out.println("N�o encontrado codigo ibge: " + c.getCidade() + "-" + c.getEstado());
					e.printStackTrace();
				}
				p.setNome(c.getNomeRazaoSocial());
				p.setNomeFantasia(c.getNomeFantasia());
				p.setNumero(c.getNumero());
				p.setPessoaId(Long.valueOf(c.getId().replace("\"", "")));
				try {
					p.setTipoPessoa(util.getTipoPessoa(c.getCpfCnpj().trim()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (p.getTipoPessoa().equals("F")) {
					p.setSexo("M");
				}
				p.setTelefone(c.getTelefone());
				p.setUf(c.getEstado());
				p = trataNumerosTelefones(p);
				p = anulaCamposVazios(p);

				p = pessoaDao.save(p);
				try {

					Prestadores pr = new Prestadores();
					pr.setAutorizado("N");
					pr.setCelular(p.getCelular());
					pr.setEmail(p.getEmail());
					pr.setInscricaoMunicipal(p.getInscricaoMunicipal());
					pr.setInscricaoPrestador(p.getCnpjCpf());
					pr.setTelefone(p.getTelefone());
					pr.setEnquadramento("N");
					pr = trataNumerosTelefones(pr);
					pr = anulaCamposVazios(pr);
					prestadoresDao.save(pr);

				} catch (Exception e) {

					System.out.println("Prestador n�o gravado: " + p.getNome());
					e.printStackTrace();
				}

			} catch (Exception e) {
				System.out.println("Pessoa n�o gravada: " + linha);
				e.printStackTrace();
			}
		}
	}

	public void processaDadosAtividadeEconomicaContribuinte(List<String> dadosList) {
		int count = 0;

		if (mapServicos == null || mapServicos.isEmpty()) {
			try {
				throw new Exception("Tabela de Servi�os n�o encontrada.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				continue;
			}

			String[] arrayAux = linha.split(",");

			try {

				CnaeServicosContribuinte cnae = new CnaeServicosContribuinte(arrayAux[0], arrayAux[1], arrayAux[2],
						arrayAux[3], arrayAux[4], arrayAux[5], arrayAux[6], arrayAux[7], arrayAux[8], arrayAux[9],
						arrayAux[10], arrayAux[11], arrayAux[12]);

				Pessoa pessoa = pessoaDao.findByPessoaId(cnae.getIdContribuinte());
				Prestadores pr = prestadoresDao.findByInscricao(pessoa.getCnpjCpf());
				ServicosOrigem servico = mapServicos.get(cnae.getIdServico());

				try {
					PrestadoresAtividades pa = new PrestadoresAtividades();
					pa.setAliquota(BigDecimal.valueOf(Double.parseDouble(cnae.getAliquota())));
					pa.setCodigoAtividade(cnae.getCnaeCodigo());
					pa.setIcnaes(cnae.getIdCnae());
					pa.setIlistaservicos(servico.getCodigo());
					pa.setInscricaoPrestador(pr.getInscricaoPrestador());
					pa.setPrestadores(pr);
					prestadoresAtividadesDao.save(pa);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}

	public void processaDadosGuiasPagas(List<String> dadosList) {
		int count = 0;

	}

	public void processaDadosGuias(List<String> dadosList) {
		int count = 0;

		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				continue;
			}

			String[] arrayAux = linha.split(",");

			GuiaOrigem guiaOrigem = new GuiaOrigem(arrayAux[0], arrayAux[1], arrayAux[2], arrayAux[3], arrayAux[4],
					arrayAux[5], arrayAux[6], arrayAux[7], arrayAux[8], arrayAux[9], arrayAux[10], arrayAux[11],
					arrayAux[12], arrayAux[13], arrayAux[14], arrayAux[15], arrayAux[16], arrayAux[17]);

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

				guias.setIntegrarGuia("N"); // TODO sanar d�vida

				String numeroGuia = guiaOrigem.getId();
				int proximoNumeroGuia = 60000000 + Integer.parseInt(numeroGuia);
				guias.setNumeroGuia(Long.valueOf(proximoNumeroGuia));

				Prestadores prestadores = prestadoresDao.findByInscricao(guias.getInscricaoPrestador());
				guias.setPrestadores(prestadores);
				String situacao = "A";
				if (guiaOrigem.getDataDePagamento() != null && !guiaOrigem.getDataDePagamento().isEmpty()) {
					situacao = "P";
				}
				guias.setSituacao(situacao);

				guias.setTipo("P");

				guias.setValorDesconto(BigDecimal.valueOf(0.00));
				guias.setValorGuia(BigDecimal.valueOf(Double.parseDouble(guiaOrigem.getValorTotal())));
				guias.setValorImposto(BigDecimal.valueOf(Double.parseDouble(guiaOrigem.getValor())));

				guias.setIdGuiaRecolhimento(guiaOrigem.getId());
				guiasDao.save(guias);

				// pagamentos
				if (guias.getSituacao().equals("P")) {
					try {
						Pagamentos pg = new Pagamentos();
						pg.setDataPagamento(util.getStringToDate(guiaOrigem.getDataDePagamento(), "yyyy-MM-dd"));
						pg.setGuias(guias);
						pg.setNumeroGuia(guias.getId());
						pg.setNumeroPagamento(guias.getId());
						pg.setTipoPagamento("N");
						pg.setValorCorrecao(BigDecimal.ZERO);
						pg.setValorJuro(BigDecimal.valueOf(Double.parseDouble(guiaOrigem.getJuros())));
						pg.setValorMulta(BigDecimal.valueOf(Double.parseDouble(guiaOrigem.getMulta())));
						pg.setValorPago(BigDecimal.valueOf(Double.parseDouble(guiaOrigem.getValorTotal())));
						pg.setDataPagamento(util.getStringToDate(guiaOrigem.getDataDePagamento(), "yyyy-MM-dd"));
						pagamentosDao.save(pg);
					} catch (Exception e) {
						System.out.println(guiaOrigem.getId());
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public Long count(String nomeEntidade) {
		return dao.count(nomeEntidade);
	}

	private Prestadores trataNumerosTelefones(Prestadores p) {

		if (p.getCelular() != null) {
			p.setCelular(p.getCelular().replaceAll("\\(", ""));
			p.setCelular(p.getCelular().replaceAll("\\)", ""));
			p.setCelular(p.getCelular().replaceAll("-", ""));
		}
		if (p.getTelefone() != null) {
			p.setTelefone(p.getTelefone().replaceAll("\\(", ""));
			p.setTelefone(p.getTelefone().replaceAll("\\)", ""));
			p.setTelefone(p.getTelefone().replaceAll("\\-", ""));
		}

		return p;
	}

	private Prestadores anulaCamposVazios(Prestadores p) {

		p.setEmail(util.trataEmail(p.getEmail()));

		if (p.getTelefone() != null && p.getTelefone().trim().isEmpty()) {
			p.setTelefone(null);
		}
		if (p.getCelular() != null && p.getCelular().trim().isEmpty()) {
			p.setCelular(null);
		}

		return p;
	}

	public Pessoa anulaCamposVazios(Pessoa pessoa) {
		pessoa.setEmail(util.trataEmail(pessoa.getEmail()));
		if (pessoa.getTelefone() != null && pessoa.getTelefone().trim().isEmpty()) {
			pessoa.setTelefone(null);
		}
		if (pessoa.getCelular() != null && pessoa.getCelular().trim().isEmpty()) {
			pessoa.setCelular(null);
		}
		if (pessoa.getInscricaoEstadual() != null && pessoa.getInscricaoEstadual().isEmpty()) {
			pessoa.setInscricaoEstadual(null);
		}
		if (pessoa.getMunicipioIbge() != null && pessoa.getMunicipioIbge().toString().trim().isEmpty()) {
			pessoa.setMunicipioIbge(null);
		}
		if (pessoa.getCep() != null && pessoa.getCep().trim().isEmpty()) {
			pessoa.setCep(null);
		}
		if (pessoa.getComplemento() != null && pessoa.getComplemento().trim().isEmpty()) {
			pessoa.setComplemento(null);
		}

		return pessoa;
	}

	public Pessoa trataNumerosTelefones(Pessoa pessoa) {

		if (pessoa.getCelular() != null) {
			pessoa.setCelular(pessoa.getCelular().replaceAll("\\(", ""));
			pessoa.setCelular(pessoa.getCelular().replaceAll("\\)", ""));
			pessoa.setCelular(pessoa.getCelular().replaceAll("-", ""));
		}
		if (pessoa.getTelefone() != null) {
			pessoa.setTelefone(pessoa.getTelefone().replaceAll("\\(", ""));
			pessoa.setTelefone(pessoa.getTelefone().replaceAll("\\)", ""));
			pessoa.setTelefone(pessoa.getTelefone().replaceAll("\\-", ""));
		}

		if (pessoa.getCelular() != null && !pessoa.getCelular().isEmpty()) {
			if (pessoa.getCelular().substring(0, 1).equals("0")) {
				pessoa.setCelular(pessoa.getCelular().substring(1));
			}
		}
		if (pessoa.getTelefone() != null && !pessoa.getTelefone().isEmpty()) {
			try {
				if (pessoa.getTelefone().substring(0, 1).equals("0")) {
					pessoa.setTelefone(pessoa.getCelular().substring(1));
				}
			} catch (Exception e) {

			}
		}

		return pessoa;
	}

	private Tomadores anulaCamposVazios(Tomadores t) {

		t.setEmail(util.trataEmail(t.getEmail()));

		if (t.getTelefone() != null && t.getTelefone().trim().isEmpty()) {
			t.setTelefone(null);
		}
		if (t.getCelular() != null && t.getCelular().trim().isEmpty()) {
			t.setCelular(null);
		}

		if (util.isEmptyOrNull(t.getInscricaoEstadual())) {
			t.setInscricaoEstadual(null);
		}
		if (util.isEmptyOrNull(t.getInscricaoMunicipal())) {
			t.setInscricaoMunicipal(null);
		}

		if (util.isEmptyOrNull(t.getCep())) {
			t.setCep(null);
		}

		return t;
	}

	private Tomadores trataNumerosTelefones(Tomadores t) {

		if (t.getCelular() != null) {
			t.setCelular(t.getCelular().replaceAll("\\(", ""));
			t.setCelular(t.getCelular().replaceAll("\\)", ""));
			t.setCelular(t.getCelular().replaceAll("-", ""));
		}
		if (t.getTelefone() != null) {
			t.setTelefone(t.getTelefone().replaceAll("\\(", ""));
			t.setTelefone(t.getTelefone().replaceAll("\\)", ""));
			t.setTelefone(t.getTelefone().replaceAll("\\-", ""));
		}

		return t;
	}

	public void processaDadosServicos(List<String> dadosList) {
		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				break;
			}

			String[] arrayAux = linha.split(",");

			ServicosOrigem servicos = new ServicosOrigem(arrayAux[0], arrayAux[1], arrayAux[2], arrayAux[3],
					arrayAux[4], arrayAux[5]);

			try {
				mapServicos.put(servicos.getId(), servicos);

			} catch (Exception e) {

			}
		}

	}

}
