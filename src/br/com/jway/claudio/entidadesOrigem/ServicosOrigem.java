package br.com.jway.claudio.entidadesOrigem;

public class ServicosOrigem {
	
	private String id;
	private String codigo;
	private String nome;
	private String aliquota;
	private String cnaes;
	private String dataDeCriacao;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAliquota() {
		return aliquota;
	}
	public void setAliquota(String aliquota) {
		this.aliquota = aliquota;
	}
	public String getCnaes() {
		return cnaes;
	}
	public void setCnaes(String cnaes) {
		this.cnaes = cnaes;
	}
	public String getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(String dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public ServicosOrigem(String id, String codigo, String nome, String aliquota, String cnaes, String dataDeCriacao) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.aliquota = aliquota;
		this.cnaes = cnaes;
		this.dataDeCriacao = dataDeCriacao;
	}		
	
	


}
