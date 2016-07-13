package br.com.jway.claudio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prestadores_atividades")
public class PrestadoresAtividades implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "prestador_id")
	private Prestadores prestadores;

	@Column(name = "inscricao_prestador")
	private String inscricaoPrestador;

	@Column(name = "iListaServicos")
	private String ilistaservicos;

	@Column(name = "iCnaes")
	private String icnaes;

	@Column(name = "aliquota")
	private BigDecimal aliquota;

	@Column(name = "dh_envio")
	private Date dhEnvio;

	@Column(name = "hash")
	private String hash;

	@Column(name = "codigo_atividade")
	private String codigoAtividade;

	public String getCodigoAtividade() {
		return codigoAtividade;
	}

	public void setCodigoAtividade(String codigoAtividade) {
		this.codigoAtividade = codigoAtividade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Prestadores getPrestadores() {
		return prestadores;
	}

	public void setPrestadores(Prestadores prestadores) {
		this.prestadores = prestadores;
	}

	public String getInscricaoPrestador() {
		return inscricaoPrestador;
	}

	public void setInscricaoPrestador(String inscricaoPrestador) {
		this.inscricaoPrestador = inscricaoPrestador;
	}

	public String getIlistaservicos() {
		return ilistaservicos;
	}

	public void setIlistaservicos(String ilistaservicos) {
		this.ilistaservicos = ilistaservicos;
	}

	public String getIcnaes() {
		return icnaes;
	}

	public void setIcnaes(String icnaes) {
		this.icnaes = icnaes;
	}

	public BigDecimal getAliquota() {
		return aliquota;
	}

	public void setAliquota(BigDecimal aliquota) {
		this.aliquota = aliquota;
	}

	public Date getDhEnvio() {
		return dhEnvio;
	}

	public void setDhEnvio(Date dhEnvio) {
		this.dhEnvio = dhEnvio;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}