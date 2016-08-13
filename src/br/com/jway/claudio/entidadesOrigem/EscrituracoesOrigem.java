package br.com.jway.claudio.entidadesOrigem; 

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.*;
import java.util.*;
import java.math.BigDecimal;

@Entity 
@Table(name="escrituracoes_origem")
public class EscrituracoesOrigem implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private String id;

   @Column(name="id_nota_fiscal")
   private String idNotaFiscal;

   @Column(name="id_contribuinte")
   private String idContribuinte;

   @Column(name="id_tomador")
   private String idTomador;

   @Column(name="status")
   private String status;

   @Column(name="data_de_criacao")
   private String dataDeCriacao;

   @Column(name="id_escrituracao_substituida")
   private String idEscrituracaoSubstituida;

   @Column(name="autor_email")
   private String autorEmail;

   @Column(name="autor_nome")
   private String autorNome;

   @Column(name="autor_cpf")
   private String autorCpf;

   @Column(name="cpf_cnpj_contribuinte")
   private String cpfCnpjContribuinte;

   @Column(name="numero_nota_fiscal")
   private String numeroNotaFiscal;

   @Column(name="serie")
   private String serie;

   @Column(name="situacao")
   private String situacao;

   @Column(name="data_da_escrituracao")
   private String dataDaEscrituracao;

   @Column(name="aliquota_nota_fiscal")
   private String aliquotaNotaFiscal;

   @Column(name="deducoes_e_descontos_nota_fiscal")
   private String deducoesEDescontosNotaFiscal;

   @Column(name="valor_da_nota_fiscal")
   private String valorDaNotaFiscal;

   @Column(name="tipo_da_nota_fiscal")
   private String tipoDaNotaFiscal;

   @Column(name="escrituracao_avulsa")
   private String escrituracaoAvulsa;

   @Column(name="servico_prestado_por_empresa_do_exterior")
   private String servicoPrestadoPorEmpresaDoExterior;

   public String getId() { 
      return id;
   }
   public void  setId(String id) { 
      this.id = id;
   }

   public String getIdNotaFiscal() { 
      return idNotaFiscal;
   }
   public void  setIdNotaFiscal(String idNotaFiscal) { 
      this.idNotaFiscal = idNotaFiscal;
   }

   public String getIdContribuinte() { 
      return idContribuinte;
   }
   public void  setIdContribuinte(String idContribuinte) { 
      this.idContribuinte = idContribuinte;
   }

   public String getIdTomador() { 
      return idTomador;
   }
   public void  setIdTomador(String idTomador) { 
      this.idTomador = idTomador;
   }

   public String getStatus() { 
      return status;
   }
   public void  setStatus(String status) { 
      this.status = status;
   }

   public String getDataDeCriacao() { 
      return dataDeCriacao;
   }
   public void  setDataDeCriacao(String dataDeCriacao) { 
      this.dataDeCriacao = dataDeCriacao;
   }

   public String getIdEscrituracaoSubstituida() { 
      return idEscrituracaoSubstituida;
   }
   public void  setIdEscrituracaoSubstituida(String idEscrituracaoSubstituida) { 
      this.idEscrituracaoSubstituida = idEscrituracaoSubstituida;
   }

   public String getAutorEmail() { 
      return autorEmail;
   }
   public void  setAutorEmail(String autorEmail) { 
      this.autorEmail = autorEmail;
   }

   public String getAutorNome() { 
      return autorNome;
   }
   public void  setAutorNome(String autorNome) { 
      this.autorNome = autorNome;
   }

   public String getAutorCpf() { 
      return autorCpf;
   }
   public void  setAutorCpf(String autorCpf) { 
      this.autorCpf = autorCpf;
   }

   public String getCpfCnpjContribuinte() { 
      return cpfCnpjContribuinte;
   }
   public void  setCpfCnpjContribuinte(String cpfCnpjContribuinte) { 
      this.cpfCnpjContribuinte = cpfCnpjContribuinte;
   }

   public String getNumeroNotaFiscal() { 
      return numeroNotaFiscal;
   }
   public void  setNumeroNotaFiscal(String numeroNotaFiscal) { 
      this.numeroNotaFiscal = numeroNotaFiscal;
   }

   public String getSerie() { 
      return serie;
   }
   public void  setSerie(String serie) { 
      this.serie = serie;
   }

   public String getSituacao() { 
      return situacao;
   }
   public void  setSituacao(String situacao) { 
      this.situacao = situacao;
   }

   public String getDataDaEscrituracao() { 
      return dataDaEscrituracao;
   }
   public void  setDataDaEscrituracao(String dataDaEscrituracao) { 
      this.dataDaEscrituracao = dataDaEscrituracao;
   }

   public String getAliquotaNotaFiscal() { 
      return aliquotaNotaFiscal;
   }
   public void  setAliquotaNotaFiscal(String aliquotaNotaFiscal) { 
      this.aliquotaNotaFiscal = aliquotaNotaFiscal;
   }

   public String getDeducoesEDescontosNotaFiscal() { 
      return deducoesEDescontosNotaFiscal;
   }
   public void  setDeducoesEDescontosNotaFiscal(String deducoesEDescontosNotaFiscal) { 
      this.deducoesEDescontosNotaFiscal = deducoesEDescontosNotaFiscal;
   }

   public String getValorDaNotaFiscal() { 
      return valorDaNotaFiscal;
   }
   public void  setValorDaNotaFiscal(String valorDaNotaFiscal) { 
      this.valorDaNotaFiscal = valorDaNotaFiscal;
   }

   public String getTipoDaNotaFiscal() { 
      return tipoDaNotaFiscal;
   }
   public void  setTipoDaNotaFiscal(String tipoDaNotaFiscal) { 
      this.tipoDaNotaFiscal = tipoDaNotaFiscal;
   }

   public String getEscrituracaoAvulsa() { 
      return escrituracaoAvulsa;
   }
   public void  setEscrituracaoAvulsa(String escrituracaoAvulsa) { 
      this.escrituracaoAvulsa = escrituracaoAvulsa;
   }

   public String getServicoPrestadoPorEmpresaDoExterior() { 
      return servicoPrestadoPorEmpresaDoExterior;
   }
   public void  setServicoPrestadoPorEmpresaDoExterior(String servicoPrestadoPorEmpresaDoExterior) { 
      this.servicoPrestadoPorEmpresaDoExterior = servicoPrestadoPorEmpresaDoExterior;
   }
   
   public EscrituracoesOrigem(String id, String idNotaFiscal, String idContribuinte, String idTomador, String status,
			String dataDeCriacao, String idEscrituracaoSubstituida, String autorEmail, String autorNome,
			String autorCpf, String cpfCpnjContribuinte, String numeroNotaFiscal, String serie, String situacao,
			String dataDaEscrituracao, String aliquotaNotaFiscal, String deducoesEDescontosNotaFiscal,
			String valorDaNotaFiscal, String tipoDaNotaFiscal, String escrituracaoAvulsa,
			String servicoPrestadoPorEmpresaDoExterior) {
		super();
		this.id = id;
		this.idNotaFiscal = idNotaFiscal;
		this.idContribuinte = idContribuinte;
		this.idTomador = idTomador;
		this.status = status;
		this.dataDeCriacao = dataDeCriacao;
		this.idEscrituracaoSubstituida = idEscrituracaoSubstituida;
		this.autorEmail = autorEmail;
		this.autorNome = autorNome;
		this.autorCpf = autorCpf;
		this.cpfCnpjContribuinte = cpfCpnjContribuinte;
		this.numeroNotaFiscal = numeroNotaFiscal;
		this.serie = serie;
		this.situacao = situacao;
		this.dataDaEscrituracao = dataDaEscrituracao;
		this.aliquotaNotaFiscal = aliquotaNotaFiscal;
		this.deducoesEDescontosNotaFiscal = deducoesEDescontosNotaFiscal;
		this.valorDaNotaFiscal = valorDaNotaFiscal;
		this.tipoDaNotaFiscal = tipoDaNotaFiscal;
		this.escrituracaoAvulsa = escrituracaoAvulsa;
		this.servicoPrestadoPorEmpresaDoExterior = servicoPrestadoPorEmpresaDoExterior;
	}
   
   public EscrituracoesOrigem() {
	   
   }
   
}