package br.com.jway.claudio.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import br.com.jway.claudio.dao.EscrituracoesOrigemDao;
import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.MunicipiosIbgeDao;
import br.com.jway.claudio.dao.NotasFiscaisCanceladasDao;
import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisEmailsDao;
import br.com.jway.claudio.dao.NotasFiscaisPrestadoresDao;
import br.com.jway.claudio.dao.NotasFiscaisServicosDao;
import br.com.jway.claudio.dao.NotasFiscaisTomadoresDao;
import br.com.jway.claudio.dao.PessoaDao;
import br.com.jway.claudio.dao.PrestadoresAtividadesDao;
import br.com.jway.claudio.dao.PrestadoresDao;
import br.com.jway.claudio.dao.TomadoresDao;
import br.com.jway.claudio.entidadesOrigem.DadosLivroPrestador;
import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.entidadesOrigem.NotasFiscaisOrigem;
import br.com.jway.claudio.entidadesOrigem.ServicosNotasFiscaisOrigem;
import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.model.GuiasNotasFiscais;
import br.com.jway.claudio.model.NotasFiscais;
import br.com.jway.claudio.model.NotasFiscaisCanceladas;
import br.com.jway.claudio.model.NotasFiscaisEmails;
import br.com.jway.claudio.model.NotasFiscaisPrestadores;
import br.com.jway.claudio.model.NotasFiscaisServicos;
import br.com.jway.claudio.model.NotasFiscaisTomadores;
import br.com.jway.claudio.model.Pessoa;
import br.com.jway.claudio.model.Prestadores;
import br.com.jway.claudio.model.PrestadoresAtividades;
import br.com.jway.claudio.model.Tomadores;
import br.com.jway.claudio.util.FileLog;
import br.com.jway.claudio.util.Util;

public class NotaMaeThreadService implements Runnable {

	private FileLog log;
	private String linha;
	private Map<String, Prestadores> mapPrestadores;
	private Map<String, Pessoa> mapPessoa;
	private Map<String, EscrituracoesOrigem> mapEscrituracoesOrigem;
	NotasFiscais nf;
	Prestadores pr;
	Pessoa pessoa;
	EscrituracoesOrigem escrituracoes;
	String inscricaoTomador;
	NotasFiscaisOrigem nfOrigem;
	Tomadores t;
	EscrituracoesOrigem escrituracaoSubstituida;
	private Util util;
	private NotasFiscaisDao notasFiscaisDao = new NotasFiscaisDao();
	private TomadoresDao tomadoresDao = new TomadoresDao();
	private PrestadoresDao prestadoresDao = new PrestadoresDao();
	private PessoaDao pessoaDao = new PessoaDao();
	private Map<String, List<ServicosNotasFiscaisOrigem>> mapServicosNotasFiscaisOrigem;
	private EscrituracoesOrigemDao escrituracoesOrigemDao = new EscrituracoesOrigemDao();

	public NotaMaeThreadService(String linha, FileLog log, Map<String, Prestadores> mapPrestadores,
			Map<String, Pessoa> mapPessoa, Map<String, EscrituracoesOrigem> mapEscrituracoesOrigem,
			Map<String, List<ServicosNotasFiscaisOrigem>> mapServicosNotasFiscaisOrigem, Util util) {
		this.log = log;
		this.linha = linha;
		this.mapPrestadores = mapPrestadores;
		this.mapPessoa = mapPessoa;
		this.mapEscrituracoesOrigem = mapEscrituracoesOrigem;
		this.util = util;
		this.mapServicosNotasFiscaisOrigem = mapServicosNotasFiscaisOrigem;
	}

	@Override
	public void run() {
		int linhas = 0;
		ExtractorService.threadsAtivas++;
		try {
			List<String> arrayAux = util.splitRegistro(linha);

			nf = new NotasFiscais();

			try {

				nfOrigem = new NotasFiscaisOrigem(arrayAux.get(0), arrayAux.get(1), arrayAux.get(2), arrayAux.get(3),
						arrayAux.get(4), arrayAux.get(5), arrayAux.get(6), arrayAux.get(7), arrayAux.get(8),
						arrayAux.get(9), arrayAux.get(10), arrayAux.get(11), arrayAux.get(12), arrayAux.get(13),
						arrayAux.get(14), arrayAux.get(15), arrayAux.get(16), arrayAux.get(17), arrayAux.get(18),
						arrayAux.get(19), arrayAux.get(20), arrayAux.get(21), arrayAux.get(22), arrayAux.get(23),
						arrayAux.get(24), arrayAux.get(25), arrayAux.get(26), arrayAux.get(27), arrayAux.get(28),
						arrayAux.get(29), arrayAux.get(30), arrayAux.get(31), arrayAux.get(32), arrayAux.get(33),
						arrayAux.get(34), arrayAux.get(35), arrayAux.get(36), arrayAux.get(37), arrayAux.get(38),
						arrayAux.get(39), arrayAux.get(40), arrayAux.get(41), arrayAux.get(42), arrayAux.get(43),
						arrayAux.get(44), arrayAux.get(45), arrayAux.get(46), arrayAux.get(47), arrayAux.get(48),
						arrayAux.get(49));

				if (nfOrigem.getNotaFiscalAvulsa().equalsIgnoreCase("t")) { // não
																			// migrar
					return;
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
						/*
						if (pr == null || pr.getId() == 0
								|| !inscricaoPrestador.trim().equals(pr.getInscricaoPrestador())) {
							System.out.println("Prestador nÃ£o encontrado:" + inscricaoPrestador);
							log.fillError(linha, "Prestador nÃ£o encontrado: " + inscricaoPrestador);
							throw new Exception();
						/*
						try{
							if(pessoa == null || pessoa.getId() == 0){
								Pessoa p = new Pessoa();
								ExtractorService.idNovasPessoas++;
								p.setPessoaId(Long.valueOf(ExtractorService.idNovasPessoas));
								p.setCnpjCpf(inscricaoPrestador);
								p.setEmail(util.trataEmail(nfOrigem.getEmailPrestador()));
								p.setUf(nfOrigem.getEstadoDePrestacaoDoServico());
								p.setInscricaoEstadual(nfOrigem.getInscricaoEstadualPrestador());
								p.setNome(nfOrigem.getRazaoSocialPrestador());
								p.setNomeFantasia(nfOrigem.getRazaoSocialPrestador());
								p.setTelefone(util.getLimpaTelefone(nfOrigem.getTelefonePrestador()));
								p.setInscricaoMunicipal(nfOrigem.getInscricaoMunicipalPrestador());
								String tipoPessoa = util.getTipoPessoa(inscricaoPrestador);
								p.setTipoPessoa(tipoPessoa);
								pessoa = pessoaDao.save(p);

							}
							Prestadores prest = new Prestadores();
							prest.setAutorizado("N");
							prest.setEmail(pessoa.getEmail());
							prest.setEnquadramento("N");
							prest.setMotivo("Solicitar Cadastro");
							prest.setInscricaoPrestador(pessoa.getCnpjCpf());
							prest.setTelefone(pessoa.getTelefone());
							prest.setInscricaoMunicipal(pessoa.getInscricaoMunicipal());
							prestadoresDao.save(prest);
							mapPrestadores = prestadoresDao.findAllMapReturn();
							mapPessoa = pessoaDao.findAllMapReturn();
						}
						catch(Exception e){
							e.printStackTrace();
							log.fillError(linha, "Erro ao salvar Pessoas/Prestadores ", e);
						}
						pr = mapPrestadores.get(inscricaoPrestador);
						pessoa = mapPessoa.get(inscricaoPrestador);
						if (pr == null || pr.getId() == 0
								|| !inscricaoPrestador.trim().equals(pr.getInscricaoPrestador())) {
							System.out.println("Prestador nÃ£o encontrado:" + inscricaoPrestador);
							log.fillError(linha, "Prestador nÃ£o encontrado: " + inscricaoPrestador);
							throw new Exception();
						}*/
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.fillError(linha, "Erro ao salvar Prestador: "+inscricaoPrestador+" ", e);
				}

				escrituracoes = mapEscrituracoesOrigem.get(nfOrigem.getId()); // pesquisa
																				// pela
																				// id
																				// nota
				try {
					if (escrituracoes == null || escrituracoes.getId() == null) {
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
				if (nfOrigem.getCompetencia() != null) {
					Long ano = Long.parseLong(nfOrigem.getCompetencia().substring(0, 4));
					if (ano < 1000) {
						nf.setDataHoraRps(nf.getDataHoraEmissao());
					} else {
						nf.setDataHoraRps(util.converteDataHoraRpsClaudio(nfOrigem.getCompetencia()));
					}
				} else {
					nf.setDataHoraRps(util.converteDataHoraRpsClaudio(nfOrigem.getCompetencia()));
				}

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
					nf.setSituacao("C");
				} else if (escrituracoes.getStatus() != null && escrituracoes.getStatus().contains("replaced")) {
					nf.setSituacaoOriginal("S");
					nf.setSituacao("S");
				} else {
					nf.setSituacaoOriginal("N");
				}

				nf.setEscrituracaoSituacao(escrituracoes.getSituacao());
				nf.setEscrituracaoTipoDaNotafiscal(escrituracoes.getTipoDaNotaFiscal());
				if (!util.isEmptyOrNull(escrituracoes.getIdEscrituracaoSubstituida())) {
					escrituracaoSubstituida = escrituracoesOrigemDao
							.findById(escrituracoes.getIdEscrituracaoSubstituida().trim());
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

				if (!util.isEmptyOrNull(nf.getInscricaoTomador())) {
					t = tomadoresDao.findByInscricao(nf.getInscricaoTomador(), nf.getInscricaoPrestador());
					if (t == null || t.getId() == null) {
						try {
							t = new Tomadores();
							t.setOptanteSimples(util.getOptantePeloSimplesNacional("N"));
							t.setNome(nf.getNomeTomador());
							t.setNomeFantasia(nf.getNomeTomador());
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

							util.trataNumerosTelefones(t);
							util.anulaCamposVazios(t);

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
		} catch (Exception e) {
			log.fillError(linha, e.getMessage());
			e.printStackTrace();
		} finally {
			ExtractorService.threadsAtivas--;
		}
	}

	private void processaDemaisTiposNotas(Prestadores p, NotasFiscais nf, NotasFiscaisOrigem nfOrigem, FileLog log,
			String linha, Tomadores t, Pessoa pessoa) {
		// -- serviços
		NotasThreadService nfServico = new NotasThreadService(p, nf, nfOrigem, log, linha, "S", null, t, pessoa,
				mapServicosNotasFiscaisOrigem);
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
}