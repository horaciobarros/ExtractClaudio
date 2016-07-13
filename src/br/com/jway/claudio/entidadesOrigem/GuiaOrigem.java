package br.com.jway.claudio.entidadesOrigem;

public class GuiaOrigem {
	
	private String id;
	private String idContribuinte;
	private String dataDeVencimento;
	private String valor;
	private String multa;
	private String juros;
	private String taxaDeExpediente;
	private String valorTotal;
	private String status;
	private String dataDeCriacao;
	private String competencia;
	private String issRetidoTributado;
	private String dataDePagamento;
	private String autorEmail;
	private String autorNome;
	private String autorCpf;
	private String notaFiscalAvulsa;
	private String idNotasFiscais;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdContribuinte() {
		return idContribuinte;
	}
	public void setIdContribuinte(String idContribuinte) {
		this.idContribuinte = idContribuinte;
	}
	public String getDataDeVencimento() {
		return dataDeVencimento;
	}
	public void setDataDeVencimento(String dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getMulta() {
		return multa;
	}
	public void setMulta(String multa) {
		this.multa = multa;
	}
	public String getJuros() {
		return juros;
	}
	public void setJuros(String juros) {
		this.juros = juros;
	}
	public String getTaxaDeExpediente() {
		return taxaDeExpediente;
	}
	public void setTaxaDeExpediente(String taxaDeExpediente) {
		this.taxaDeExpediente = taxaDeExpediente;
	}
	public String getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(String dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public String getCompetencia() {
		return competencia;
	}
	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	public String getIssRetidoTributado() {
		return issRetidoTributado;
	}
	public void setIssRetidoTributado(String issRetidoTributado) {
		this.issRetidoTributado = issRetidoTributado;
	}
	public String getDataDePagamento() {
		return dataDePagamento;
	}
	public void setDataDePagamento(String dataDePagamento) {
		this.dataDePagamento = dataDePagamento;
	}
	public String getAutorEmail() {
		return autorEmail;
	}
	public void setAutorEmail(String autorEmail) {
		this.autorEmail = autorEmail;
	}
	public String getAutorNome() {
		return autorNome;
	}
	public void setAutorNome(String autorNome) {
		this.autorNome = autorNome;
	}
	public String getAutorCpf() {
		return autorCpf;
	}
	public void setAutorCpf(String autorCpf) {
		this.autorCpf = autorCpf;
	}
	public String getNotaFiscalAvulsa() {
		return notaFiscalAvulsa;
	}
	public void setNotaFiscalAvulsa(String notaFiscalAvulsa) {
		this.notaFiscalAvulsa = notaFiscalAvulsa;
	}
	public String getIdNotasFiscais() {
		return idNotasFiscais;
	}
	public void setIdNotasFiscais(String idNotasFiscais) {
		this.idNotasFiscais = idNotasFiscais;
	}
	public GuiaOrigem(String id, String idContribuinte, String dataDeVencimento, String valor, String multa,
			String juros, String taxaDeExpediente, String valorTotal, String status, String dataDeCriacao,
			String competencia, String issRetidoTributado, String dataDePagamento, String autorEmail, String autorNome,
			String autorCpf, String notaFiscalAvulsa, String idNotasFiscais) {
		super();
		this.id = id;
		this.idContribuinte = idContribuinte;
		this.dataDeVencimento = dataDeVencimento;
		this.valor = valor;
		this.multa = multa;
		this.juros = juros;
		this.taxaDeExpediente = taxaDeExpediente;
		this.valorTotal = valorTotal;
		this.status = status;
		this.dataDeCriacao = dataDeCriacao;
		this.competencia = competencia;
		this.issRetidoTributado = issRetidoTributado;
		this.dataDePagamento = dataDePagamento;
		this.autorEmail = autorEmail;
		this.autorNome = autorNome;
		this.autorCpf = autorCpf;
		this.notaFiscalAvulsa = notaFiscalAvulsa;
		this.idNotasFiscais = idNotasFiscais;
	}


}
