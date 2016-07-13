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
import br.com.jway.claudio.entidadesOrigem.AtividadeEconomicaContribuinte;
import br.com.jway.claudio.entidadesOrigem.ContribuinteOrigem;
import br.com.jway.claudio.entidadesOrigem.DadosLivroPrestador;
import br.com.jway.claudio.entidadesOrigem.GuiaRecolhimento51;
import br.com.jway.claudio.entidadesOrigem.GuiaRecolhimento51Da;
import br.com.jway.claudio.entidadesOrigem.GuiaRecolhimento52;
import br.com.jway.claudio.entidadesOrigem.GuiaRecolhimento53;
import br.com.jway.claudio.entidadesOrigem.GuiaRecolhimento54;
import br.com.jway.claudio.entidadesOrigem.GuiasNFSe;
import br.com.jway.claudio.entidadesOrigem.NotasFiscaisOrigem;
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

	public List<String> lerArquivo(String arquivoIn) {
		BufferedReader br;
		List<String> dadosList = new ArrayList<String>();
		try {
			br = new BufferedReader(
					new InputStreamReader(new FileInputStream("c:/TEMP/claudio/" + arquivoIn + ".txt"), "cp1252"));

			while (br.ready()) {
				String linha = br.readLine();
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
		// -- servi√ßos
		NotasThreadService nfServico = new NotasThreadService(pr, nf, nfo, log, linha, "S");
		Thread s = new Thread(nfServico);
		s.start();

		// -- canceladas
		if (dlp.getStatusNota().substring(0, 1).equals("C")) {
			NotasThreadService nfCanceladas = new NotasThreadService(pr, nf, dlp, log, linha, "C");
			Thread c = new Thread(nfCanceladas);
			c.start();
		}

		// email
		if (dlp.getEmailPrestador() != null && !dlp.getEmailPrestador().isEmpty()) {
			NotasThreadService nfEmail = new NotasThreadService(pr, nf, dlp, log, linha, "E");
			Thread e = new Thread(nfEmail);
			e.start();
		}

		// notas-fiscais-cond-pagamentos ??

		// notas-fiscais-obras ??

		// notas-fiscais-prestadores
		NotasThreadService nfPrestadores = new NotasThreadService(pr, nf, dlp, log, linha, "P");
		Thread prestadoresThread = new Thread(nfPrestadores);
		prestadoresThread.start();

		// guias x notas fiscais
		Guias g = new Guias();
		if (dlp.getNossoNumero() != null && !dlp.getNossoNumero().trim().isEmpty()) {
			g = guiasDao.findByNumeroGuia(dlp.getNossoNumero());
			if (g != null) {
				NotasThreadService nfGuias = new NotasThreadService(pr, nf, dlp, log, linha, "G", g);
				Thread gThread = new Thread(nfGuias);
				gThread.start();

			} else {
				System.out.println("Numero de guia n√£o encontrado: " + dlp.getNossoNumero());

			}
		}

		// notas fiscais tomadores

		if (t != null && t.getId() != null) {
			NotasThreadService nfTomadores = new NotasThreadService(pr, nf, dlp, log, linha, "T", g, t);
			Thread nftThread = new Thread(nfTomadores);
			nftThread.start();
		}

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

			ContribuinteOrigem c = new ContribuinteOrigem(linha.substring(0, 15).trim(), linha.substring(15, 16).trim(),
					linha.substring(16, 30).trim(), linha.substring(30, 158).trim(), linha.substring(158, 228).trim(),
					linha.substring(228, 248).trim(), linha.substring(248, 258).trim(),
					linha.substring(258, 268).trim(), linha.substring(268, 300).trim(),
					linha.substring(300, 320).trim(), linha.substring(320, 384).trim(),
					linha.substring(384, 448).trim(), linha.substring(448, 512).trim(),
					linha.substring(512, 522).trim(), linha.substring(522, 532).trim(),
					linha.substring(532, 542).trim(), linha.substring(542, 552).trim(),
					linha.substring(552, 562).trim(), linha.substring(562, 565).trim(),
					linha.substring(565, 568).trim(), linha.substring(568, 571).trim(),
					linha.substring(571, 574).trim(), linha.substring(574, 670).trim(),
					linha.substring(670, 690).trim(), linha.substring(690, 740).trim(),
					linha.substring(740, 804).trim(), linha.substring(804, 868).trim(),
					linha.substring(868, 870).trim(), linha.substring(870, 878).trim(),
					linha.substring(878, 958).trim(), linha.substring(958, 978).trim(),
					linha.substring(978, 998).trim(), linha.substring(998, 1018).trim(),
					linha.substring(1018, 1038).trim(), linha.substring(1038, 1166).trim());
			count++;
			// System.out.println(
			// c.getNome() + "," + c.getCnpjCpf() + "," + c.getNomeContador() +
			// ", linhas lidas:" + count);
			mapContribuinte.put(c.getInscricao().trim(), c);

			// pessoa
			try {
				Pessoa p = new Pessoa();
				p.setPessoaId(Long.valueOf(count));
				p.setBairro(c.getBairro());
				p.setCelular(c.getCelular());
				p.setCep(c.getCep());
				p.setCnpjCpf(c.getCnpjCpf());
				p.setComplemento(c.getComplemento());
				p.setEmail(c.getEmail());

				p.setEndereco(c.getLocalizacao());
				p.setInscricaoMunicipal(c.getInscricao());

				p.setMunicipio(c.getCidade());
				p.setUf(c.getEstado());

				try {
					p.setMunicipioIbge(Long.valueOf(municipiosIbgeDao.getCodigoIbge(p.getMunicipio(), p.getUf())));
				} catch (Exception e) {
					System.out.println("CÛdigo Ibge n„o encontrado para:" + p.getMunicipio() + "-" + p.getUf());
					e.printStackTrace();
				}
				p.setNome(c.getNome());
				p.setNomeFantasia(c.getNomeFantasia());
				p.setNumero(c.getNumero());
				p.setOptanteSimples(c.getIndicaSimplesNacional().substring(0, 1));
				p.setTelefone(c.getTelefone());
				p.setTipoPessoa(c.getTipoJuridico());
				trataNumerosTelefones(p);
				anulaCamposVazios(p);
				pessoaDao.save(p);
				// prestadores
				Prestadores pr = null;
				try {
					pr = new Prestadores();
					pr.setAutorizado("S");
					pr.setCelular(c.getCelular());
					pr.setEmail(c.getEmail());
					pr.setEnquadramento("N");
					pr.setFax(c.getFax());
					pr.setInscricaoPrestador(c.getCnpjCpf());
					pr.setTelefone(c.getTelefone());
					pr.setInscricaoMunicipal(p.getInscricaoMunicipal());
					trataNumerosTelefones(pr);
					anulaCamposVazios(pr);
					prestadoresDao.save(pr);
				} catch (Exception e) {
					System.out.print("Prestador n„o gravado: " + c.getNome());
					e.printStackTrace();
				}

			} catch (Exception e) {
				System.out.print("Pessoa n„o gravada: " + c.getNome());
				e.printStackTrace();
			}

		}

	}

	public void processaDadosAtividadeEconomicaContribuinte(List<String> dadosList) {
		int count = 0;

		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				continue;
			}
			count++;

			AtividadeEconomicaContribuinte a = new AtividadeEconomicaContribuinte(linha.substring(0, 15).trim(),
					linha.substring(15, 25).trim(), linha.substring(25, 275).trim(), linha.substring(275, 278).trim());
			count++;

			ContribuinteOrigem c = mapContribuinte.get(a.getInscricaoMunicipal());
			if (c != null) {
				Pessoa p = pessoaDao.findByCnpjCpf(c.getCnpjCpf().trim());
				try {
					PrestadoresAtividades pa = new PrestadoresAtividades();
					pa.setIcnaes(a.getcNAE2_0().trim());
					pa.setInscricaoPrestador(c.getCnpjCpf().trim());
					pa.setPrestadores(prestadoresDao.findByInscricao(c.getCnpjCpf().trim()));
					pa.setCodigoAtividade(String.valueOf(count));
					pa.setAliquota(BigDecimal.ONE);
					pa.setIlistaservicos(String.valueOf(count));
					prestadoresAtividadesDao.save(pa);
				} catch (Exception e) {
					System.out.println("Prestador atividade n„o gravado: " + c.getNome());
					e.printStackTrace();
				}

			}

		}

	}

	public void processaDadosGuiasPagas(List<String> dadosList) {
		int count = 0;
		mapGuiasPagas = new Hashtable<String, GuiaRecolhimento52>();
		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				continue;
			}

			GuiaRecolhimento52 g = new GuiaRecolhimento52(linha.substring(0, 10).trim(), linha.substring(10, 25).trim(),
					linha.substring(25, 35).trim(), linha.substring(35, 37).trim(),
					linha.substring(37, 101).trim().trim(), linha.substring(101, 111).trim(),
					linha.substring(111, 113).trim(), linha.substring(113, 117).trim(),
					linha.substring(117, 127).trim(), linha.substring(127, 137).trim(),
					linha.substring(137, 147).trim(), linha.substring(147, 157).trim(),
					linha.substring(157, 221).trim(), linha.substring(221, 477).trim());
			count++;
			// System.out.println(g.getAnoCompetencia() + "-" +
			// g.getMesCompetencia() + "-"
			// + g.getInscricaoMunicipalContribuinte() + "-" + g.getValor() + "-
			// linhas lidas:" + count);
			mapGuiasPagas.put(g.getIdGuiaRecolhimento(), g);

		}

	}

	public void processaDadosGuiasGeradas(List<String> dadosList) {
		int count = 0;

		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				continue;
			}

			GuiaRecolhimento51 g = new GuiaRecolhimento51(linha.substring(0, 10).trim(), linha.substring(10, 25).trim(),
					linha.substring(25, 35).trim(), linha.substring(35, 37).trim(), linha.substring(37, 101).trim(),
					linha.substring(101, 111).trim(), linha.substring(111, 121).trim(),
					linha.substring(121, 131).trim(), linha.substring(131, 141).trim(),
					linha.substring(141, 143).trim(), linha.substring(143, 147).trim());
			count++;
			// System.out.println(g.getAnoCompetencia() + "-" +
			// g.getMesCompetencia() + "-"
			// + g.getInscricaoMunicipalContribuinte() + "-" + g.getValor() + "-
			// linhas lidas:" + count);

			String descricao = util.getNomeMes(g.getMesCompetencia().trim()) + "/" + g.getAnoCompetencia();
			Competencias cp = competenciasDao.findByDescricao(descricao);
			if (cp == null || cp.getId() == 0) {
				cp = new Competencias();
				cp.setDataInicio(util.getFirstDayOfMonth(g.getAnoCompetencia().trim(), g.getMesCompetencia().trim()));
				cp.setDataFim(util.getLastDayOfMonth(g.getAnoCompetencia().trim(), g.getMesCompetencia().trim()));
				cp.setDataVencimento(util.getStringToDate(g.getDataVencimento()));
				cp.setDescricao(util.getNomeMes(g.getMesCompetencia().trim()) + "/" + g.getAnoCompetencia().trim());
				competenciasDao.save(cp);
			}
			try {

				Prestadores pr = new Prestadores();
				pr = prestadoresDao.findByInscricaoMunicipal(g.getInscricaoMunicipalContribuinte().trim());
				Guias guias = new Guias();
				guias.setCompetencias(cp);
				guias.setDataVencimento(util.getStringToDate(g.getDataVencimento()));
				String cnpj = mapContribuinte.get(g.getInscricaoMunicipalContribuinte().trim()).getCnpjCpf();
				guias.setInscricaoPrestador(cnpj);
				guias.setIntegrarGuia("S");
				guias.setNumeroGuia(Long.valueOf(g.getNumGuia().trim()));
				guias.setPrestadores(prestadoresDao.findByInscricao(cnpj));
				guias.setSituacao(getSituacaoGuia(g));
				guias.setValorGuia(util.getStringToBigDecimal(g.getValor()));
				guias.setTipo("P");
				guias.setValorImposto(guias.getValorGuia());
				guias.setIdGuiaRecolhimento(g.getIdGuiaRecolhimento());
				guiasDao.save(guias);
				// System.out.println(guias.getNumeroGuia() + " - " +
				// guias.getInscricaoPrestador());

				if (getSituacaoGuia(g).equals("P")) {
					GuiaRecolhimento52 guiaPaga = mapGuiasPagas.get(g.getIdGuiaRecolhimento());
					Pagamentos pg = new Pagamentos();
					try {
						pg.setDataPagamento(util.getStringToDate(guiaPaga.getDataPagamento().trim()));
						pg.setGuias(guias);
						pg.setNumeroGuia(guias.getNumeroGuia());
						pg.setNumeroPagamento(Long.valueOf(guiaPaga.getParcela().trim()));
						pg.setTipoPagamento("N");
						pg.setValorPago(util.getStringToBigDecimal(guiaPaga.getValorRecebido()));
						pg.setValorCorrecao(BigDecimal.valueOf(0));
						pg.setValorDiferenca(BigDecimal.valueOf(0));
						pg.setValorJuro(BigDecimal.valueOf(0));
						pg.setValorMulta(BigDecimal.valueOf(0));
						pagamentosDao.save(pg);
					} catch (Exception e) {
						System.out.println(" Pagamento de guia n„o gravado: " + guiaPaga.getDataPagamento());
						e.printStackTrace();
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(" Guia n„o gravada: " + g.getNumGuia());
			}

		}

	}

	private String getSituacaoGuia(GuiaRecolhimento51 g) {
		GuiaRecolhimento52 guiaPaga = mapGuiasPagas.get(g.getIdGuiaRecolhimento());
		if (guiaPaga != null) {
			return "P";
		} else {
			return "A";
		}
	}

	public void processaDadosGuiasDA(List<String> dadosList) {
		int count = 0;

		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				continue;
			}

			GuiaRecolhimento51Da g = new GuiaRecolhimento51Da(linha.substring(0, 10), linha.substring(10, 25),
					linha.substring(25, 35), linha.substring(35, 37), linha.substring(37, 101),
					linha.substring(101, 111), linha.substring(111, 121), linha.substring(121, 131),
					linha.substring(131, 141), linha.substring(141, 143), linha.substring(143, 147));
			count++;
			// System.out.println(g.getAnoCompetencia() + "-" +
			// g.getMesCompetencia() + "-"
			// + g.getInscricaoMunicipalContribuinte() + "-" + g.getValor() + "-
			// linhas lidas:" + count);

		}

	}

	public void processaDadosGuiaSuspensas(List<String> dadosList) {
		int count = 0;

		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				continue;
			}

			GuiaRecolhimento53 g = new GuiaRecolhimento53(linha.substring(0, 10), linha.substring(10, 25),
					linha.substring(25, 35), linha.substring(35, 37), linha.substring(37, 101),
					linha.substring(101, 111), linha.substring(111, 121), linha.substring(121, 131),
					linha.substring(131, 141), linha.substring(141, 143), linha.substring(143, 147),
					linha.substring(147, 157), linha.substring(157, 413), linha.substring(413, 541));
			count++;
			// System.out.println(g.getAnoCompetencia() + "-" +
			// g.getMesCompetencia() + "-"
			// + g.getInscricaoMunicipalContribuinte() + "-" + g.getValor() + "-
			// linhas lidas:" + count);

		}

	}

	public void processaDadosGuiaCanceladas(List<String> dadosList) {
		int count = 0;

		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				continue;
			}

			if (linha.substring(0, 30).trim().isEmpty()) {
				continue;
			}

			try {
				GuiaRecolhimento54 g = new GuiaRecolhimento54(linha.substring(0, 10), linha.substring(10, 25),
						linha.substring(25, 35), linha.substring(35, 37), linha.substring(37, 101),
						linha.substring(101, 111), linha.substring(111, 121), linha.substring(121, 131),
						linha.substring(131, 141), linha.substring(141, 143), linha.substring(143, 147),
						linha.substring(147, 157), linha.substring(157, 413), linha.substring(413, 541));
				count++;
				// System.out.println(g.getAnoCompetencia() + "-" +
				// g.getMesCompetencia() + "-"
				// + g.getInscricaoMunicipalContribuinte() + "-" + g.getValor()
				// + "- linhas lidas:" + count);
			} catch (Exception e) {

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

	public void processaAssociacaoGuiasNotasFiscais(List<String> dadosList) {

		for (String linha : dadosList) {

			try {

				GuiasNFSe guiaOrigem = new GuiasNFSe(linha.substring(0, 2).trim(), linha.substring(2, 7).trim(),
						linha.substring(7, 22).trim(), linha.substring(22, 37).trim(), linha.substring(37, 46).trim(),
						linha.substring(46, 54).trim(), linha.substring(54).trim());

				Guias g = guiasDao.findByIdGuiaRecolhimento(guiaOrigem.getIdGuiaRecolhimento());

				if (g != null) {
					NotasFiscais nf = notasFiscaisDao.findByNumeroDocumentoInscricaoPrestador(
							guiaOrigem.getNumeroDocumento(), g.getInscricaoPrestador());

					if (nf != null) {
						GuiasNotasFiscais gnf = new GuiasNotasFiscais();

						gnf.setGuias(g);
						gnf.setInscricaoPrestador(g.getInscricaoPrestador()); //
						gnf.setNumeroGuia(g.getNumeroGuia());
						gnf.setNumeroNota(nf.getNumeroNota());
						guiasNotasFiscaisDao.save(gnf);
					}
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

}
