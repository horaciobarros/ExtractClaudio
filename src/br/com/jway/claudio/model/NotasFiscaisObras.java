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
@Table(name="notas_fiscais_obras")
public class NotasFiscaisObras implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "nota_id")
   private NotasFiscais notasFiscais;

   @Column(name="numero_nota")
   private Long numeroNota;

   @Column(name="inscricao_prestador")
   private String inscricaoPrestador;

   @Column(name="numero_obra")
   private String numeroObra;

   @Column(name="numero_art")
   private String numeroArt;

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

   public Long getNumeroNota() { 
      return numeroNota;
   }
   public void  setNumeroNota(Long numeroNota) { 
      this.numeroNota = numeroNota;
   }

   public String getInscricaoPrestador() { 
      return inscricaoPrestador;
   }
   public void  setInscricaoPrestador(String inscricaoPrestador) { 
      this.inscricaoPrestador = inscricaoPrestador;
   }

   public String getNumeroObra() { 
      return numeroObra;
   }
   public void  setNumeroObra(String numeroObra) { 
      this.numeroObra = numeroObra;
   }

   public String getNumeroArt() { 
      return numeroArt;
   }
   public void  setNumeroArt(String numeroArt) { 
      this.numeroArt = numeroArt;
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