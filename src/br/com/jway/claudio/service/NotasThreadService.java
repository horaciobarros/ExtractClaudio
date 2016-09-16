package br.com.jway.claudio.service;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import br.com.jway.claudio.dao.MunicipiosIbgeDao;
import br.com.jway.claudio.dao.NotasFiscaisCanceladasDao;
import br.com.jway.claudio.dao.NotasFiscaisEmailsDao;
import br.com.jway.claudio.dao.NotasFiscaisPrestadoresDao;
import br.com.jway.claudio.dao.NotasFiscaisServicosDao;
import br.com.jway.claudio.dao.NotasFiscaisTomadoresDao;
import br.com.jway.claudio.dao.PrestadoresAtividadesDao;
import br.com.jway.claudio.dao.ServicosDao;
import br.com.jway.claudio.dao.ServicosOrigemDao;
import br.com.jway.claudio.entidadesOrigem.NotasFiscaisOrigem;
import br.com.jway.claudio.entidadesOrigem.Servicos;
import br.com.jway.claudio.entidadesOrigem.ServicosNotasFiscaisOrigem;
import br.com.jway.claudio.entidadesOrigem.ServicosOrigem;
import br.com.jway.claudio.model.Guias;
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

public class NotasThreadService implements Runnable {
	private Prestadores pr;
	private NotasFiscais nf;
	private NotasFiscaisOrigem nfOrigem;
	private FileLog log;
	private String linha;
	private String tipoNotaFilha;
	private Util util = new Util();
	private NotasFiscaisServicosDao notasFiscaisServicosDao = new NotasFiscaisServicosDao();
	private NotasFiscaisEmailsDao notasFiscaisEmailsDao = new NotasFiscaisEmailsDao();
	private NotasFiscaisPrestadoresDao notasFiscaisPrestadoresDao = new NotasFiscaisPrestadoresDao();
	private Tomadores tomadores;
	private NotasFiscaisTomadoresDao notasFiscaisTomadoresDao = new NotasFiscaisTomadoresDao();
	private Pessoa pessoa;
	private Map<String, List<ServicosNotasFiscaisOrigem>> mapServicosNotasFiscaisOrigem;
	private NotasFiscaisCanceladasDao notasFiscaisCanceladasDao = new NotasFiscaisCanceladasDao();
	private ServicosDao servicosDao = new ServicosDao();
	private ServicosOrigemDao servicosOrigemDao = new ServicosOrigemDao();
	private PrestadoresAtividadesDao prestadoresAtividadesDao = new PrestadoresAtividadesDao();
	private MunicipiosIbgeDao municipiosIbgeDao = new MunicipiosIbgeDao();

	public NotasThreadService(Prestadores pr, NotasFiscais nf, NotasFiscaisOrigem nfOrigem, FileLog log, String linha,
			String tipoNotaFilha, Guias guia, Tomadores tomadores, Pessoa pessoa,
			Map<String, List<ServicosNotasFiscaisOrigem>> mapServicosNotasFiscaisOrigem2) {
		this.pessoa = pessoa;
		this.pr = pr;
		this.nf = nf;
		this.nfOrigem = nfOrigem;
		this.log = log;
		this.linha = linha;
		this.tipoNotaFilha = tipoNotaFilha;
		this.tomadores = tomadores;
		this.mapServicosNotasFiscaisOrigem = mapServicosNotasFiscaisOrigem2;

	}

	public NotasThreadService(Prestadores pr, NotasFiscais nf, NotasFiscaisOrigem nfOrigem, FileLog log, String linha,
			String tipoNotaFilha, Guias guia, Tomadores tomadores, Pessoa pessoa) {
		this.pessoa = pessoa;
		this.pr = pr;
		this.nf = nf;
		this.nfOrigem = nfOrigem;
		this.log = log;
		this.linha = linha;
		this.tipoNotaFilha = tipoNotaFilha;
		this.tomadores = tomadores;

	}

	@Override
	public void run() {
		if (tipoNotaFilha.equals("S")) { // serviços
			try {
				NotasFiscaisServicos nfs;
				List<ServicosNotasFiscaisOrigem> listaItens = mapServicosNotasFiscaisOrigem.get(nfOrigem.getId());
				for (ServicosNotasFiscaisOrigem s : listaItens) {
					try {
						nfs = new NotasFiscaisServicos();
						nfs.setInscricaoPrestador(util.getCpfCnpj(nfOrigem.getCpfCnpjPrestador()));
						nfs.setNumeroNota(Long.valueOf(nf.getNumeroNota()));

						if (nf.getNaturezaOperacao().equals("2")) { // retenção
							String localOndeFoiPrestadoServico = nfOrigem.getLocalOndeFoiPrestadoOServico();
							String estadoDePrestacaoDoServico = nfOrigem.getEstadoDePrestacaoDoServico();
							if (estadoDePrestacaoDoServico == null) {
								estadoDePrestacaoDoServico = "MG";
							}
							nfs.setMunicipioIbge(
									municipiosIbgeDao.getCodigoIbge(localOndeFoiPrestadoServico,
											estadoDePrestacaoDoServico));
							if (util.isEmptyOrNull(nfs.getMunicipioIbge())) {
								nfs.setMunicipioIbge(tomadores.getMunicipioIbge().toString());
							}
						} else {
							nfs.setMunicipioIbge(util.CODIGO_IBGE_CLAUDIO);
						}
						
						nfs.setAliquota(BigDecimal.valueOf(Double.parseDouble(s.getAliquota())));

						StringBuilder sbItem = new StringBuilder();

						// Map<String, Servicos> mapServicosAux = new
						// Hashtable<String, Servicos>();

						Servicos servico = servicosDao.findByIdOrigem(Long.parseLong(s.getIdServico().trim()));

						if (servico != null) {
							String codigoServico = servico.getCodigo();

							if (codigoServico != null && !codigoServico.trim().isEmpty()) {
								codigoServico = codigoServico.replaceAll("\\.", "").replace("a", "").replace("b", "");
								codigoServico = util.completarZerosEsquerda(codigoServico, 4);
								sbItem.append(codigoServico);
								nfs.setIcnaes(servico.getCnaes());

								if (servico.getNome() != null && servico.getNome().length() > 200) {
									nfs.setDescricaoCnae(servico.getNome().substring(0, 200));
								} else {
									nfs.setDescricaoCnae(servico.getNome());
								}
							}
						}

						if (sbItem.toString().isEmpty()) {
							nfs.setIdServicoPerdido(Long.parseLong(s.getIdServico()));
							ServicosOrigem serv = servicosOrigemDao.findByIdOrigem(Long.parseLong(s.getIdServico()));
							String codigoServico = "";
							if (serv != null) {
								codigoServico = serv.getCodigo();
							}

							if (codigoServico != null && !codigoServico.trim().isEmpty()) {
								codigoServico = codigoServico.replaceAll("\\.", "").replace("a", "").replace("b", "");
								codigoServico = util.completarZerosEsquerda(codigoServico, 4);
								sbItem.append(codigoServico);
								nfs.setIcnaes(serv.getCnaes());

								if (serv.getNome() != null && serv.getNome().length() > 200) {
									nfs.setDescricaoCnae(serv.getNome().substring(0, 200));
								} else {
									nfs.setDescricaoCnae(serv.getNome());
								}
							}

						}

						if (sbItem.toString().isEmpty()) {
							PrestadoresAtividades pa = consideraCnaePrestador(nfs.getInscricaoPrestador(),
									nfs.getAliquota());
							String codigoServico = "";
							if (pa != null) {
								codigoServico = pa.getIlistaservicos();
							}

							if (codigoServico != null && !codigoServico.trim().isEmpty()) {
								codigoServico = codigoServico.replaceAll("\\.", "").replace("a", "").replace("b", "");
								codigoServico = util.completarZerosEsquerda(codigoServico, 4);
								sbItem.append(codigoServico);
								nfs.setIcnaes(pa.getIcnaes());
								Servicos serv = servicosDao.findByCodigoServicoCodigoCnae(codigoServico,
										pa.getIcnaes());
								if (serv == null) {
									serv = servicosDao.findByCodigo(codigoServico);
								}
								String descricaoCnae = "";
								if (serv != null) {
									descricaoCnae = serv.getNome();
								}
								if (descricaoCnae.isEmpty()) {
									ServicosOrigem servOrigem = servicosOrigemDao
											.findByCodigoServicoCodigoCnae(codigoServico, pa.getIcnaes());
									if (servOrigem == null) {
										servOrigem = servicosOrigemDao.findByCodigo(codigoServico);
									}
									if (servOrigem != null) {
										descricaoCnae = servOrigem.getNome();
									}
								}
								if (descricaoCnae != null && descricaoCnae.length() > 200) {
									nfs.setDescricaoCnae(descricaoCnae.substring(0, 200));
								} else {
									nfs.setDescricaoCnae(descricaoCnae);
								}
							}
						}

						if (sbItem.toString().isEmpty()) {
							log.fillError(linha,
									"Nota Fiscal Servico - Serviço não encontrado:" + listaItens.get(0).getIdServico()
											+ " da nota " + nf.getNumeroNota() + " de "
											+ nfOrigem.getRazaoSocialPrestador());
						}
						nfs.setItemListaServico(util.completarZerosEsquerda(sbItem.toString(), 4));

						try {
							if (nfOrigem.getDescricaoDoServico().length() > 2000) {
								nfs.setDescricao(nfOrigem.getDescricaoDoServico().substring(0, 2000));
							} else {
								nfs.setDescricao(nfOrigem.getDescricaoDoServico());
							}
						} catch (Exception e) {
							System.out.println(nfs.getItemListaServico());
							e.printStackTrace();
							log.fillError(linha, "Nota Fiscal Servico", e);
						}

						nfs.setValorServico(
								BigDecimal.valueOf(Double.parseDouble(s.getValor())));
						nfs.setQuantidade(BigDecimal.valueOf(1));
						nfs.setValorDeducao(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getDeducoes())));
						nfs.setValorBaseCalculo(nf.getValorTotalBaseCalculo());
						nfs.setValorIss(nf.getValorTotalIss());
						nfs.setNotasFiscais(nf);
						nfs.setValorUnitario(
								BigDecimal.valueOf(Double.parseDouble(s.getValor())));
						if (nfs.getAliquota().compareTo(BigDecimal.ZERO) == 0) {
							nfs.setAliquota(BigDecimal.valueOf(1));
						}
						notasFiscaisServicosDao.save(nfs);
						nfs = null;
					} catch (Exception e) {
						System.out.println(linha);
						e.printStackTrace();
						log.fillError(linha, "Nota Fiscal Servico", e);
					}
				}
			} catch (Exception e) {
				System.out.println(linha);
				e.printStackTrace();
				log.fillError(linha, "Nota Fiscal Servico", e);
			}
		}
		if (tipoNotaFilha.equals("C")) { // canceladas
			try {
				NotasFiscaisCanceladas nfc = new NotasFiscaisCanceladas();
				nfc.setDatahoracancelamento(nf.getDataHoraEmissao());
				nfc.setInscricaoPrestador(nf.getInscricaoPrestador());
				nfc.setNumeroNota(Long.valueOf(nf.getNumeroNota()));
				if (util.isEmptyOrNull(nfc.getMotivo())) {
					nfc.setMotivo("Dados incorretos");
				}
				nfc.setNotasFiscais(nf);
				notasFiscaisCanceladasDao.save(nfc);
				nfc = null;

			} catch (Exception e) {
				e.printStackTrace();
				log.fillError(linha, "Nota Fiscal Cancelada", e);
			}
		}

		if (tipoNotaFilha.equals("E") && pr.getEmail() != null && !pr.getEmail().isEmpty()
				&& Util.validarEmail(util.trataEmail(pr.getEmail()))) { // email
			try {
				NotasFiscaisEmails nfe = new NotasFiscaisEmails();
				nfe.setEmail(util.trataEmail(pr.getEmail()));
				nfe.setInscricaoPrestador(util.getCpfCnpj(nfOrigem.getCpfCnpjPrestador()));
				nfe.setNotasFiscais(nf);
				nfe.setNumeroNota(Long.valueOf(nf.getNumeroNota()));
				notasFiscaisEmailsDao.save(nfe);
				nfe = null;
			} catch (Exception e) {
				e.printStackTrace();
				log.fillError(linha, "Nota Fiscal Email", e);
			}
		}

		if (tipoNotaFilha.equals("P")) { // prestadores
			try {
				NotasFiscaisPrestadores nfp = new NotasFiscaisPrestadores();
				nfp.setBairro(pessoa.getBairro());
				nfp.setCelular(util.getLimpaTelefone(pessoa.getCelular()));
				nfp.setCep(pessoa.getCep());
				nfp.setComplemento(pessoa.getComplemento());
				nfp.setEmail(util.trataEmail(pr.getEmail()));
				nfp.setEndereco(pessoa.getEndereco());
				nfp.setInscricaoPrestador(util.getCpfCnpj(nfOrigem.getCpfCnpjPrestador()));
				nfp.setInscricaoMunicipal(pessoa.getInscricaoMunicipal());
				nfp.setNome(nfOrigem.getRazaoSocialPrestador());
				nfp.setNomeFantasia(pessoa.getNomeFantasia());
				nfp.setNotasFiscais(nf);
				nfp.setNumero(pessoa.getNumero());
				nfp.setNumeroNota(nf.getNumeroNota());
				nfp.setOptanteSimples(nf.getOptanteSimples()); // TODO resolver
				nfp.setTipoPessoa(util.getTipoPessoa(nfOrigem.getCpfCnpjPrestador()));
				nfp.setTelefone(util.getLimpaTelefone(nfOrigem.getTelefonePrestador()));
				notasFiscaisPrestadoresDao.save(nfp);
				nfp = null;

			} catch (Exception e) {
				e.printStackTrace();
				log.fillError(linha, "Nota Fiscal Prestadores", e);
			}
		}

		if (tipoNotaFilha.equals("T") && tomadores != null && tomadores.getId() != null) {
			try {
				NotasFiscaisTomadores nft = new NotasFiscaisTomadores();
				nft.setBairro(tomadores.getBairro());
				nft.setCelular(util.getLimpaTelefone(tomadores.getCelular()));
				nft.setCep(util.trataCep(tomadores.getCep()));
				nft.setComplemento(tomadores.getComplemento());
				nft.setEmail(util.trataEmail(tomadores.getEmail()));
				nft.setEndereco(tomadores.getEndereco());
				nft.setInscricaoEstadual(tomadores.getInscricaoEstadual());
				nft.setInscricaoMunicipal(tomadores.getInscricaoMunicipal());
				nft.setInscricaoPrestador(nf.getInscricaoPrestador());
				nft.setInscricaoTomador(tomadores.getInscricaoTomador());
				nft.setMunicipio(tomadores.getMunicipio());
				if (tomadores.getMunicipioIbge() != null) {
					nft.setMunicipioIbge(Long.toString(tomadores.getMunicipioIbge()));
				}
				if (util.isEmptyOrNull(nf.getNomeTomador())){
					nft.setNome(tomadores.getNome());
					nft.setNomeFantasia(tomadores.getNomeFantasia());	
				}
				else{
					nft.setNome(nf.getNomeTomador());
					nft.setNomeFantasia(nf.getNomeTomador());
				}
				
				nft.setNotasFiscais(nf);
				nft.setNumero(tomadores.getNumero());
				nft.setNumeroNota(nf.getNumeroNota());
				nft.setOptanteSimples(tomadores.getOptanteSimples());
				nft.setTipoPessoa(tomadores.getTipoPessoa());
				nft.setPorteEmpresa(tomadores.getPorteEmpresa());
				nft.setTelefone(util.getLimpaTelefone(nfOrigem.getTelefoneTomador()));
				notasFiscaisTomadoresDao.save(nft);
				nft = null;
			} catch (Exception e) {
				e.printStackTrace();
				log.fillError(linha, "Nota Fiscal Tomadores", e);
			}

		}

	}

	private PrestadoresAtividades consideraCnaePrestador(String inscricaoPrestador, BigDecimal aliquota) {
		PrestadoresAtividades pa = prestadoresAtividadesDao.findByInscricaoAliquota(inscricaoPrestador, aliquota);
		if (pa == null || pa.getId() == 0) {
			pa = prestadoresAtividadesDao.findByInscricao(inscricaoPrestador);
		}
		return pa;

	}

}
