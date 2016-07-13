package br.com.jway.claudio.entidadesOrigem;

public class CnaeServicosContribuinte {

	private String id;
	private String principal;
	private String idContribuinte;
	private String idServico;
	private String servicoCodigo;
	private String servico;
	private String idCnae;
	private String cnaeCodigo;
	private String cnae;
	private String aliquota;
	private String tipoDaAliquota;
	private String textoDaLegislacao;
	private String dataDeCriacao;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getIdContribuinte() {
		return idContribuinte;
	}
	public void setIdContribuinte(String idContribuinte) {
		this.idContribuinte = idContribuinte;
	}
	public String getIdServico() {
		return idServico;
	}
	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}
	public String getServicoCodigo() {
		return servicoCodigo;
	}
	public void setServicoCodigo(String servicoCodigo) {
		this.servicoCodigo = servicoCodigo;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public String getIdCnae() {
		return idCnae;
	}
	public void setIdCnae(String idCnae) {
		this.idCnae = idCnae;
	}
	public String getCnaeCodigo() {
		return cnaeCodigo;
	}
	public void setCnaeCodigo(String cnaeCodigo) {
		this.cnaeCodigo = cnaeCodigo;
	}
	public String getCnae() {
		return cnae;
	}
	public void setCnae(String cnae) {
		this.cnae = cnae;
	}
	public String getAliquota() {
		return aliquota;
	}
	public void setAliquota(String aliquota) {
		this.aliquota = aliquota;
	}
	public String getTipoDaAliquota() {
		return tipoDaAliquota;
	}
	public void setTipoDaAliquota(String tipoDaAliquota) {
		this.tipoDaAliquota = tipoDaAliquota;
	}
	public String getTextoDaLegislacao() {
		return textoDaLegislacao;
	}
	public void setTextoDaLegislacao(String textoDaLegislacao) {
		this.textoDaLegislacao = textoDaLegislacao;
	}
	public String getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(String dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public CnaeServicosContribuinte(String id, String principal, String idContribuinte, String idServico,
			String servicoCodigo, String servico, String idCnae, String cnaeCodigo, String cnae, String aliquota,
			String tipoDaAliquota, String textoDaLegislacao, String dataDeCriacao) {
		super();
		this.id = id;
		this.principal = principal;
		this.idContribuinte = idContribuinte;
		this.idServico = idServico;
		this.servicoCodigo = servicoCodigo;
		this.servico = servico;
		this.idCnae = idCnae;
		this.cnaeCodigo = cnaeCodigo;
		this.cnae = cnae;
		this.aliquota = aliquota;
		this.tipoDaAliquota = tipoDaAliquota;
		this.textoDaLegislacao = textoDaLegislacao;
		this.dataDeCriacao = dataDeCriacao;
	}

	

}
