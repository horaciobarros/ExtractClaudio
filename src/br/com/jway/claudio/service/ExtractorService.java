package br.com.jway.claudio.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import br.com.jway.claudio.dao.CompetenciasDao;
import br.com.jway.claudio.dao.Dao;
import br.com.jway.claudio.dao.EscrituracoesOrigemDao;
import br.com.jway.claudio.dao.GuiasDao;
import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisSubstDao;
import br.com.jway.claudio.dao.PagamentosDao;
import br.com.jway.claudio.dao.PessoaDao;
import br.com.jway.claudio.dao.PrestadoresAtividadesDao;
import br.com.jway.claudio.dao.PrestadoresDao;
import br.com.jway.claudio.dao.PrestadoresOptanteSimplesDao;
import br.com.jway.claudio.dao.ServicosOrigemDao;
import br.com.jway.claudio.dao.TomadoresDao;
import br.com.jway.claudio.entidadesOrigem.CnaeServicosContribuinte;
import br.com.jway.claudio.entidadesOrigem.ContribuinteOrigem;
import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.entidadesOrigem.GuiaOrigem;
import br.com.jway.claudio.entidadesOrigem.NotasFiscaisOrigem;
import br.com.jway.claudio.entidadesOrigem.ServicosNotasFiscaisOrigem;
import br.com.jway.claudio.entidadesOrigem.ServicosOrigem;
import br.com.jway.claudio.model.Competencias;
import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.model.GuiasNotasFiscais;
import br.com.jway.claudio.model.NotasFiscais;
import br.com.jway.claudio.model.NotasFiscaisSubst;
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
	private MunicipiosIbgeDao municipiosIbgeDao = new MunicipiosIbgeDao();
	private PessoaDao pessoaDao = new PessoaDao();
	private GuiasNotasFiscaisDao guiasNotasFiscaisDao = new GuiasNotasFiscaisDao();
	private Map<String, List<ServicosNotasFiscaisOrigem>> mapServicosNotasFiscaisOrigem = new Hashtable<String, List<ServicosNotasFiscaisOrigem>>();
	private NotasFiscaisSubstDao notasFiscaisSubstDao;
	private EscrituracoesOrigemDao escrituracoesOrigemDao = new EscrituracoesOrigemDao(); 
	private ServicosOrigemDao servicosOrigemDao = new ServicosOrigemDao();

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
				"NotasFiscaisSubst", "NotasFiscaisTomadores", "NotasFiscaisXml", "NotasFiscais", "Pagamentos",
				"PrestadoresAtividades", "PrestadoresOptanteSimples", "Guias", "Competencias");

	}

	public List<String> excluiParaProcessarNivel3() {
		return Arrays.asList("GuiasNotasFiscais", "NotasFiscaisCanceladas", "NotasFiscaisCondPagamentos",
				"NotasFiscaisEmails", "NotasFiscaisObras", "NotasFiscaisPrestadores", "NotasFiscaisServicos",
				"NotasFiscaisSubst", "NotasFiscaisTomadores", "NotasFiscaisXml", "NotasFiscais");
	}

	public List<String> excluiParaProcessarNivel4() {
		return Arrays.asList("GuiasNotasFiscais", "NotasFiscaisSubst");
	}

	public void excluiDados(String nomeEntidade) {

		dao.excluiDados(nomeEntidade);
	}

	public List<String> lerArquivosClaudio(String arquivoIn) {
		BufferedReader br;
		List<String> dadosList = new ArrayList<String>();
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream("c:/TEMP/claudio/tratados/" + arquivoIn + ".csv"), "utf-8"));
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

	public void processaDadosNotasFiscais(List<String> dadosList) throws Exception {

		FileLog log = new FileLog("notas_fiscais");

		int fator = 0;
		int totalLines = 0;
		double percent = 0;
		double divisor = 0;
		NotasFiscais nf;
		Prestadores pr;
		Pessoa pessoa;
		EscrituracoesOrigem escrituracoes;
		String inscricaoTomador;
		NotasFiscaisOrigem nfOrigem;
		Tomadores t;
		EscrituracoesOrigem escrituracaoSubstituida;
		DecimalFormat decimal = new DecimalFormat( "0.00" );
		
		Map<String, Prestadores> mapPrestadores = prestadoresDao.findAllMapReturn();
		Map<String, Pessoa> mapPessoa = pessoaDao.findAllMapReturn();
		Map<String, EscrituracoesOrigem> mapEscrituracoesOrigem = escrituracoesOrigemDao.findAllMapReturn();
		
		for (String linha : dadosList) {
			
			fator++;
			totalLines++;
			if (fator == 1000) {
				fator = 0;
				divisor = dadosList.size();
				percent = (totalLines / divisor * 100);
				System.out.println("Linhas processadas: " + totalLines + " ou " + decimal.format(percent) + " % de " 
						+ dadosList.size() + " - "+ Util.getDataHoraAtual());
			}

			List<String> arrayAux = util.splitRegistro(linha);
			
			nf = new NotasFiscais();

			try {

				nfOrigem = new NotasFiscaisOrigem(arrayAux.get(0), arrayAux.get(1), arrayAux.get(2),
						arrayAux.get(3), arrayAux.get(4), arrayAux.get(5), arrayAux.get(6), arrayAux.get(7),
						arrayAux.get(8), arrayAux.get(9), arrayAux.get(10), arrayAux.get(11), arrayAux.get(12),
						arrayAux.get(13), arrayAux.get(14), arrayAux.get(15), arrayAux.get(16), arrayAux.get(17),
						arrayAux.get(18), arrayAux.get(19), arrayAux.get(20), arrayAux.get(21), arrayAux.get(22),
						arrayAux.get(23), arrayAux.get(24), arrayAux.get(25), arrayAux.get(26), arrayAux.get(27),
						arrayAux.get(28), arrayAux.get(29), arrayAux.get(30), arrayAux.get(31), arrayAux.get(32),
						arrayAux.get(33), arrayAux.get(34), arrayAux.get(35), arrayAux.get(36), arrayAux.get(37),
						arrayAux.get(38), arrayAux.get(39), arrayAux.get(40), arrayAux.get(41), arrayAux.get(42),
						arrayAux.get(43), arrayAux.get(44), arrayAux.get(45), arrayAux.get(46), arrayAux.get(47),
						arrayAux.get(48), arrayAux.get(49));

				if (nfOrigem.getNotaFiscalAvulsa().equalsIgnoreCase("t")) { // não
																			// migrar
					continue;
				}

				String inscricaoPrestador = util.getCpfCnpj(util.getCpfCnpj(nfOrigem.getCpfCnpjPrestador()));
				pr = mapPrestadores.get(inscricaoPrestador);
				pessoa = mapPessoa.get(inscricaoPrestador);
				try {
					if (pr == null || pr.getId() == 0
							|| !inscricaoPrestador.trim().equals(pr.getInscricaoPrestador())) {
						System.out.println("Prestador nÃ£o encontrado:" + inscricaoPrestador);
						log.fillError(linha, "Prestador nÃ£o encontrado: " + inscricaoPrestador);
						throw new Exception();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				escrituracoes = mapEscrituracoesOrigem.get(nfOrigem.getId()); // pesquisa pela id nota
				try {
					if (escrituracoes == null) {
						System.out.println(
								"Escrituração não encontrada:" + pessoa.getNome() + " nota:" + nf.getNumeroNota());
						log.fillError(linha,
								"Escrituração não encontrada: " + pessoa.getNome() + " nota:" + nf.getNumeroNota());
						throw new Exception();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				nf.setIdOrigem(Long.parseLong(nfOrigem.getId()));
				
				if (util.isEmptyOrNull(nfOrigem.getCompetencia())) {
				nf.setDataHoraEmissao(util.getStringToDateHoursMinutes(nfOrigem.getCompetencia()));
				} else {
					nf.setDataHoraEmissao(util.getStringToDateHoursMinutes(nfOrigem.getDataDeCriacao()));
				}
				
				nf.setInscricaoPrestador(util.getCpfCnpj(nfOrigem.getCpfCnpjPrestador()));
				inscricaoTomador = util.getCpfCnpj(nfOrigem.getCnpjCpfTomador());
				if ("F".equals(util.getTipoPessoa(inscricaoTomador))) {
					if (Util.validarCpf(inscricaoTomador)) {
						nf.setInscricaoTomador(inscricaoTomador);
						if (!util.isEmptyOrNull(nfOrigem.getRazaoSocialTomador())) {
							nf.setNomeTomador(nfOrigem.getRazaoSocialTomador());
						} else {
							nf.setNomeTomador("Não informado");
						}
					}
				} else if ("J".equals(util.getTipoPessoa(inscricaoTomador))) {
					if (Util.validarCnpj(inscricaoTomador)) {
						nf.setInscricaoTomador(inscricaoTomador);
						if (!util.isEmptyOrNull(nfOrigem.getRazaoSocialTomador())) {
							nf.setNomeTomador(nfOrigem.getRazaoSocialTomador());
						} else {
							nf.setNomeTomador("Não informado");
						}
					}
				}

				nf.setNaturezaOperacao(nfOrigem.getNaturezaDaOperacao());
				nf.setNomePrestador(nfOrigem.getRazaoSocialPrestador());

				nf.setNumeroNota(Long.valueOf(escrituracoes.getNumeroNotaFiscal()));
				nf.setPrestadores(pr);
				if (util.getTipoPessoa(pr.getInscricaoPrestador()).equals("J")) {
					nf.setValorCofins(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getCofins())));
				}
				nf.setValorCsll(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getCsll())));
				nf.setValorInss(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getInss())));
				nf.setValorIr(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getIr())));
				nf.setValorOutrasRetencoes(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getOutrasRetencoes())));
				nf.setValorTotalServico(
						BigDecimal.valueOf(Double.parseDouble(nfOrigem.getValorDosServicosPrestados())));
				nf.setValorTotalIss(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getValorDoIssqnDevido())));
				nf.setSituacao("N");
				nf.setSituacaoTributaria("N");
				nf.setNumeroVerificacao(util.completarZerosEsquerda(nfOrigem.getId().toString(), 9));
				nf.setNaturezaOperacao("1"); // TODO resolver
				nf.setOptanteSimples("N"); // TODO resolver
				nf.setValorTotalBaseCalculo(
						BigDecimal.valueOf(Double.parseDouble(nfOrigem.getValorDosServicosPrestados())));
				nf.setValorTotalDeducao(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getDeducoes())));
				nf.setServicoPrestadoForaPais("N");
				nf.setDataHoraRps(util.converteDataHoraRpsClaudio(nfOrigem.getCompetencia()));
				nf.setNumeroRps(nf.getNumeroNota());
				nf.setSerieRps("C");
				List<BigDecimal> lista = Arrays.asList(nf.getValorCofins(), nf.getValorCsll(), nf.getValorInss(),
						nf.getValorIr());
				BigDecimal descontos = util.getSumOfBigDecimal(lista);

				nf.setValorLiquido(util.getSubtract(
						BigDecimal.valueOf(Double.parseDouble(nfOrigem.getValorDosServicosPrestados())), descontos));
				if (nf.getValorLiquido().compareTo(BigDecimal.ZERO) == -1) {
					nf.setValorLiquido(nf.getValorLiquido().multiply(BigDecimal.valueOf(-1)));
				}

				if (escrituracoes.getStatus() != null && escrituracoes.getStatus().contains("canceled")) {
					nf.setSituacaoOriginal("C");

				} else {
					nf.setSituacaoOriginal("N");
				}

				nf.setEscrituracaoSituacao(escrituracoes.getSituacao());
				nf.setEscrituracaoTipoDaNotafiscal(escrituracoes.getTipoDaNotaFiscal());
				if (!util.isEmptyOrNull(escrituracoes.getIdEscrituracaoSubstituida())) {
					escrituracaoSubstituida = mapEscrituracoesOrigem.get(escrituracoes.getIdEscrituracaoSubstituida());
					if (escrituracaoSubstituida == null) {
						log.fillError(linha, "Erro Escrituracao substituida não encontrada: "
								+ escrituracoes.getIdEscrituracaoSubstituida());
					} else {
						nf.setIdNotaFiscalSubstituida(escrituracaoSubstituida.getIdNotaFiscal());
					}
				}
				nf = notasFiscaisDao.save(nf);

				// tomadores
				t = null;

				if (!util.isEmptyOrNull(nf.getInscricaoTomador())
						&& !util.isEmptyOrNull(nfOrigem.getRazaoSocialTomador())) {
					t = tomadoresDao.findByInscricao(nf.getInscricaoTomador(), nf.getInscricaoPrestador());
					if (t == null || t.getId() == null) {
						try {
							t = new Tomadores();
							t.setOptanteSimples(util.getOptantePeloSimplesNacional("N"));
							t.setNome(nfOrigem.getRazaoSocialTomador());
							t.setNomeFantasia(nfOrigem.getRazaoSocialTomador());
							t.setPrestadores(nf.getPrestadores());
							t.setInscricaoTomador(nf.getInscricaoTomador());
							t.setTipoPessoa(util.getTipoPessoa(t.getInscricaoTomador()));
							t.setInscricaoEstadual(nfOrigem.getInscricaoEstadualTomador());
							t.setInscricaoMunicipal(nfOrigem.getInscricaoMunicipalTomador());
							t.setTelefone(util.getLimpaTelefone(nfOrigem.getTelefoneTomador().trim()));
							t.setEmail(util.trataEmail(nfOrigem.getEmailTomador()));

							// Complementando o endereço Tomador pode estar no
							// cadastro de pessoa
							Pessoa pessoaTomador = pessoaDao.findByCnpjCpf(t.getInscricaoTomador());
							if (pessoaTomador != null) {
								t.setBairro(pessoaTomador.getBairro());
								t.setCep(pessoaTomador.getCep());
								t.setComplemento(pessoaTomador.getComplemento());
								t.setEndereco(pessoaTomador.getEndereco());
								t.setNumero(pessoaTomador.getNumero());
								t.setMunicipio(pessoaTomador.getMunicipio());
								t.setMunicipioIbge(pessoaTomador.getMunicipioIbge());
							} else {
								t.setBairro(null);
								t.setCep(null);
								t.setComplemento(null);
								t.setEndereco(null);
								t.setMunicipio(null);
								t.setMunicipioIbge(null);

							}

							trataNumerosTelefones(t);
							anulaCamposVazios(t);

							t = tomadoresDao.save(t);
						} catch (Exception e) {
							log.fillError(linha, "Erro Tomadores ", e);
							e.printStackTrace();
							t = null;
						}

					}
				}

				processaDemaisTiposNotas(pr, nf, nfOrigem, log, linha, t, pessoa);

			} catch (Exception e2) {
				log.fillError(linha, "Erro NotasFiscais ", e2);
				e2.printStackTrace();
			}
			pr = null;
			nf = null;
			nfOrigem = null;
			linha = null;
			t = null;
			pessoa = null;

		}
		log.close();

	}

	private void processaDemaisTiposNotas(Prestadores p, NotasFiscais nf, NotasFiscaisOrigem nfOrigem, FileLog log,
			String linha, Tomadores t, Pessoa pessoa) {
		// -- serviços
		NotasThreadService nfServico = new NotasThreadService(p, nf, nfOrigem, log, linha, "S", null, t, pessoa, mapServicosNotasFiscaisOrigem);
		Thread s = new Thread(nfServico);
		s.start();

		// -- canceladas
		if (nf.getSituacaoOriginal().substring(0, 1).equals("C")) {
			NotasThreadService nfCanceladas = new NotasThreadService(p, nf, nfOrigem, log, linha, "C", null, t, pessoa);
			Thread c = new Thread(nfCanceladas);
			c.start();
		}

		// email
		if (nfOrigem.getEmailPrestador() != null && !nfOrigem.getEmailPrestador().isEmpty()) {
			NotasThreadService nfEmail = new NotasThreadService(p, nf, nfOrigem, log, linha, "E", null, t, pessoa);
			Thread e = new Thread(nfEmail);
			e.start();
		}

		// notas-fiscais-cond-pagamentos ??

		// notas-fiscais-obras ??

		// notas-fiscais-prestadores
		NotasThreadService nfPrestadores = new NotasThreadService(p, nf, nfOrigem, log, linha, "P", null, t, pessoa);
		Thread prestadoresThread = new Thread(nfPrestadores);
		prestadoresThread.start();

		// guias x notas fiscais
		// Guias g = new Guias();
		// if (nfOrigem.getNossoNumero() != null &&
		// !nfOrigem.getNossoNumero().trim().isEmpty()) {
		// g = guiasDao.findByNumeroGuia(nfOrigem.getNossoNumero());
		// if (g != null) {
		// NotasThreadService nfGuias = new NotasThreadService(p, nf, nfOrigem,
		// log, linha, "G", g);
		// Thread gThread = new Thread(nfGuias);
		// gThread.start();

		// } else {
		// System.out.println("Numero de guia nÃ£o encontrado: " +
		// nfOrigem.getNossoNumero());
		//
		// }
		// }

		// notas fiscais tomadores

		if (t != null && t.getId() != null) {
			NotasThreadService nfTomadores = new NotasThreadService(p, nf, nfOrigem, log, linha, "T", null, t, pessoa);
			Thread nftThread = new Thread(nfTomadores);
			nftThread.start();
		}

	}

	public List<String> lerArquivoXml(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void processaDadosContribuinte(List<String> dadosList) {

		FileLog log = new FileLog("contribuintes");

		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				break;
			}
			List<String> arrayAux = util.splitRegistro(linha);
			try {
				ContribuinteOrigem c = new ContribuinteOrigem(arrayAux.get(0), arrayAux.get(1), arrayAux.get(2),
						arrayAux.get(3), arrayAux.get(4), arrayAux.get(5), arrayAux.get(6), arrayAux.get(7),
						arrayAux.get(8), arrayAux.get(9), arrayAux.get(10), arrayAux.get(11), arrayAux.get(12),
						arrayAux.get(13), arrayAux.get(14), arrayAux.get(15), arrayAux.get(16), arrayAux.get(17),
						arrayAux.get(18), arrayAux.get(19), arrayAux.get(20), arrayAux.get(21), arrayAux.get(22),
						arrayAux.get(23), arrayAux.get(24), arrayAux.get(25));

				Pessoa p = new Pessoa();
				String bairro = c.getBairro();
				if (bairro.length() > 50) {
					bairro = bairro.substring(0, 50);
				}
				p.setBairro(bairro);
				p.setCelular(util.getLimpaTelefone(c.getTelefoneCelular()));
				p.setCep(util.trataCep(c.getCep().trim()));
				p.setComplemento(c.getComplemento());

				if (Util.validarEmail(util.trataEmail(c.getEmail()))) {
					p.setEmail(util.trataEmail(c.getEmail()));
				}
				if (c.getLogradouro().trim().length() > 50) {
					p.setEndereco(util.trataEndereco(c.getLogradouro()));
				} else {
					p.setEndereco(c.getLogradouro());
				}

				p.setInscricaoEstadual(c.getInscricaoEstadual());
				p.setInscricaoMunicipal(c.getInscricaoMunicipal());
				p.setMunicipio(c.getCidade());
				if (util.isEmptyOrNull(c.getCidade())) {
					c.setCidade("Cláudio");
				}
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
					System.out.println("Não encontrado codigo ibge: " + c.getCidade() + "-" + c.getEstado());
					e.printStackTrace();
				}
				p.setNome(c.getNomeRazaoSocial());
				p.setNomeFantasia(c.getNomeFantasia());
				p.setNumero(c.getNumero());
				p.setPessoaId(Long.valueOf(c.getId().replace("\"", "")));
				p.setCnpjCpf(util.getCpfCnpj(c.getCpfCnpj()));
				try {
					p.setTipoPessoa(util.getTipoPessoa(p.getCnpjCpf().trim()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (p.getTipoPessoa().equals("F")) {
					p.setSexo("M");
				}
				p.setTelefone(util.getLimpaTelefone(c.getTelefone()));
				p.setUf(c.getEstado());
				if (c.getTipoContribuinte() != null && c.getTipoContribuinte().equalsIgnoreCase("simple")) {
					p.setOptanteSimples("S");
				}
				p = trataNumerosTelefones(p);
				p = anulaCamposVazios(p);

				p = pessoaDao.save(p);
				try {

					Prestadores pr = new Prestadores();
					pr.setAutorizado("N");
					pr.setMotivo("Solicitar cadastro");
					pr.setCelular(p.getCelular());
					if (Util.validarEmail(util.trataEmail(p.getEmail()))) {
						pr.setEmail(util.trataEmail(p.getEmail()).replace(".@", "@"));
					}
					pr.setInscricaoMunicipal(p.getInscricaoMunicipal());
					pr.setInscricaoPrestador(p.getCnpjCpf());
					pr.setTelefone(p.getTelefone());
					pr.setEnquadramento("N");
					pr = trataNumerosTelefones(pr);
					pr = anulaCamposVazios(pr);
					prestadoresDao.save(pr);

				} catch (Exception e) {
					log.fillError(linha, "Prestador não gravado", e);
					System.out.println(
							"Prestador não gravado: " + p.getNome() + " quatidade de campos: " + arrayAux.size());
					e.printStackTrace();
				}

			} catch (Exception e) {
				log.fillError(linha, "Pessoa não gravada", e);
				System.out.println("Pessoa não gravada: " + linha + " quatidade de campos: " + arrayAux.size());
				e.printStackTrace();
			}

		}
		log.close();
	}

	public void processaDadosAtividadeEconomicaContribuinte(List<String> dadosList) {

		FileLog log = new FileLog("cnae_servicos_contribuintes");
		for (String linha : dadosList) {

			if (linha == null || linha.trim().isEmpty()) {
				continue;
			}

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
					cnae.setServicoCodigo(cnae.getServicoCodigo().replace("a", "").replace("b", ""));
				}
				Pessoa pessoa = pessoaDao.findByPessoaId(cnae.getIdContribuinte());
				Prestadores pr = prestadoresDao.findByInscricao(pessoa.getCnpjCpf());
				ServicosOrigem servico = servicosOrigemDao.findByIdCodigo((cnae.getServicoCodigo()));

				try {
					PrestadoresAtividades pa = new PrestadoresAtividades();
					pa.setAliquota(BigDecimal.valueOf(util.corrigeDouble(servico.getAliquota())));
					if (pa.getAliquota().compareTo(BigDecimal.ZERO) == 0) {
						pa.setAliquota(BigDecimal.ONE);
					}
					pa.setCodigoAtividade(cnae.getCnaeCodigo().replace("-", "").replace("/", "").substring(0, 5));
					pa.setIcnaes(cnae.getCnaeCodigo().replace("-", "").replace("/", ""));
					pa.setIlistaservicos(util.completarZerosEsquerda(
							servico.getCodigo().replace(".", "").replace("a", "").replace("b", ""), 4));
					pa.setInscricaoPrestador(pr.getInscricaoPrestador());
					pa.setPrestadores(pr);
					prestadoresAtividadesDao.save(pa);
				} catch (Exception e) {
					System.out.println(linha);
					log.fillError(linha, "Prestadores atividades ", e);
					e.printStackTrace();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		log.close();

	}

	public void processaDadosGuiasPagas(List<String> dadosList) {

	}

	public void processaDadosGuias(List<String> dadosList) {

		FileLog log = new FileLog("guias");
		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				continue;
			}

			List<String> arrayAux = util.splitRegistro(linha);

			GuiaOrigem guiaOrigem = new GuiaOrigem(arrayAux.get(0), arrayAux.get(1), arrayAux.get(2), arrayAux.get(3),
					arrayAux.get(4), arrayAux.get(5), arrayAux.get(6), arrayAux.get(7), arrayAux.get(8),
					arrayAux.get(9), arrayAux.get(10), arrayAux.get(11), arrayAux.get(12), arrayAux.get(13),
					arrayAux.get(14), arrayAux.get(15), arrayAux.get(16), arrayAux.get(17));

			if (guiaOrigem.getNotaFiscalAvulsa().equalsIgnoreCase("t")) {
				continue;
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
				guias.setValorGuia(BigDecimal.valueOf(util.corrigeDouble(guiaOrigem.getValorTotal())));
				guias.setValorImposto(BigDecimal.valueOf(util.corrigeDouble(guiaOrigem.getValor())));

				guias.setIdGuiaRecolhimento(guiaOrigem.getId());
				// guias.setIdNotasFiscais(guiaOrigem.getIdNotasFiscais());
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
						System.out.println(guiaOrigem.getId());
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				log.fillError(linha, "pagamentos", e);
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
		if (util.isEmptyOrNull(pessoa.getEndereco())) {
			pessoa.setEndereco(null);
		}
		if (util.isEmptyOrNull(pessoa.getBairro())) {
			pessoa.setBairro(null);
		}
		if (util.isEmptyOrNull(pessoa.getNome())) {
			pessoa.setNome(null);
		}
		if (util.isEmptyOrNull(pessoa.getNomeFantasia())) {
			pessoa.setNomeFantasia(null);
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

	public void processaDadosServicosNotasFiscais(List<String> dadosList) {
		FileLog log = new FileLog("servicos_notas_fiscais");
		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				break;
			}

			List<String> arrayAux = util.splitRegistro(linha);

			ServicosNotasFiscaisOrigem snf = new ServicosNotasFiscaisOrigem(arrayAux.get(0), arrayAux.get(1),
					arrayAux.get(2), arrayAux.get(3), arrayAux.get(4), arrayAux.get(5));

			try {
				List<ServicosNotasFiscaisOrigem> list = mapServicosNotasFiscaisOrigem.get(arrayAux.get(0));

				if (list == null) {
					list = new ArrayList<ServicosNotasFiscaisOrigem>();
				}
				list.add(snf);
				mapServicosNotasFiscaisOrigem.put(snf.getIdNotaFiscal(), list);

			} catch (Exception e) {
				e.printStackTrace();
				log.fillError(linha, "", e);
			}
		}

	}

	public void processaDadosGuiasNotasFiscais() {

		FileLog log = new FileLog("guias_notas_fiscais");
		for (Guias guia : guiasDao.findAll()) {
			try {
				if (guia.getIdNotasFiscais() != null && !guia.getIdNotasFiscais().isEmpty()) {
					String[] lista = guia.getIdNotasFiscais().split(",");
					for (int i = 0; i < lista.length; i++) {
						NotasFiscais nf = notasFiscaisDao.findById(Long.parseLong(lista[i]));
						if (nf != null) {
							GuiasNotasFiscais gnf = new GuiasNotasFiscais();
							gnf.setGuias(guia);
							gnf.setInscricaoPrestador(guia.getInscricaoPrestador()); //
							gnf.setNumeroGuia(guia.getNumeroGuia());
							gnf.setNumeroNota(nf.getNumeroNota());
							guiasNotasFiscaisDao.save(gnf);
						}
					}
				}
			} catch (Exception e) {
				log.fillError(guia.toString(), "guias notas fiscais", e);
			}
		}
	}

	public void incluiCompetencias() {

		for (int ano = 2010; ano < 2017; ano++) {

			for (int mes = 1; mes <= 12; mes++) {
				String descricao = util.getNomeMes(Integer.toString(mes)) + "/" + ano;
				Competencias cp = competenciasDao.findByDescricao(descricao);

				try {
					if (cp == null || cp.getId() == 0) { // acertar datas
						cp = new Competencias();
						cp.setDescricao(descricao.trim());
						cp.setDataInicio(util.getFirstDayOfMonth(Integer.toString(ano), Integer.toString(mes)));
						cp.setDataFim(util.getLastDayOfMonth(Integer.toString(ano), Integer.toString(mes)));
						cp.setDataVencimento(util.getDecimoDiaMesPosterior(cp.getDataFim()));

						competenciasDao.save(cp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void processaDadosNotasFiscaisSubstituidas() {

		FileLog log = new FileLog("notas_fiscais_substitutas");

		NotasFiscais nfSubstituida;
		notasFiscaisDao = new NotasFiscaisDao();
		NotasFiscaisSubst nfsub;
		notasFiscaisSubstDao = new NotasFiscaisSubstDao();
		
		for (NotasFiscais nf : notasFiscaisDao.findSubstitutas()) {

			// buscando a nota que foi substituida
			nfSubstituida = notasFiscaisDao
					.findByIdOrigem(Long.parseLong(nf.getIdNotaFiscalSubstituida()));
			if (nfSubstituida != null) {
				try {
					nfsub = new NotasFiscaisSubst();
					nfsub.setDatahorasubstituicao(nfSubstituida.getDataHoraEmissao());
					nfsub.setInscricaoPrestador(nfSubstituida.getInscricaoPrestador());
					nfsub.setNumeroNota(Long.valueOf(nfSubstituida.getNumeroNota()));
					nfsub.setNumeroNotaSubstituta(nf.getNumeroNota());
					nfsub.setMotivo("Dados incorretos");
					nfsub.setNotasFiscais(nf);
					notasFiscaisSubstDao.save(nfsub);
					nfsub = null;
				} catch (Exception e) {
					e.printStackTrace();
					log.fillError(nfSubstituida.getNumeroNota().toString() + nfSubstituida.getNomePrestador(),
							"Nota Fiscal Substituida", e);
				}
				nfSubstituida = null;	
			} else {
				log.fillError("Nota Fiscal substituida não encontrada:", nf.getIdNotaFiscalSubstituida());
			}

		}

	}

}
