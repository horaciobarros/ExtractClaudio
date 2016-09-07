package br.com.jway.claudio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "pessoa_id")
	private Long pessoaId;

	@Column(name = "cnpj_cpf")
	private String cnpjCpf;

	@Column(name = "nome")
	private String nome;

	@Column(name = "tipo_pessoa")
	private String tipoPessoa;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Column(name = "optante_simples")
	private String optanteSimples;

	@Column(name = "porte_empresa")
	private String porteEmpresa;

	@Column(name = "inscricao_municipal")
	private String inscricaoMunicipal;

	@Column(name = "inscricao_estadual")
	private String inscricaoEstadual;

	@Column(name = "cep")
	private String cep;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "numero")
	private String numero;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "celular")
	private String celular;

	@Column(name = "website")
	private String website;

	@Column(name = "municipio_ibge")
	private Long municipioIbge;

	@Column(name = "pais")
	private String pais;

	@Column(name = "municipio")
	private String municipio;

	@Column(name = "numero_documento")
	private String numeroDocumento;

	@Column(name = "dh_envio")
	private Date dhEnvio;

	@Column(name = "hash")
	private String hash;
	
	@Column(name = "uf")
	private String uf;
	
	@Column(name = "sexo")
	private String sexo;

	@Column(name = "tipo_de_contribuinte_origem")
	private String tipoDeContribuinteOrigem;

	@Column(name = "tipo_da_empresa_origem")
	private String tipoDaEmpresaOrigem;

	@Column(name = "tipo_contribuinte_origem")
	private String tipoContribuinteOrigem;

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getOptanteSimples() {
		return optanteSimples;
	}

	public void setOptanteSimples(String optanteSimples) {
		this.optanteSimples = optanteSimples;
	}

	public String getPorteEmpresa() {
		return porteEmpresa;
	}

	public void setPorteEmpresa(String porteEmpresa) {
		this.porteEmpresa = porteEmpresa;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		if (inscricaoEstadual != null) {
			if (inscricaoEstadual.trim().length() > 15) {
				inscricaoEstadual = inscricaoEstadual.substring(0, 15);
			} else {
				inscricaoEstadual = inscricaoEstadual.trim();
			}
		}
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Long getMunicipioIbge() {
		return municipioIbge;
	}

	public void setMunicipioIbge(Long municipioIbge) {
		this.municipioIbge = municipioIbge;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	public String getTipoDeContribuinteOrigem() {
		return tipoDeContribuinteOrigem;
	}

	public void setTipoDeContribuinteOrigem(String tipoDeContribuinteOrigem) {
		this.tipoDeContribuinteOrigem = tipoDeContribuinteOrigem;
	}

	public String getTipoDaEmpresaOrigem() {
		return tipoDaEmpresaOrigem;
	}

	public void setTipoDaEmpresaOrigem(String tipoDaEmpresaOrigem) {
		this.tipoDaEmpresaOrigem = tipoDaEmpresaOrigem;
	}

	public String getTipoContribuinteOrigem() {
		return tipoContribuinteOrigem;
	}

	public void setTipoContribuinteOrigem(String tipoContribuinteOrigem) {
		this.tipoContribuinteOrigem = tipoContribuinteOrigem;
	}
}