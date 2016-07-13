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
@Table(name="notas_fiscais_subst")
public class NotasFiscaisSubst implements Serializable {

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

   @Column(name="numero_nota_substituta")
   private Long numeroNotaSubstituta;

   @Column(name="dataHoraSubstituicao")
   private Date datahorasubstituicao;

   @Column(name="motivo")
   private String motivo;

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

   public Long getNumeroNotaSubstituta() { 
      return numeroNotaSubstituta;
   }
   public void  setNumeroNotaSubstituta(Long numeroNotaSubstituta) { 
      this.numeroNotaSubstituta = numeroNotaSubstituta;
   }

   public Date getDatahorasubstituicao() { 
      return datahorasubstituicao;
   }
   public void  setDatahorasubstituicao(Date datahorasubstituicao) { 
      this.datahorasubstituicao = datahorasubstituicao;
   }

   public String getMotivo() { 
      return motivo;
   }
   public void  setMotivo(String motivo) { 
      this.motivo = motivo;
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