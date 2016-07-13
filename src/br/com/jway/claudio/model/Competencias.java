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
@Table(name="competencias")
public class Competencias implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="descricao")
   private String descricao;

   @Column(name="data_fim")
   private Date dataFim;

   @Column(name="nova_data_fim")
   private Date novaDataFim;

   @Column(name="data_inicio")
   private Date dataInicio;

   @Column(name="nova_data_inicio")
   private Date novaDataInicio;

   @Column(name="data_vencimento")
   private Date dataVencimento;

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

   public String getDescricao() { 
      return descricao;
   }
   public void  setDescricao(String descricao) { 
      this.descricao = descricao;
   }

   public Date getDataFim() { 
      return dataFim;
   }
   public void  setDataFim(Date dataFim) { 
      this.dataFim = dataFim;
   }

   public Date getNovaDataFim() { 
      return novaDataFim;
   }
   public void  setNovaDataFim(Date novaDataFim) { 
      this.novaDataFim = novaDataFim;
   }

   public Date getDataInicio() { 
      return dataInicio;
   }
   public void  setDataInicio(Date dataInicio) { 
      this.dataInicio = dataInicio;
   }

   public Date getNovaDataInicio() { 
      return novaDataInicio;
   }
   public void  setNovaDataInicio(Date novaDataInicio) { 
      this.novaDataInicio = novaDataInicio;
   }

   public Date getDataVencimento() { 
      return dataVencimento;
   }
   public void  setDataVencimento(Date dataVencimento) { 
      this.dataVencimento = dataVencimento;
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