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
@Table(name = "guias")
public class Guias implements Serializable {

	@Override
	public String toString() {
		return "Guias [id=" + id + ", prestadores=" + prestadores.getInscricaoPrestador() + ", competencias=" + competencias.getDescricao()
				+ ", inscricaoPrestador=" + inscricaoPrestador + ", numeroGuia=" + numeroGuia + ", situacao=" + situacao
				+ ", dataVencimento=" + dataVencimento + ", valorDesconto=" + valorDesconto + ", valorTaxaExpediente="
				+ valorTaxaExpediente + ", valorGuia=" + valorGuia + ", valorImposto=" + valorImposto + ", tipo=" + tipo
				+ ", integrarGuia=" + integrarGuia + ", dhEnvio=" + dhEnvio + ", hash=" + hash + ", idGuiaRecolhimento="
				+ idGuiaRecolhimento + ", idNotasFiscais=" + idNotasFiscais + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "prestador_id")
	private Prestadores prestadores;

	@ManyToOne
	@JoinColumn(name = "competencia_id")
	private Competencias competencias;

	@Column(name = "inscricao_prestador")
	private String inscricaoPrestador;

	@Column(name = "numero_guia")
	private Long numeroGuia;

	@Column(name = "situacao")
	private String situacao;

	@Column(name = "data_vencimento")
	private Date dataVencimento;

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;

	@Column(name = "valor_taxa_expediente")
	private BigDecimal valorTaxaExpediente;

	@Column(name = "valor_guia")
	private BigDecimal valorGuia;

	@Column(name = "valor_imposto")
	private BigDecimal valorImposto;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "integrar_guia")
	private String integrarGuia;

	@Column(name = "dh_envio")
	private Date dhEnvio;

	@Column(name = "hash")
	private String hash;

	@Column(name = "id_guia_recolhimento")
	private String idGuiaRecolhimento;
	
	@Column(name = "numero_guia_origem")
	private String numeroGuiaOrigem;
	
	public String getIdNotasFiscais() {
		return idNotasFiscais;
	}

	public void setIdNotasFiscais(String idNotasFiscais) {
		this.idNotasFiscais = idNotasFiscais;
	}

	@Column(name = "idNotasFiscais")
	private String idNotasFiscais;

	public String getIdGuiaRecolhimento() {
		return idGuiaRecolhimento;
	}

	public void setIdGuiaRecolhimento(String idGuiaRecolhimento) {
		this.idGuiaRecolhimento = idGuiaRecolhimento;
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

	public Competencias getCompetencias() {
		return competencias;
	}

	public void setCompetencias(Competencias competencias) {
		this.competencias = competencias;
	}

	public String getInscricaoPrestador() {
		return inscricaoPrestador;
	}

	public void setInscricaoPrestador(String inscricaoPrestador) {
		this.inscricaoPrestador = inscricaoPrestador;
	}

	public Long getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(Long numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTaxaExpediente() {
		return valorTaxaExpediente;
	}

	public void setValorTaxaExpediente(BigDecimal valorTaxaExpediente) {
		this.valorTaxaExpediente = valorTaxaExpediente;
	}

	public BigDecimal getValorGuia() {
		return valorGuia;
	}

	public void setValorGuia(BigDecimal valorGuia) {
		this.valorGuia = valorGuia;
	}

	public BigDecimal getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIntegrarGuia() {
		return integrarGuia;
	}

	public void setIntegrarGuia(String integrarGuia) {
		this.integrarGuia = integrarGuia;
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

	public String getNumeroGuiaOrigem() {
		return numeroGuiaOrigem;
	}

	public void setNumeroGuiaOrigem(String numeroGuiaOrigem) {
		this.numeroGuiaOrigem = numeroGuiaOrigem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guias other = (Guias) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}