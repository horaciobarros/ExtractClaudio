package br.com.jway.claudio.service;

import java.math.BigDecimal;

import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisCanceladasDao;
import br.com.jway.claudio.dao.NotasFiscaisEmailsDao;
import br.com.jway.claudio.dao.NotasFiscaisPrestadoresDao;
import br.com.jway.claudio.dao.NotasFiscaisServicosDao;
import br.com.jway.claudio.dao.NotasFiscaisTomadoresDao;
import br.com.jway.claudio.dao.PrestadoresAtividadesDao;
import br.com.jway.claudio.entidadesOrigem.NotasFiscaisOrigem;
import br.com.jway.claudio.entidadesOrigem.NotasFiscaisOrigem;
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

public class NotasThreadService implements Runnable {
	private Prestadores pr;
	private NotasFiscais nf;
	private NotasFiscaisOrigem nfOrigem;
	private FileLog log;
	private String linha;
	private String tipoNotaFilha;
	private Guias guia;
	private Util util = new Util();
	private NotasFiscaisServicosDao notasFiscaisServicosDao = new NotasFiscaisServicosDao();
	private NotasFiscaisCanceladasDao notasFiscaisCanceladasDao = new NotasFiscaisCanceladasDao();
	private NotasFiscaisEmailsDao notasFiscaisEmailsDao = new NotasFiscaisEmailsDao();
	private NotasFiscaisPrestadoresDao notasFiscaisPrestadoresDao = new NotasFiscaisPrestadoresDao();
	private GuiasNotasFiscaisDao guiasNotasFiscaisDao = new GuiasNotasFiscaisDao();
	private Tomadores tomadores;
	private NotasFiscaisTomadoresDao notasFiscaisTomadoresDao = new NotasFiscaisTomadoresDao();
	private PrestadoresAtividadesDao prestadoresAtividadesDao = new PrestadoresAtividadesDao();
	private Pessoa pessoa;


	public NotasThreadService(Prestadores pr, NotasFiscais nf, NotasFiscaisOrigem dlp, FileLog log, String linha,
			String tipoNotaFilha, Guias guia, Tomadores tomadores, Pessoa pessoa) {
		this.pessoa = pessoa;
		this.pr = pr;
		this.nf = nf;
		this.nfOrigem = dlp;
		this.log = log;
		this.linha = linha;
		this.tipoNotaFilha = tipoNotaFilha;
		this.guia = guia;
		this.tomadores = tomadores;

	}

	@Override
	public void run() {
		if (tipoNotaFilha.equals("S")) { // serviços
			try {

				NotasFiscaisServicos nfs = new NotasFiscaisServicos();
				nfs.setInscricaoPrestador(util.getCpfCnpj(nfOrigem.getCpfCnpjPrestador()));
				nfs.setNumeroNota(Long.valueOf(nf.getNumeroNota()));
				nfs.setMunicipioIbge(util.CODIGO_IBGE_CLAUDIO);
				
				if (nfs.getItemListaServico() == null || nfs.getItemListaServico().isEmpty()) {
					nfs.setItemListaServico("1401");
				}
				nfs.setDescricao(nfOrigem.getDescricaoDoServico());
				
				nfs.setAliquota(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getAliquota())));
				nfs.setValorServico(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getValorDosServicosPrestados())));
				nfs.setQuantidade(BigDecimal.valueOf(1));
				nfs.setValorDeducao(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getDeducoes())));
				nfs.setValorBaseCalculo(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getValorDosServicosPrestados())));
				nfs.setValorIss(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getValorDoIssqnDevido())));
				nfs.setNotasFiscais(nf);
				nfs.setValorUnitario(BigDecimal.valueOf(Double.parseDouble(nfOrigem.getValorDosServicosPrestados())));
				if (nfs.getAliquota().compareTo(BigDecimal.ZERO) == 0) {
					nfs.setAliquota(BigDecimal.valueOf(1));
				}
				notasFiscaisServicosDao.save(nfs);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (tipoNotaFilha.equals("C")) { // canceladas
			try {
				NotasFiscaisCanceladas nfc = new NotasFiscaisCanceladas();
				if (nfc.getDatahoracancelamento().getTime() < nf.getDataHoraEmissao().getTime()) {
					nfc.setDatahoracancelamento(util.getStringToDateHoursMinutes(nfOrigem.getDataEmissaoRps()));
				}
				nfc.setInscricaoPrestador(nfOrigem.getCpfCnpjPrestador());
				nfc.setNumeroNota(Long.valueOf(nf.getNumeroNota()));
				if (util.isEmptyOrNull(nfc.getMotivo())) {
					nfc.setMotivo("Dados incorretos");
				}
				nfc.setNotasFiscais(nf);
				notasFiscaisCanceladasDao.save(nfc);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (tipoNotaFilha.equals("E") && pr.getEmail() != null && !pr.getEmail().isEmpty()) { // email
			try {
				NotasFiscaisEmails nfe = new NotasFiscaisEmails();
				nfe.setEmail(pr.getEmail());
				nfe.setInscricaoPrestador(util.getCpfCnpj(nfOrigem.getCpfCnpjPrestador()));
				nfe.setNotasFiscais(nf);
				nfe.setNumeroNota(Long.valueOf(nf.getNumeroNota()));
				notasFiscaisEmailsDao.save(nfe);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (tipoNotaFilha.equals("P")) { // prestadores
			try {
				NotasFiscaisPrestadores nfp = new NotasFiscaisPrestadores();
				nfp.setBairro(pessoa.getBairro());
				nfp.setCelular(util.getLimpaTelefone(nfOrigem.getTelefonePrestador()));
				nfp.setCep(pessoa.getCep());
				nfp.setComplemento(pessoa.getComplemento());
				nfp.setEmail(pr.getEmail());
				nfp.setEndereco(pessoa.getEndereco());
				nfp.setInscricaoPrestador(util.getCpfCnpj(nfOrigem.getCpfCnpjPrestador()));
				nfp.setNome(nfOrigem.getRazaoSocialPrestador());
				nfp.setNomeFantasia(pessoa.getNomeFantasia());
				nfp.setNotasFiscais(nf);
				nfp.setNumero(pessoa.getNumero());
				nfp.setNumeroNota(nf.getNumeroNota());
				nfp.setOptanteSimples("N"); // TODO resolver
				nfp.setTipoPessoa(util.getTipoPessoa(nfOrigem.getCpfCnpjPrestador()));
				notasFiscaisPrestadoresDao.save(nfp);

			} catch (Exception e) {
				e.printStackTrace();
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
				nft.setNome(tomadores.getNome());
				nft.setNomeFantasia(tomadores.getNomeFantasia());
				if (nft.getNomeFantasia() == null) {
					nft.setNomeFantasia(tomadores.getNome());
				}
				nft.setNotasFiscais(nf);
				nft.setNumero(tomadores.getNumero());
				nft.setNumeroNota(nf.getNumeroNota());
				nft.setOptanteSimples(tomadores.getOptanteSimples());
				nft.setTipoPessoa(tomadores.getTipoPessoa());
				notasFiscaisTomadoresDao.save(nft);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
