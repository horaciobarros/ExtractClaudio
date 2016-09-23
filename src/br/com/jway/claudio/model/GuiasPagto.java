package br.com.jway.claudio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Fernando Werneck - 20/09/2016
 * Analista Desenvolvedor
 * fernandowtb@hotmail.com
 * www.jwaysistemas.com.br
 * (31) 98594-8242
 */
@Entity
@Table(name = "guia_pagto")
public class GuiasPagto {

	@Id
	@Column(name="id")
	private int id;
	@Column(name = "numero_guia")
	private String numeroGuia;
	@Column(name = "numero_pagamento")
	private String numeroPagamento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public String getNumeroPagamento() {
		return numeroPagamento;
	}
	public void setNumeroPagamento(String numeroPagamento) {
		this.numeroPagamento = numeroPagamento;
	}
}
