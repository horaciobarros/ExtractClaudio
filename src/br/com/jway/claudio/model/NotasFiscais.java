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
import javax.persistence.Transient;

@Entity
@Table(name = "notas_fiscais")
public class NotasFiscais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "prestador_id")
	private Prestadores prestadores;

	@Column(name = "numero_nota")
	private Long numeroNota;

	@Column(name = "inscricao_prestador")
	private String inscricaoPrestador;

	@Column(name = "situacao")
	private String situacao;

	@Column(name = "nome_prestador")
	private String nomePrestador;

	@Column(name = "situacao_tributaria")
	private String situacaoTributaria;

	@Column(name = "data_hora_emissao")
	private Date dataHoraEmissao;

	@Column(name = "numero_verificacao")
	private String numeroVerificacao;

	@Column(name = "natureza_operacao")
	private String naturezaOperacao;

	@Column(name = "numero_rps")
	private Long numeroRps;

	@Column(name = "serie_rps")
	private String serieRps;

	@Column(name = "optante_simples")
	private String optanteSimples;

	@Column(name = "data_hora_rps")
	private Date dataHoraRps;

	@Column(name = "valor_pis_pasep")
	private BigDecimal valorPisPasep;

	@Column(name = "valor_cofins")
	private BigDecimal valorCofins;

	@Column(name = "valor_csll")
	private BigDecimal valorCsll;

	@Column(name = "valor_inss")
	private BigDecimal valorInss;

	@Column(name = "valor_ir")
	private BigDecimal valorIr;

	@Column(name = "valor_outras_retencoes")
	private BigDecimal valorOutrasRetencoes;

	@Column(name = "aliquota_pis")
	private BigDecimal aliquotaPis;

	@Column(name = "aliquota_cofins")
	private BigDecimal aliquotaCofins;

	@Column(name = "aliquota_inss")
	private BigDecimal aliquotaInss;

	@Column(name = "aliquota_ir")
	private BigDecimal aliquotaIr;

	@Column(name = "aliquota_csll")
	private BigDecimal aliquotaCsll;

	@Column(name = "aliquota_outras")
	private BigDecimal aliquotaOutras;

	@Column(name = "valor_liquido")
	private BigDecimal valorLiquido;

	@Column(name = "valor_total_servico")
	private BigDecimal valorTotalServico;

	@Column(name = "valor_total_base_calculo")
	private BigDecimal valorTotalBaseCalculo;

	@Column(name = "valor_total_deducao")
	private BigDecimal valorTotalDeducao;

	@Column(name = "valor_total_desc_condicinado")
	private BigDecimal valorTotalDescCondicinado;

	@Column(name = "valor_total_desc_incondicinado")
	private BigDecimal valorTotalDescIncondicinado;

	@Column(name = "valor_total_iss")
	private BigDecimal valorTotalIss;

	@Column(name = "valor_total_iss_optante")
	private BigDecimal valorTotalIssOptante;

	@Column(name = "inscricao_tomador")
	private String inscricaoTomador;

	@Column(name = "nome_tomador")
	private String nomeTomador;

	@Column(name = "outras_infomacoes")
	private String outrasInfomacoes;

	@Column(name = "servico_prestado_fora_pais")
	private String servicoPrestadoForaPais;

	@Column(name = "dh_envio")
	private Date dhEnvio;

	@Column(name = "hash")
	private String hash;

	@Column(name = "situacao_original")
	private String situacaoOriginal;
	
	@Column(name = "id_origem")
	private Long idOrigem;

	public Long getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(Long idOrigem) {
		this.idOrigem = idOrigem;
	}

	public String getSituacaoOriginal() {
		return situacaoOriginal;
	}

	public void setSituacaoOriginal(String situacaoAux) {
		this.situacaoOriginal = situacaoAux;
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

	public Long getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(Long numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getInscricaoPrestador() {
		return inscricaoPrestador;
	}

	public void setInscricaoPrestador(String inscricaoPrestador) {
		this.inscricaoPrestador = inscricaoPrestador;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getNomePrestador() {
		return nomePrestador;
	}

	public void setNomePrestador(String nomePrestador) {
		this.nomePrestador = nomePrestador;
	}

	public String getSituacaoTributaria() {
		return situacaoTributaria;
	}

	public void setSituacaoTributaria(String situacaoTributaria) {
		this.situacaoTributaria = situacaoTributaria;
	}

	public Date getDataHoraEmissao() {
		return dataHoraEmissao;
	}

	public void setDataHoraEmissao(Date dataHoraEmissao) {
		this.dataHoraEmissao = dataHoraEmissao;
	}

	public String getNumeroVerificacao() {
		return numeroVerificacao;
	}

	public void setNumeroVerificacao(String numeroVerificacao) {
		this.numeroVerificacao = numeroVerificacao;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}

	public Long getNumeroRps() {
		return numeroRps;
	}

	public void setNumeroRps(Long numeroRps) {
		this.numeroRps = numeroRps;
	}

	public String getSerieRps() {
		return serieRps;
	}

	public void setSerieRps(String serieRps) {
		this.serieRps = serieRps;
	}

	public String getOptanteSimples() {
		return optanteSimples;
	}

	public void setOptanteSimples(String optanteSimples) {
		this.optanteSimples = optanteSimples;
	}

	public Date getDataHoraRps() {
		return dataHoraRps;
	}

	public void setDataHoraRps(Date dataHoraRps) {
		this.dataHoraRps = dataHoraRps;
	}

	public BigDecimal getValorPisPasep() {
		return valorPisPasep;
	}

	public void setValorPisPasep(BigDecimal valorPisPasep) {
		this.valorPisPasep = valorPisPasep;
	}

	public BigDecimal getValorCofins() {
		return valorCofins;
	}

	public void setValorCofins(BigDecimal valorCofins) {
		this.valorCofins = valorCofins;
	}

	public BigDecimal getValorCsll() {
		return valorCsll;
	}

	public void setValorCsll(BigDecimal valorCsll) {
		this.valorCsll = valorCsll;
	}

	public BigDecimal getValorInss() {
		return valorInss;
	}

	public void setValorInss(BigDecimal valorInss) {
		this.valorInss = valorInss;
	}

	public BigDecimal getValorIr() {
		return valorIr;
	}

	public void setValorIr(BigDecimal valorIr) {
		this.valorIr = valorIr;
	}

	public BigDecimal getValorOutrasRetencoes() {
		return valorOutrasRetencoes;
	}

	public void setValorOutrasRetencoes(BigDecimal valorOutrasRetencoes) {
		this.valorOutrasRetencoes = valorOutrasRetencoes;
	}

	public BigDecimal getAliquotaPis() {
		return aliquotaPis;
	}

	public void setAliquotaPis(BigDecimal aliquotaPis) {
		this.aliquotaPis = aliquotaPis;
	}

	public BigDecimal getAliquotaCofins() {
		return aliquotaCofins;
	}

	public void setAliquotaCofins(BigDecimal aliquotaCofins) {
		this.aliquotaCofins = aliquotaCofins;
	}

	public BigDecimal getAliquotaInss() {
		return aliquotaInss;
	}

	public void setAliquotaInss(BigDecimal aliquotaInss) {
		this.aliquotaInss = aliquotaInss;
	}

	public BigDecimal getAliquotaIr() {
		return aliquotaIr;
	}

	public void setAliquotaIr(BigDecimal aliquotaIr) {
		this.aliquotaIr = aliquotaIr;
	}

	public BigDecimal getAliquotaCsll() {
		return aliquotaCsll;
	}

	public void setAliquotaCsll(BigDecimal aliquotaCsll) {
		this.aliquotaCsll = aliquotaCsll;
	}

	public BigDecimal getAliquotaOutras() {
		return aliquotaOutras;
	}

	public void setAliquotaOutras(BigDecimal aliquotaOutras) {
		this.aliquotaOutras = aliquotaOutras;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public BigDecimal getValorTotalServico() {
		return valorTotalServico;
	}

	public void setValorTotalServico(BigDecimal valorTotalServico) {
		this.valorTotalServico = valorTotalServico;
	}

	public BigDecimal getValorTotalBaseCalculo() {
		return valorTotalBaseCalculo;
	}

	public void setValorTotalBaseCalculo(BigDecimal valorTotalBaseCalculo) {
		this.valorTotalBaseCalculo = valorTotalBaseCalculo;
	}

	public BigDecimal getValorTotalDeducao() {
		return valorTotalDeducao;
	}

	public void setValorTotalDeducao(BigDecimal valorTotalDeducao) {
		this.valorTotalDeducao = valorTotalDeducao;
	}

	public BigDecimal getValorTotalDescCondicinado() {
		return valorTotalDescCondicinado;
	}

	public void setValorTotalDescCondicinado(BigDecimal valorTotalDescCondicinado) {
		this.valorTotalDescCondicinado = valorTotalDescCondicinado;
	}

	public BigDecimal getValorTotalDescIncondicinado() {
		return valorTotalDescIncondicinado;
	}

	public void setValorTotalDescIncondicinado(BigDecimal valorTotalDescIncondicinado) {
		this.valorTotalDescIncondicinado = valorTotalDescIncondicinado;
	}

	public BigDecimal getValorTotalIss() {
		return valorTotalIss;
	}

	public void setValorTotalIss(BigDecimal valorTotalIss) {
		this.valorTotalIss = valorTotalIss;
	}

	public BigDecimal getValorTotalIssOptante() {
		return valorTotalIssOptante;
	}

	public void setValorTotalIssOptante(BigDecimal valorTotalIssOptante) {
		this.valorTotalIssOptante = valorTotalIssOptante;
	}

	public String getInscricaoTomador() {
		return inscricaoTomador;
	}

	public void setInscricaoTomador(String inscricaoTomador) {
		this.inscricaoTomador = inscricaoTomador;
	}

	public String getNomeTomador() {
		return nomeTomador;
	}

	public void setNomeTomador(String nomeTomador) {
		this.nomeTomador = nomeTomador;
	}

	public String getOutrasInfomacoes() {
		return outrasInfomacoes;
	}

	public void setOutrasInfomacoes(String outrasInfomacoes) {
		this.outrasInfomacoes = outrasInfomacoes;
	}

	public String getServicoPrestadoForaPais() {
		return servicoPrestadoForaPais;
	}

	public void setServicoPrestadoForaPais(String servicoPrestadoForaPais) {
		this.servicoPrestadoForaPais = servicoPrestadoForaPais;
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