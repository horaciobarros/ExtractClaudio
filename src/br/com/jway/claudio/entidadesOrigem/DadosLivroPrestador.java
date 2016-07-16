package br.com.jway.claudio.entidadesOrigem;

public class DadosLivroPrestador {
	/*
	 * id_codigo|nosso_numero|numero_nota|serie_nota|tipo_documento|data_emissao|codigo_verificacao|natureza_operacao
	 * |regime_tributacao|optante_pelo_simples_nacional|status_nota|data_cancelamento|motivo_cancelamento|data_competencia
	 * |outras_informacoes|codigo_atividade_municipal|codigo_cnae|discriminacao_servico|valor_servico|valor_deducao
	 * |valor_pis|valor_cofins|valor_inss|valor_ir|valor_csll|tipo_retencao|valor_iss|valor_outras_retencoes
	 * |valor_base_calculo|valor_aliquota|valor_total_nfse|valor_desconto_incondicionado|valor_desconto_condicionado
	 * |cnpj_prestador|inscricao_municipal_prestador|inscricao_estadual_prestador|razao_social_prestador
	 * |nome_fantasia_prestador|endereco_prestador|endereco_numero_prestador|endereco_complemento_prestador
	 * |endereco_bairro_prestador|cidade_prestador|uf_prestador|cep_prestador|telefone_prestador|email_prestador
	 * |cnpj_tomador|inscricao_municipal_tomador|inscricao_estadual_tomador|razao_social_tomador|endereco_tomador
	 * |endereco_numero_tomador|endereco_complemento_tomador|endereco_bairro_tomador|municipio_tomador|uf_tomador
	 * |cep_tomador|telefone_tomador|email_tomador|cnpj_intermediario|inscricao_municipal_intermediario
	 * |razao_social_intermediario|data_inclusao_registro
	 */
	private Long idCodigo;
	private String nossoNumero;
	private String numeroNota;
	private String serieNota;
	private String tipoDocumento;
	private String dataEmissao;
	private String codigoVerificacao;
	private String naturezaOperacao;
	private String regimeTributacao;
	private String optantePeloSimplesNacional;
	private String statusNota;
	private String dataCancelamento;
	private String motivoCancelamento;
	private String dataCompetencia;
	private String outrasInformacoes;
	private String codigoAtividadeMunipal;
	private String codigoCnae;
	private String discriminacaoServico;
	private Double valorServico;
	private Double valorDeducao;
	private Double valorPis;
	private Double valorCofins;
	private Double valorInss;
	private Double valorIr;
	private Double valorCsll;
	private String tipoRetencao;
	private Double valorIss;
	private Double valorOutrasRetencoes;
	private Double valorBaseCalculo;
	private Double valorAliquota;
	private Double valorTotalNfse;
	private Double valorDescontoIncondicionado;
	private Double valorDescontoCondicionado;
	private String cnpjPrestador;
	private String inscricaoMunicipalPrestador;
	private String inscricaoEstadualPrestador;
	private String razaoSocialPrestador;
	private String nomeFantasiaPrestador;
	private String enderecoPrestador;
	private String enderecoNumeroPrestador;
	private String enderecoComplementoPrestador;
	private String enderecoBairroPrestador;
	private String cidadePrestador;
	private String ufPrestador;
	private String cepPrestador;
	private String telefonePrestador;
	private String emailPrestador;
	private String cnpjTomador;
	private String inscricaoMunicipalTomador;
	private String inscricaoEstadualTomador;
	private String razaoSocialTomador;
	private String enderecoTomador;
	private String enderecoNumeroTomador;
	private String enderecoComplementoTomador;
	private String enderecoBairroTomador;
	private String municipioTomador;
	private String ufTomador;
	private String cepTomador;
	private String telefoneTomador;
	private String emailTomador;
	private String cnpjIntermediario;
	private String inscricaoMunicipalIntermediario;
	private String razaoSocialIntermediario;
	private String dataInclusaoRegistro;
	private String itemListaServico;
	
	public String getItemListaServico() {
		return itemListaServico;
	}

	public void setItemListaServico(String itemListaServico) {
		this.itemListaServico = itemListaServico;
	}

	public DadosLivroPrestador( Long idCodigo, String nossoNumero, String numeroNota, String serieNota, String tipoDocumento, String dataEmissao,
			 String codigoVerificacao, String naturezaOperacao, String regimeTributacao, String optantePeloSimplesNacional, String statusNota,
			 String dataCancelamento, String motivoCancelamento, String dataCompetencia, String outrasInformacoes, String codigoAtividadeMunipal,
			 String codigoCnae, String discriminacaoServico, Double valorServico, Double valorDeducao, Double valorPis, Double valorCofins,
			 Double valorInss, Double valorIr, Double valorCsll, String tipoRetencao, Double valorIss, Double valorOutrasRetencoes,
			 Double valorBaseCalculo, Double valorAliquota, Double valorTotalNfse, Double valorDescontoIncondicionado,
			 Double valorDescontoCondicionado, String cnpjPrestador, String inscricaoMunicipalPrestador, String inscricaoEstadualPrestador,
			 String razaoSocialPrestador, String nomeFantasiaPrestador, String enderecoPrestador, String enderecoNumeroPrestador,
			 String enderecoComplementoPrestador, String enderecoBairroPrestador, String cidadePrestador, String ufPrestador,
			 String cepPrestador, String telefonePrestador, String emailPrestador, String cnpjTomador, String inscricaoMunicipalTomador,
			 String inscricaoEstadualTomador, String razaoSocialTomador, String enderecoTomador, String enderecoNumeroTomador,
			 String enderecoComplementoTomador, String enderecoBairroTomador, String municipioTomador, String ufTomador,
			 String cepTomador, String telefoneTomador, String emailTomador, String cnpjIntermediario, String inscricaoMunicipalIntermediario,
			 String razaoSocialIntermediario, String dataInclusaoRegistro) {
		
		this.idCodigo = idCodigo;
		this.nossoNumero = nossoNumero;
		this.numeroNota = numeroNota;
		this.serieNota = serieNota;
		this.tipoDocumento = tipoDocumento;
		this.dataEmissao = dataEmissao;
		this.codigoVerificacao = codigoVerificacao;
		this.naturezaOperacao = naturezaOperacao;
		this.regimeTributacao = regimeTributacao;
		this.optantePeloSimplesNacional = optantePeloSimplesNacional;
		this.statusNota = statusNota;
		this.dataCancelamento = dataCancelamento;
		this.motivoCancelamento = motivoCancelamento;
		this.dataCompetencia = dataCompetencia;
		this.outrasInformacoes = outrasInformacoes;
		this.codigoAtividadeMunipal = codigoAtividadeMunipal;
		this.codigoCnae = codigoCnae;
		this.discriminacaoServico = discriminacaoServico;
		this.valorServico = valorServico;
		this.valorDeducao = valorDeducao;
		this.valorPis = valorPis;
		this.valorCofins = valorCofins;
		this.valorInss = valorInss;
		this.valorIr = valorIr;
		this.valorCsll = valorCsll;
		this.tipoRetencao = tipoRetencao;
		this.valorIss = valorIss;
		this.valorOutrasRetencoes = valorOutrasRetencoes;
		this.valorBaseCalculo = valorBaseCalculo;
		this.valorAliquota = valorAliquota;
		this.valorTotalNfse = valorTotalNfse;
		this.valorDescontoIncondicionado = valorDescontoIncondicionado;
		this.valorDescontoCondicionado = valorDescontoCondicionado;
		this.cnpjPrestador = cnpjPrestador;
		this.inscricaoMunicipalPrestador = inscricaoMunicipalPrestador;
		this.inscricaoEstadualPrestador = inscricaoEstadualPrestador;
		this.razaoSocialPrestador = razaoSocialPrestador;
		this.nomeFantasiaPrestador = nomeFantasiaPrestador;
		this.enderecoPrestador = enderecoPrestador;
		this.enderecoNumeroPrestador = enderecoNumeroPrestador;
		this.enderecoComplementoPrestador = enderecoComplementoPrestador;
		this.enderecoBairroPrestador = enderecoBairroPrestador;
		this.cidadePrestador = cidadePrestador;
		this.ufPrestador = ufPrestador;
		this.cepPrestador = cepPrestador;
		this.telefonePrestador = telefonePrestador;
		this.emailPrestador = emailPrestador;
		this.cnpjTomador = cnpjTomador;
		this.inscricaoMunicipalTomador = inscricaoMunicipalTomador;
		this.inscricaoEstadualTomador = inscricaoEstadualTomador;
		this.razaoSocialTomador = razaoSocialTomador;
		this.enderecoTomador = enderecoTomador;
		this.enderecoNumeroTomador = enderecoNumeroTomador;
		this.enderecoComplementoTomador = enderecoComplementoTomador;
		this.enderecoBairroTomador = enderecoBairroTomador;
		this.municipioTomador = municipioTomador;
		this.ufTomador = ufTomador;
		this.cepTomador = cepTomador;
		this.telefoneTomador = telefoneTomador;
		this.emailTomador = emailTomador;
		this.cnpjIntermediario = cnpjIntermediario;
		this.inscricaoMunicipalIntermediario = inscricaoMunicipalIntermediario;
		this.razaoSocialIntermediario = razaoSocialIntermediario;
		this.dataInclusaoRegistro = dataInclusaoRegistro;
	}

	/**
	 * criado para tratar desposicionamento do registro no txt
	 */
	public DadosLivroPrestador( Long idCodigo, String nossoNumero, String numeroNota, String serieNota, String tipoDocumento, String dataEmissao,
			 String codigoVerificacao, String naturezaOperacao, String regimeTributacao, String optantePeloSimplesNacional, String statusNota,
			 String dataCancelamento, String motivoCancelamento, String dataCompetencia, String outrasInformacoes, String codigoAtividadeMunipal,
			 String codigoCnae, String discriminacaoServico, String aux, Double valorServico, Double valorDeducao, Double valorPis, Double valorCofins,
			 Double valorInss, Double valorIr, Double valorCsll, String tipoRetencao, Double valorIss, Double valorOutrasRetencoes,
			 Double valorBaseCalculo, Double valorAliquota, Double valorTotalNfse, Double valorDescontoIncondicionado,
			 Double valorDescontoCondicionado, String cnpjPrestador, String inscricaoMunicipalPrestador, String inscricaoEstadualPrestador,
			 String razaoSocialPrestador, String nomeFantasiaPrestador, String enderecoPrestador, String enderecoNumeroPrestador,
			 String enderecoComplementoPrestador, String enderecoBairroPrestador, String cidadePrestador, String ufPrestador,
			 String cepPrestador, String telefonePrestador, String emailPrestador, String cnpjTomador, String inscricaoMunicipalTomador,
			 String inscricaoEstadualTomador, String razaoSocialTomador, String enderecoTomador, String enderecoNumeroTomador,
			 String enderecoComplementoTomador, String enderecoBairroTomador, String municipioTomador, String ufTomador,
			 String cepTomador, String telefoneTomador, String emailTomador, String cnpjIntermediario, String inscricaoMunicipalIntermediario,
			 String razaoSocialIntermediario, String dataInclusaoRegistro) {
		
		this.idCodigo = idCodigo;
		this.nossoNumero = nossoNumero;
		this.numeroNota = numeroNota;
		this.serieNota = serieNota;
		this.tipoDocumento = tipoDocumento;
		this.dataEmissao = dataEmissao;
		this.codigoVerificacao = codigoVerificacao;
		this.naturezaOperacao = naturezaOperacao;
		this.regimeTributacao = regimeTributacao;
		this.optantePeloSimplesNacional = optantePeloSimplesNacional;
		this.statusNota = statusNota;
		this.dataCancelamento = dataCancelamento;
		this.motivoCancelamento = motivoCancelamento;
		this.dataCompetencia = dataCompetencia;
		this.outrasInformacoes = outrasInformacoes;
		this.codigoAtividadeMunipal = codigoAtividadeMunipal;
		this.codigoCnae = codigoCnae;
		this.discriminacaoServico = discriminacaoServico;
		this.valorServico = valorServico;
		this.valorDeducao = valorDeducao;
		this.valorPis = valorPis;
		this.valorCofins = valorCofins;
		this.valorInss = valorInss;
		this.valorIr = valorIr;
		this.valorCsll = valorCsll;
		this.tipoRetencao = tipoRetencao;
		this.valorIss = valorIss;
		this.valorOutrasRetencoes = valorOutrasRetencoes;
		this.valorBaseCalculo = valorBaseCalculo;
		this.valorAliquota = valorAliquota;
		this.valorTotalNfse = valorTotalNfse;
		this.valorDescontoIncondicionado = valorDescontoIncondicionado;
		this.valorDescontoCondicionado = valorDescontoCondicionado;
		this.cnpjPrestador = cnpjPrestador;
		this.inscricaoMunicipalPrestador = inscricaoMunicipalPrestador;
		this.inscricaoEstadualPrestador = inscricaoEstadualPrestador;
		this.razaoSocialPrestador = razaoSocialPrestador;
		this.nomeFantasiaPrestador = nomeFantasiaPrestador;
		this.enderecoPrestador = enderecoPrestador;
		this.enderecoNumeroPrestador = enderecoNumeroPrestador;
		this.enderecoComplementoPrestador = enderecoComplementoPrestador;
		this.enderecoBairroPrestador = enderecoBairroPrestador;
		this.cidadePrestador = cidadePrestador;
		this.ufPrestador = ufPrestador;
		this.cepPrestador = cepPrestador;
		this.telefonePrestador = telefonePrestador;
		this.emailPrestador = emailPrestador;
		this.cnpjTomador = cnpjTomador;
		this.inscricaoMunicipalTomador = inscricaoMunicipalTomador;
		this.inscricaoEstadualTomador = inscricaoEstadualTomador;
		this.razaoSocialTomador = razaoSocialTomador;
		this.enderecoTomador = enderecoTomador;
		this.enderecoNumeroTomador = enderecoNumeroTomador;
		this.enderecoComplementoTomador = enderecoComplementoTomador;
		this.enderecoBairroTomador = enderecoBairroTomador;
		this.municipioTomador = municipioTomador;
		this.ufTomador = ufTomador;
		this.cepTomador = cepTomador;
		this.telefoneTomador = telefoneTomador;
		this.emailTomador = emailTomador;
		this.cnpjIntermediario = cnpjIntermediario;
		this.inscricaoMunicipalIntermediario = inscricaoMunicipalIntermediario;
		this.razaoSocialIntermediario = razaoSocialIntermediario;
		this.dataInclusaoRegistro = dataInclusaoRegistro;
	}


	public Long getIdCodigo() {
		return idCodigo;
	}

	public void setIdCodigo(Long idCodigo) {
		this.idCodigo = idCodigo;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getSerieNota() {
		return serieNota;
	}

	public void setSerieNota(String serieNota) {
		this.serieNota = serieNota;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getCodigoVerificacao() {
		return codigoVerificacao;
	}

	public void setCodigoVerificacao(String codigoVerificacao) {
		this.codigoVerificacao = codigoVerificacao;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}

	public String getRegimeTributacao() {
		return regimeTributacao;
	}

	public void setRegimeTributacao(String regimeTributacao) {
		this.regimeTributacao = regimeTributacao;
	}

	public String getOptantePeloSimplesNacional() {
		return optantePeloSimplesNacional;
	}

	public void setOptantePeloSimplesNacional(String optantePeloSimplesNacional) {
		this.optantePeloSimplesNacional = optantePeloSimplesNacional;
	}

	public String getStatusNota() {
		return statusNota;
	}

	public void setStatusNota(String statusNota) {
		this.statusNota = statusNota;
	}

	public String getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(String dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public String getDataCompetencia() {
		return dataCompetencia;
	}

	public void setDataCompetencia(String dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public String getOutrasInformacoes() {
		return outrasInformacoes;
	}

	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
	}

	public String getCodigoAtividadeMunipal() {
		return codigoAtividadeMunipal;
	}

	public void setCodigoAtividadeMunipal(String codigoAtividadeMunipal) {
		this.codigoAtividadeMunipal = codigoAtividadeMunipal;
	}

	public String getCodigoCnae() {
		return codigoCnae;
	}

	public void setCodigoCnae(String codigoCnae) {
		this.codigoCnae = codigoCnae;
	}

	public String getDiscriminacaoServico() {
		return discriminacaoServico;
	}

	public void setDiscriminacaoServico(String discriminacaoServico) {
		this.discriminacaoServico = discriminacaoServico;
	}

	public Double getValorServico() {
		return valorServico;
	}

	public void setValorServico(Double valorServico) {
		this.valorServico = valorServico;
	}

	public Double getValorDeducao() {
		return valorDeducao;
	}

	public void setValorDeducao(Double valorDeducao) {
		this.valorDeducao = valorDeducao;
	}

	public Double getValorPis() {
		return valorPis;
	}

	public void setValorPis(Double valorPis) {
		this.valorPis = valorPis;
	}

	public Double getValorCofins() {
		return valorCofins;
	}

	public void setValorCofins(Double valorCofins) {
		this.valorCofins = valorCofins;
	}

	public Double getValorInss() {
		return valorInss;
	}

	public void setValorInss(Double valorInss) {
		this.valorInss = valorInss;
	}

	public Double getValorIr() {
		return valorIr;
	}

	public void setValorIr(Double valorIr) {
		this.valorIr = valorIr;
	}

	public Double getValorCsll() {
		return valorCsll;
	}

	public void setValorCsll(Double valorCsll) {
		this.valorCsll = valorCsll;
	}

	public String getTipoRetencao() {
		return tipoRetencao;
	}

	public void setTipoRetencao(String tipoRetencao) {
		this.tipoRetencao = tipoRetencao;
	}

	public Double getValorIss() {
		return valorIss;
	}

	public void setValorIss(Double valorIss) {
		this.valorIss = valorIss;
	}

	public Double getValorOutrasRetencoes() {
		return valorOutrasRetencoes;
	}

	public void setValorOutrasRetencoes(Double valorOutrasRetencoes) {
		this.valorOutrasRetencoes = valorOutrasRetencoes;
	}

	public Double getValorBaseCalculo() {
		return valorBaseCalculo;
	}

	public void setValorBaseCalculo(Double valorBaseCalculo) {
		this.valorBaseCalculo = valorBaseCalculo;
	}

	public Double getValorAliquota() {
		return valorAliquota;
	}

	public void setValorAliquota(Double valorAliquota) {
		this.valorAliquota = valorAliquota;
	}

	public Double getValorTotalNfse() {
		return valorTotalNfse;
	}

	public void setValorTotalNfse(Double valorTotalNfse) {
		this.valorTotalNfse = valorTotalNfse;
	}

	public Double getValorDescontoIncondicionado() {
		return valorDescontoIncondicionado;
	}

	public void setValorDescontoIncondicionado(Double valorDescontoIncondicionado) {
		this.valorDescontoIncondicionado = valorDescontoIncondicionado;
	}

	public Double getValorDescontoCondicionado() {
		return valorDescontoCondicionado;
	}

	public void setValorDescontoCondicionado(Double valorDescontoCondicionado) {
		this.valorDescontoCondicionado = valorDescontoCondicionado;
	}

	public String getCnpjPrestador() {
		return cnpjPrestador;
	}

	public void setCnpjPrestador(String cnpjPrestador) {
		this.cnpjPrestador = cnpjPrestador;
	}

	public String getInscricaoMunicipalPrestador() {
		return inscricaoMunicipalPrestador;
	}

	public void setInscricaoMunicipalPrestador(String inscricaoMunicipalPrestador) {
		this.inscricaoMunicipalPrestador = inscricaoMunicipalPrestador;
	}

	public String getInscricaoEstadualPrestador() {
		return inscricaoEstadualPrestador;
	}

	public void setInscricaoEstadualPrestador(String inscricaoEstadualPrestador) {
		this.inscricaoEstadualPrestador = inscricaoEstadualPrestador;
	}

	public String getRazaoSocialPrestador() {
		return razaoSocialPrestador;
	}

	public void setRazaoSocialPrestador(String razaoSocialPrestador) {
		this.razaoSocialPrestador = razaoSocialPrestador;
	}

	public String getNomeFantasiaPrestador() {
		return nomeFantasiaPrestador;
	}

	public void setNomeFantasiaPrestador(String nomeFantasiaPrestador) {
		this.nomeFantasiaPrestador = nomeFantasiaPrestador;
	}

	public String getEnderecoPrestador() {
		return enderecoPrestador;
	}

	public void setEnderecoPrestador(String enderecoPrestador) {
		this.enderecoPrestador = enderecoPrestador;
	}

	public String getEnderecoNumeroPrestador() {
		return enderecoNumeroPrestador;
	}

	public void setEnderecoNumeroPrestador(String enderecoNumeroPrestador) {
		this.enderecoNumeroPrestador = enderecoNumeroPrestador;
	}

	public String getEnderecoComplementoPrestador() {
		return enderecoComplementoPrestador;
	}

	public void setEnderecoComplementoPrestador(String enderecoComplementoPrestador) {
		this.enderecoComplementoPrestador = enderecoComplementoPrestador;
	}

	public String getEnderecoBairroPrestador() {
		return enderecoBairroPrestador;
	}

	public void setEnderecoBairroPrestador(String enderecoBairroPrestador) {
		this.enderecoBairroPrestador = enderecoBairroPrestador;
	}

	public String getCidadePrestador() {
		return cidadePrestador;
	}

	public void setCidadePrestador(String cidadePrestador) {
		this.cidadePrestador = cidadePrestador;
	}

	public String getUfPrestador() {
		return ufPrestador;
	}

	public void setUfPrestador(String ufPrestador) {
		this.ufPrestador = ufPrestador;
	}

	public String getCepPrestador() {
		return cepPrestador;
	}

	public void setCepPrestador(String cepPrestador) {
		this.cepPrestador = cepPrestador;
	}

	public String getTelefonePrestador() {
		return telefonePrestador;
	}

	public void setTelefonePrestador(String telefonePrestador) {
		this.telefonePrestador = telefonePrestador;
	}

	public String getEmailPrestador() {
		return emailPrestador;
	}

	public void setEmailPrestador(String emailPrestador) {
		this.emailPrestador = emailPrestador;
	}

	public String getCnpjTomador() {
		return cnpjTomador;
	}

	public void setCnpjTomador(String cnpjTomador) {
		this.cnpjTomador = cnpjTomador;
	}

	public String getInscricaoMunicipalTomador() {
		return inscricaoMunicipalTomador;
	}

	public void setInscricaoMunicipalTomador(String inscricaoMunicipalTomador) {
		this.inscricaoMunicipalTomador = inscricaoMunicipalTomador;
	}

	public String getInscricaoEstadualTomador() {
		return inscricaoEstadualTomador;
	}

	public void setInscricaoEstadualTomador(String inscricaoEstadualTomador) {
		this.inscricaoEstadualTomador = inscricaoEstadualTomador;
	}

	public String getRazaoSocialTomador() {
		return razaoSocialTomador;
	}

	public void setRazaoSocialTomador(String razaoSocialTomador) {
		this.razaoSocialTomador = razaoSocialTomador;
	}

	public String getEnderecoTomador() {
		return enderecoTomador;
	}

	public void setEnderecoTomador(String enderecoTomador) {
		this.enderecoTomador = enderecoTomador;
	}

	public String getEnderecoNumeroTomador() {
		return enderecoNumeroTomador;
	}

	public void setEnderecoNumeroTomador(String enderecoNumeroTomador) {
		this.enderecoNumeroTomador = enderecoNumeroTomador;
	}

	public String getEnderecoComplementoTomador() {
		return enderecoComplementoTomador;
	}

	public void setEnderecoComplementoTomador(String enderecoComplementoTomador) {
		this.enderecoComplementoTomador = enderecoComplementoTomador;
	}

	public String getEnderecoBairroTomador() {
		return enderecoBairroTomador;
	}

	public void setEnderecoBairroTomador(String enderecoBairroTomador) {
		this.enderecoBairroTomador = enderecoBairroTomador;
	}

	public String getMunicipioTomador() {
		return municipioTomador;
	}

	public void setMunicipioTomador(String municipioTomador) {
		this.municipioTomador = municipioTomador;
	}

	public String getUfTomador() {
		return ufTomador;
	}

	public void setUfTomador(String ufTomador) {
		this.ufTomador = ufTomador;
	}

	public String getCepTomador() {
		return cepTomador;
	}

	public void setCepTomador(String cepTomador) {
		this.cepTomador = cepTomador;
	}

	public String getTelefoneTomador() {
		return telefoneTomador;
	}

	public void setTelefoneTomador(String telefoneTomador) {
		this.telefoneTomador = telefoneTomador;
	}

	public String getEmailTomador() {
		return emailTomador;
	}

	public void setEmailTomador(String emailTomador) {
		this.emailTomador = emailTomador;
	}

	public String getCnpjIntermediario() {
		return cnpjIntermediario;
	}

	public void setCnpjIntermediario(String cnpjIntermediario) {
		this.cnpjIntermediario = cnpjIntermediario;
	}

	public String getInscricaoMunicipalIntermediario() {
		return inscricaoMunicipalIntermediario;
	}

	public void setInscricaoMunicipalIntermediario(String inscricaoMunicipalIntermediario) {
		this.inscricaoMunicipalIntermediario = inscricaoMunicipalIntermediario;
	}

	public String getRazaoSocialIntermediario() {
		return razaoSocialIntermediario;
	}

	public void setRazaoSocialIntermediario(String razaoSocialIntermediario) {
		this.razaoSocialIntermediario = razaoSocialIntermediario;
	}

	public String getDataInclusaoRegistro() {
		return dataInclusaoRegistro;
	}

	public void setDataInclusaoRegistro(String dataInclusaoRegistro) {
		this.dataInclusaoRegistro = dataInclusaoRegistro;
	}
	
	
	
	
}
