package br.com.jway.claudio.entidadesOrigem;

public class EscrituracoesOrigem {
	
	private String	id;
	private String  idNotaFiscal;
	private String  idContribuinte;
	private String  idTomador;
	private String  status;
	private String  dataDeCriacao;
	private String  idEscrituracaoSubstituida;
	private String  autorEmail;
	private String  autorNome;
	private String  autorCpf;
	private String  cpfCpnjContribuinte;
	private String  numeroNotaFiscal;
	private String  serie;
	private String  situacao;
	private String  dataDaEscrituracao;
	private String  aliquotaNotaFiscal;
	private String  deducoesEDescontosNotaFiscal;
	private String  valorDaNotaFiscal;
	private String  tipoDaNotaFiscal;
	private String  escrituracaoAvulsa;
	private String  servicoPrestadoPorEmpresaDoExterior;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdNotaFiscal() {
		return idNotaFiscal;
	}
	public void setIdNotaFiscal(String idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}
	public String getIdContribuinte() {
		return idContribuinte;
	}
	public void setIdContribuinte(String idContribuinte) {
		this.idContribuinte = idContribuinte;
	}
	public String getIdTomador() {
		return idTomador;
	}
	public void setIdTomador(String idTomador) {
		this.idTomador = idTomador;
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
	public String getIdEscrituracaoSubstituida() {
		return idEscrituracaoSubstituida;
	}
	public void setIdEscrituracaoSubstituida(String idEscrituracaoSubstituida) {
		this.idEscrituracaoSubstituida = idEscrituracaoSubstituida;
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
	public String getCpfCpnjContribuinte() {
		return cpfCpnjContribuinte;
	}
	public void setCpfCpnjContribuinte(String cpfCpnjContribuinte) {
		this.cpfCpnjContribuinte = cpfCpnjContribuinte;
	}
	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}
	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDataDaEscrituracao() {
		return dataDaEscrituracao;
	}
	public void setDataDaEscrituracao(String dataDaEscrituracao) {
		this.dataDaEscrituracao = dataDaEscrituracao;
	}
	public String getAliquotaNotaFiscal() {
		return aliquotaNotaFiscal;
	}
	public void setAliquotaNotaFiscal(String aliquotaNotaFiscal) {
		this.aliquotaNotaFiscal = aliquotaNotaFiscal;
	}
	public String getDeducoesEDescontosNotaFiscal() {
		return deducoesEDescontosNotaFiscal;
	}
	public void setDeducoesEDescontosNotaFiscal(String deducoesEDescontosNotaFiscal) {
		this.deducoesEDescontosNotaFiscal = deducoesEDescontosNotaFiscal;
	}
	public String getValorDaNotaFiscal() {
		return valorDaNotaFiscal;
	}
	public void setValorDaNotaFiscal(String valorDaNotaFiscal) {
		this.valorDaNotaFiscal = valorDaNotaFiscal;
	}
	public String getTipoDaNotaFiscal() {
		return tipoDaNotaFiscal;
	}
	public void setTipoDaNotaFiscal(String tipoDaNotaFiscal) {
		this.tipoDaNotaFiscal = tipoDaNotaFiscal;
	}
	public String getEscrituracaoAvulsa() {
		return escrituracaoAvulsa;
	}
	public void setEscrituracaoAvulsa(String escrituracaoAvulsa) {
		this.escrituracaoAvulsa = escrituracaoAvulsa;
	}
	public String getServicoPrestadoPorEmpresaDoExterior() {
		return servicoPrestadoPorEmpresaDoExterior;
	}
	public void setServicoPrestadoPorEmpresaDoExterior(String servicoPrestadoPorEmpresaDoExterior) {
		this.servicoPrestadoPorEmpresaDoExterior = servicoPrestadoPorEmpresaDoExterior;
	}
	public EscrituracoesOrigem(String id, String idNotaFiscal, String idContribuinte, String idTomador, String status,
			String dataDeCriacao, String idEscrituracaoSubstituida, String autorEmail, String autorNome,
			String autorCpf, String cpfCpnjContribuinte, String numeroNotaFiscal, String serie, String situacao,
			String dataDaEscrituracao, String aliquotaNotaFiscal, String deducoesEDescontosNotaFiscal,
			String valorDaNotaFiscal, String tipoDaNotaFiscal, String escrituracaoAvulsa,
			String servicoPrestadoPorEmpresaDoExterior) {
		super();
		this.id = id;
		this.idNotaFiscal = idNotaFiscal;
		this.idContribuinte = idContribuinte;
		this.idTomador = idTomador;
		this.status = status;
		this.dataDeCriacao = dataDeCriacao;
		this.idEscrituracaoSubstituida = idEscrituracaoSubstituida;
		this.autorEmail = autorEmail;
		this.autorNome = autorNome;
		this.autorCpf = autorCpf;
		this.cpfCpnjContribuinte = cpfCpnjContribuinte;
		this.numeroNotaFiscal = numeroNotaFiscal;
		this.serie = serie;
		this.situacao = situacao;
		this.dataDaEscrituracao = dataDaEscrituracao;
		this.aliquotaNotaFiscal = aliquotaNotaFiscal;
		this.deducoesEDescontosNotaFiscal = deducoesEDescontosNotaFiscal;
		this.valorDaNotaFiscal = valorDaNotaFiscal;
		this.tipoDaNotaFiscal = tipoDaNotaFiscal;
		this.escrituracaoAvulsa = escrituracaoAvulsa;
		this.servicoPrestadoPorEmpresaDoExterior = servicoPrestadoPorEmpresaDoExterior;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
         

}
