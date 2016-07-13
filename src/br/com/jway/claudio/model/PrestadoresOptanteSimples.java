package br.com.jway.claudio.model; 

import java.io.Serializable;
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
@Table(name="prestadores_optante_simples")
public class PrestadoresOptanteSimples implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "prestador_id")
   private Prestadores prestadores;

   @Column(name="inscricao_prestador")
   private String inscricaoPrestador;

   @Column(name="data_inicio")
   private Date dataInicio;

   @Column(name="data_efeito")
   private Date dataEfeito;

   @Column(name="descricao")
   private String descricao;

   @Column(name="mei")
   private String mei;

   @Column(name="motivo")
   private String motivo;

   @Column(name="optante")
   private String optante;

   @Column(name="orgao")
   private String orgao;

   @Column(name="dh_envio")
   private Date dhEnvio;

   @Column(name="hash")
   private String hash;

   public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }


   public  Prestadores  getPrestadores() { 
      return prestadores;
   }
   public void  setPrestadores(Prestadores prestadores) { 
      this.prestadores = prestadores;
   }

   public String getInscricaoPrestador() { 
      return inscricaoPrestador;
   }
   public void  setInscricaoPrestador(String inscricaoPrestador) { 
      this.inscricaoPrestador = inscricaoPrestador;
   }

   public Date getDataInicio() { 
      return dataInicio;
   }
   public void  setDataInicio(Date dataInicio) { 
      this.dataInicio = dataInicio;
   }

   public Date getDataEfeito() { 
      return dataEfeito;
   }
   public void  setDataEfeito(Date dataEfeito) { 
      this.dataEfeito = dataEfeito;
   }

   public String getDescricao() { 
      return descricao;
   }
   public void  setDescricao(String descricao) { 
      this.descricao = descricao;
   }

   public String getMei() { 
      return mei;
   }
   public void  setMei(String mei) { 
      this.mei = mei;
   }

   public String getMotivo() { 
      return motivo;
   }
   public void  setMotivo(String motivo) { 
      this.motivo = motivo;
   }

   public String getOptante() { 
      return optante;
   }
   public void  setOptante(String optante) { 
      this.optante = optante;
   }

   public String getOrgao() { 
      return orgao;
   }
   public void  setOrgao(String orgao) { 
      this.orgao = orgao;
   }

   public Date getDhEnvio() { 
      return dhEnvio;
   }
   public void  setDhEnvio(Date dhEnvio) { 
      this.dhEnvio = dhEnvio;
   }

   public String getHash() { 
      return hash;
   }
   public void  setHash(String hash) { 
      this.hash = hash;
   }
}