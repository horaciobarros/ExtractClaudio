package br.com.jway.claudio.model; 

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="notas_fiscais_cond_pagamentos")
public class NotasFiscaisCondPagamentos implements Serializable {

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

   @Column(name="numero_parcela")
   private Long numeroParcela;

   @Column(name="data_vencimento")
   private Date dataVencimento;

   @Column(name="valor_parcela")
   private BigDecimal valorParcela;

   @Column(name="tipo")
   private Long tipo;

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

   public Long getNumeroParcela() { 
      return numeroParcela;
   }
   public void  setNumeroParcela(Long numeroParcela) { 
      this.numeroParcela = numeroParcela;
   }

   public Date getDataVencimento() { 
      return dataVencimento;
   }
   public void  setDataVencimento(Date dataVencimento) { 
      this.dataVencimento = dataVencimento;
   }

   public BigDecimal getValorParcela() { 
      return valorParcela;
   }
   public void  setValorParcela(BigDecimal valorParcela) { 
      this.valorParcela = valorParcela;
   }

   public Long getTipo() { 
      return tipo;
   }
   public void  setTipo(Long tipo) { 
      this.tipo = tipo;
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