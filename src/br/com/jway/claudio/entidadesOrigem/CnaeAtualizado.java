package br.com.jway.claudio.entidadesOrigem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cnae_atualizado")
public class CnaeAtualizado {
	
	@Id
	@Column(name = "id")
	private Long id;
	
	private String secao;
	private String divisao;
	private String grupo;
	private String classe;
	private String subclasse;
	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public String getDivisao() {
		return divisao;
	}

	public void setDivisao(String divisao) {
		this.divisao = divisao;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getSubclasse() {
		return subclasse;
	}

	public void setSubclasse(String subclasse) {
		this.subclasse = subclasse;
	}

	private String denominacao;
	private String cnae;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}
	
	

}
