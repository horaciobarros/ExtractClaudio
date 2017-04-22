package br.com.jway.claudio.entidadesOrigem;

public class DeParaServicos {
	
	private String cnpj;
	private String codigoAtual;
	private String codigoNovo;
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCodigoAtual() {
		return codigoAtual;
	}
	public void setCodigoAtual(String codigoAtual) {
		this.codigoAtual = codigoAtual;
	}
	public String getCodigoNovo() {
		return codigoNovo;
	}
	public void setCodigoNovo(String codigoNovo) {
		this.codigoNovo = codigoNovo;
	}
	public DeParaServicos(String cnpj, String codigoAtual, String codigoNovo) {
		this.cnpj = cnpj;
		this.codigoAtual = codigoAtual;
		this.codigoNovo = codigoNovo;
	}
}
