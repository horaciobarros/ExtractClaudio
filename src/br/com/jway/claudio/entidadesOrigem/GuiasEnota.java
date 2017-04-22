package br.com.jway.claudio.entidadesOrigem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "guias_excluir")
public class GuiasEnota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String inscricao;
	private String competencia;
	@Column(name = "numero_baixa")
	private String numeroBaixa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getCompetencia() {
		return competencia;
	}
	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	public String getNumeroBaixa() {
		return numeroBaixa;
	}
	public void setNumeroBaixa(String numeroBaixa) {
		this.numeroBaixa = numeroBaixa;
	}
	
	
}
