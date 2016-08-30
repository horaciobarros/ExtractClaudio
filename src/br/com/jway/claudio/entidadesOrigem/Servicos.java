package br.com.jway.claudio.entidadesOrigem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servicos")
public class Servicos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	private String codigo;
	private String nome;
	private String aliquota;
	private String cnaes;
	@Column(name = "data_de_criacao")
	private String dataDeCriacao;
	@Column(name = "id_origem")
	private long idOrigem;
	
	
	public long getIdOrigem() {
		return idOrigem;
	}
	public void setIdOrigem(long idOrigem) {
		this.idOrigem = idOrigem;
	}
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
	

}
