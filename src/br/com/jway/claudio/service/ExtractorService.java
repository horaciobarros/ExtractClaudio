package br.com.jway.claudio.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.jway.claudio.dao.CompetenciasDao;
import br.com.jway.claudio.dao.Dao;
import br.com.jway.claudio.dao.EscrituracoesOrigemDao;
import br.com.jway.claudio.dao.GuiasDao;
import br.com.jway.claudio.dao.GuiasNotasFiscaisDao;
import br.com.jway.claudio.dao.MunicipiosIbgeDao;
import br.com.jway.claudio.dao.NotasFiscaisDao;
import br.com.jway.claudio.dao.NotasFiscaisSubstDao;
import br.com.jway.claudio.dao.PagamentosDao;
import br.com.jway.claudio.dao.PessoaDao;
import br.com.jway.claudio.dao.PrestadoresAtividadesDao;
import br.com.jway.claudio.dao.PrestadoresDao;
import br.com.jway.claudio.dao.PrestadoresOptanteSimplesDao;
import br.com.jway.claudio.dao.ServicosOrigemDao;
import br.com.jway.claudio.dao.TomadoresDao;
import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.entidadesOrigem.ServicosNotasFiscaisOrigem;
import br.com.jway.claudio.entidadesOrigem.ServicosOrigem;
import br.com.jway.claudio.model.Competencias;
import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.model.NotasFiscais;
import br.com.jway.claudio.model.Pessoa;
import br.com.jway.claudio.model.Prestadores;
import br.com.jway.claudio.model.Tomadores;
import br.com.jway.claudio.util.FileLog;
import br.com.jway.claudio.util.Util;

public class ExtractorService {
	public static int threadsAtivas = 0;
	public static int idNovasPessoas = 350000;
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
	private PrestadoresOptanteSimplesDao prestadoresOptanteSimpesDao = new PrestadoresOptanteSimplesDao();
	private EscrituracoesOrigemDao escrituracoesDao = new EscrituracoesOrigemDao();

	public List<String> excluiParaProcessarNivel1() {
		return Arrays.asList("GuiasNotasFiscais", "NotasFiscaisCanceladas", "NotasFiscaisCondPagamentos", "NotasFiscaisEmails", "NotasFiscaisObras",
				"NotasFiscaisPrestadores", "NotasFiscaisServicos", "NotasFiscaisSubst", "NotasFiscaisTomadores", "NotasFiscaisXml", "Pagamentos",
				"PrestadoresAtividades", "" + "PrestadoresOptanteSimples", "Guias", "Competencias", "NotasFiscais", "EscrituracoesOrigem", "ServicosOrigem",
				"Tomadores", "Prestadores", "Pessoa");

	}

	public List<String> excluiParaProcessarNivel2() {
		return Arrays.asList("GuiasNotasFiscais", "NotasFiscaisCanceladas", "NotasFiscaisCondPagamentos", "NotasFiscaisEmails", "NotasFiscaisObras",
				"NotasFiscaisPrestadores", "NotasFiscaisServicos", "NotasFiscaisSubst", "NotasFiscaisTomadores", "NotasFiscaisXml", "NotasFiscais",
				"EscrituracoesOrigem", "ServicosOrigem", "Pagamentos", "PrestadoresAtividades", "PrestadoresOptanteSimples", "Guias", "Competencias", "Tomadores");

	}

	public List<String> excluiParaProcessarNivel3() {
		return Arrays.asList("GuiasNotasFiscais", "NotasFiscaisCanceladas", "NotasFiscaisCondPagamentos", "NotasFiscaisEmails", "NotasFiscaisObras",
				"NotasFiscaisPrestadores", "NotasFiscaisServicos", "NotasFiscaisSubst", "NotasFiscaisTomadores", "NotasFiscaisXml", "NotasFiscais",
				"EscrituracoesOrigem", "ServicosOrigem", "Tomadores");
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
			br = new BufferedReader(new InputStreamReader(new FileInputStream("c:/TEMP/claudio/tratados/" + arquivoIn + ".csv"), "utf-8"));
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

		Map<String, Prestadores> mapPrestadores = prestadoresDao.findAllMapReturn();
		Map<String, Pessoa> mapPessoa = pessoaDao.findAllMapReturn();
		Map<String, EscrituracoesOrigem> mapEscrituracoesOrigem = escrituracoesOrigemDao.findAllMapReturn();

		ExecutorService executor = Executors.newFixedThreadPool(200);
		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				break;
			}

			NotaMaeThreadService notaMaeThread = new NotaMaeThreadService(linha, log, mapPrestadores, mapPessoa, mapEscrituracoesOrigem,
					mapServicosNotasFiscaisOrigem, util);
			executor.execute(notaMaeThread);
		}
		Util.pausar(5000);
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		Util.pausar(5000);
		System.out.println("Notas Fiscais finalizada - " + Util.getDataHoraAtual());
		log.close();
	}

	public List<String> lerArquivoXml(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void processaDadosContribuinte(List<String> dadosList) {

		FileLog log = new FileLog("contribuintes");

		System.out.println("Gravando contribuintes - " + Util.getDataHoraAtual());

		ContribuintesThread threadService;
		ExecutorService executor = Executors.newFixedThreadPool(200);
		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				break;
			}
			threadService = new ContribuintesThread(util, linha, log);
			executor.execute(threadService);
		}
		Util.pausar(3000);
		executor.shutdown();
		while (!executor.isTerminated()) {
			Util.pausar(5000);
		}
		System.out.println("Contribuintes finalizada - " + Util.getDataHoraAtual());
		log.close();
	}

	public void processaDadosAtividadeEconomicaContribuinte(List<String> dadosList) {

		FileLog log = new FileLog("cnae_servicos_contribuintes");
		System.out.println("Gravando cnae_servicos_contribuintes - " + Util.getDataHoraAtual());

		AtividadeContribuinteThread threadService;
		ExecutorService executor = Executors.newFixedThreadPool(200);
		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				break;
			}
			threadService = new AtividadeContribuinteThread(util, linha, log);
			executor.execute(threadService);
		}
		Util.pausar(3000);
		executor.shutdown();
		while (!executor.isTerminated()) {
			Util.pausar(5000);
		}
		System.out.println("cnae_servicos_contribuintes finalizada - " + Util.getDataHoraAtual());

		log.close();

	}

	public void processaDadosGuiasPagas(List<String> dadosList) {

	}

	public void processaDadosGuias(List<String> dadosList) {

		FileLog log = new FileLog("guias");
		System.out.println("Gravando guias - " + Util.getDataHoraAtual());

		GuiasThread threadService;
		ExecutorService executor = Executors.newFixedThreadPool(200);
		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				break;
			}
			threadService = new GuiasThread(util, linha, log);
			executor.execute(threadService);
		}
		Util.pausar(3000);
		executor.shutdown();
		while (!executor.isTerminated()) {
			Util.pausar(5000);
		}
		Util.pausar(3000);
		System.out.println("guias finalizada - " + Util.getDataHoraAtual());

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

			ServicosNotasFiscaisOrigem snf = new ServicosNotasFiscaisOrigem(arrayAux.get(0), arrayAux.get(1), arrayAux.get(2), arrayAux.get(3), arrayAux.get(4),
					arrayAux.get(5));

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
		Util.pausar(3000);

	}

	public void processaDadosGuiasNotasFiscais() {
		FileLog log = new FileLog("guias_notas_fiscais");
		
		ExecutorService executor = Executors.newFixedThreadPool(200);
		for (Guias guia : guiasDao.findAll()) {
			GuiasNotasThread thread = new GuiasNotasThread(guia, log);
			executor.execute(thread);
		}
		Util.pausar(5000);
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Notas Fiscais finalizada - " + Util.getDataHoraAtual());
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

		ExecutorService executor = Executors.newFixedThreadPool(200);
		for (NotasFiscais nf : notasFiscaisDao.findSubstitutas()) {
			NotasSubstituidasThread thread = new NotasSubstituidasThread(nf, log);
			executor.execute(thread);
		}
		Util.pausar(5000);
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Notas Fiscais finalizada - " + Util.getDataHoraAtual());

	}

	public void processaDadosServicos(List<String> dadosList) {
		FileLog log = new FileLog("servicos");
		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				break;
			}

			List<String> arrayAux = util.splitRegistroServico(linha);
			String aux = arrayAux.get(4);
			String[] cnaes = aux.split(";");

			for (String cnae : cnaes) {
				ServicosOrigem servicos = new ServicosOrigem(arrayAux.get(0), arrayAux.get(1), arrayAux.get(2).replace("\"", ""), arrayAux.get(3), cnae.trim()
						.substring(0, cnae.trim().indexOf(" ")).trim().replace("\"", ""), arrayAux.get(5));
				if (servicos != null && servicos.getCodigo() != null) {
					servicos.setCodigo(util.completarZerosEsquerda(servicos.getCodigo().replace("a", "").replace("b", "").replace(".", ""), 4));
				}
				try {
					servicosOrigemDao.save(servicos);

				} catch (Exception e) {
					log.fillError(linha, "serviços", e);
					e.printStackTrace();
				}
			}
		}

	}

	public void processaDadosEscrituracoes(List<String> dadosList) {
		System.out.println("Gravando Escriturações - " + Util.getDataHoraAtual());
		FileLog log = new FileLog("escrituracoes");

		EscrituracoesThread escritService;
		ExecutorService executor = Executors.newFixedThreadPool(200);
		for (String linha : dadosList) {
			if (linha == null || linha.trim().isEmpty()) {
				break;
			}

			escritService = new EscrituracoesThread(util, linha, log);
			executor.execute(escritService);
		}
		Util.pausar(3000);
		executor.shutdown();
		while (!executor.isTerminated()) {
			Util.pausar(5000);
		}
		Util.pausar(3000);
		System.out.println("Escriturações finalizada - " + Util.getDataHoraAtual());

	}

}
