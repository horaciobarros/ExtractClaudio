package br.com.jway.claudio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "municipios_ibge")
public class MunicipiosIbge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codigo")
	private String codigo;

	@Column(name = "municipio")
	private String municipio;

	@Column(name = "uf")
	private String uf;

	public String getCodigo() {
		return codigo;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getUf() {
		return uf;
	}

}
