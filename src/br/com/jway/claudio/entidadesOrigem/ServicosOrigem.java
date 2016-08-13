package br.com.jway.claudio.entidadesOrigem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servicos_origem")
public class ServicosOrigem {
	
	@Id
	@Column(name = "id")
	private long id;
	private String codigo;
	private String nome;
	private String aliquota;
	private String cnaes;
	private String dataDeCriacao;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
		this.id = Long.parseLong(id);
		this.codigo = codigo;
		this.nome = nome;
		this.aliquota = aliquota;
		this.cnaes = cnaes;
		this.dataDeCriacao = dataDeCriacao;
	}		
	
	public ServicosOrigem() {
		
	}


}
