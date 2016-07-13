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
@Table(name="notas_fiscais_canceladas")
public class NotasFiscaisCanceladas implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "nota_id")
   private NotasFiscais notasFiscais;

   @Column(name="inscricao_prestador")
   private String inscricaoPrestador;

   @Column(name="numero_nota")
   private Long numeroNota;

   @Column(name="dataHoraCancelamento")
   private Date datahoracancelamento;

   @Column(name="motivo")
   private String motivo;

   @Column(name="numeroProcesso")
   private String numeroprocesso;

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


   public  NotasFiscais  getNotasFiscais() { 
      return notasFiscais;
   }
   public void  setNotasFiscais(NotasFiscais notasFiscais) { 
      this.notasFiscais = notasFiscais;
   }

   public String getInscricaoPrestador() { 
      return inscricaoPrestador;
   }
   public void  setInscricaoPrestador(String inscricaoPrestador) { 
      this.inscricaoPrestador = inscricaoPrestador;
   }

   public Long getNumeroNota() { 
      return numeroNota;
   }
   public void  setNumeroNota(Long numeroNota) { 
      this.numeroNota = numeroNota;
   }

   public Date getDatahoracancelamento() { 
      return datahoracancelamento;
   }
   public void  setDatahoracancelamento(Date datahoracancelamento) { 
      this.datahoracancelamento = datahoracancelamento;
   }

   public String getMotivo() { 
      return motivo;
   }
   public void  setMotivo(String motivo) { 
      this.motivo = motivo;
   }

   public String getNumeroprocesso() { 
      return numeroprocesso;
   }
   public void  setNumeroprocesso(String numeroprocesso) { 
      this.numeroprocesso = numeroprocesso;
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