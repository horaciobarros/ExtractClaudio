package br.com.jway.claudio.service;

import java.util.List;

import br.com.jway.claudio.dao.MunicipiosIbgeDao;
import br.com.jway.claudio.dao.PessoaDao;
import br.com.jway.claudio.dao.PrestadoresDao;
import br.com.jway.claudio.dao.PrestadoresOptanteSimplesDao;
import br.com.jway.claudio.entidadesOrigem.ContribuinteOrigem;
import br.com.jway.claudio.model.Pessoa;
import br.com.jway.claudio.model.Prestadores;
import br.com.jway.claudio.model.PrestadoresOptanteSimples;
import br.com.jway.claudio.util.FileLog;
import br.com.jway.claudio.util.Util;

public class ContribuintesThread implements Runnable{
	private Util util;
	private String linha;
	private FileLog log;
	private PessoaDao pessoaDao = new PessoaDao();
	private PrestadoresOptanteSimplesDao prestadoresOptanteSimplesDao = new PrestadoresOptanteSimplesDao();
	private MunicipiosIbgeDao municipiosIbgeDao = new MunicipiosIbgeDao();
	private PrestadoresDao prestadoresDao = new PrestadoresDao();
	
	public ContribuintesThread(Util util, String linha, FileLog log) {
		this.util = util;
		this.linha = linha;
		this.log = log;
	}
	@Override
	public void run() {
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
			String procuraPorte = util.getStringLimpa(p.getNome());
			if (procuraPorte!=null){
				if (procuraPorte.contains("LTDAME")){
					p.setPorteEmpresa("1");
				} else if (procuraPorte.contains("LTDAEPP")){
					p.setPorteEmpresa("2");
				}
			}
			if (c.getTipoDaEmpresa()!=null && c.getTipoDaEmpresa().equalsIgnoreCase("mei")) {
				p.setPorteEmpresa("5");
			}
			
			if (c.getTipoDaEmpresa() != null && c.getTipoDaEmpresa().equalsIgnoreCase("simple")) {
				p.setOptanteSimples("S");
			}
			else{
				p.setOptanteSimples("N");
			}
			
			
			p.setTipoContribuinteOrigem(c.getTipoContribuinte());
			p.setTipoDeContribuinteOrigem(c.getTipoDeContribuinte());
			p.setTipoDaEmpresaOrigem(c.getTipoDaEmpresa());
			
			p = util.trataNumerosTelefones(p);
			p = util.anulaCamposVazios(p);

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
				pr = util.trataNumerosTelefones(pr);
				pr = util.anulaCamposVazios(pr);
				prestadoresDao.save(pr);
				
				if (!util.isEmptyOrNull(p.getOptanteSimples()) && p.getOptanteSimples().equals("S")) {
					
					PrestadoresOptanteSimples po = new PrestadoresOptanteSimples();
					po.setDataEfeito(util.getStringToDateHoursMinutesSeconds(c.getDataDeCriacao()));
					po.setDataInicio(util.getStringToDateHoursMinutesSeconds(c.getDataDeCriacao()));
					po.setDescricao("Optante simples");
					po.setInscricaoPrestador(p.getCnpjCpf());
					po.setMotivo("Opção do Contribuinte");
					if (c.getTipoDaEmpresa().equalsIgnoreCase("mei")) {
						po.setMei("S");
					} else {
						po.setMei("N");
					}
					po.setOptante("S");
					po.setOrgao("M");
					po.setPrestadores(pr);
					prestadoresOptanteSimplesDao.save(po);
				}
				

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

}
