package br.com.jway.claudio.entidadesOrigem;

public class ServicosNotasFiscaisOrigem {

	private String id;
	private String idNotaFiscal;
	private String idServico;
	private String valor;
	private String aliquota;
	private String dataDeCriacao;
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
	public String getIdServico() {
		return idServico;
	}
	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getAliquota() {
		return aliquota;
	}
	public void setAliquota(String aliquota) {
		this.aliquota = aliquota;
	}
	public String getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(String dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public ServicosNotasFiscaisOrigem(String id, String idNotaFiscal, String idServico, String valor, String aliquota,
			String dataDeCriacao) {
		super();
		this.id = id;
		this.idNotaFiscal = idNotaFiscal;
		this.idServico = idServico;
		this.valor = valor;
		this.aliquota = aliquota;
		this.dataDeCriacao = dataDeCriacao;
	}

	
}
