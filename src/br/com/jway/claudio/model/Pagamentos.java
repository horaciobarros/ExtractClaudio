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
@Table(name="pagamentos")
public class Pagamentos implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "guia_id")
   private Guias guias;

   @Column(name="numero_guia")
   private Long numeroGuia;

   @Column(name="numero_pagamento")
   private Long numeroPagamento;

   @Column(name="data_pagamento")
   private Date dataPagamento;

   @Column(name="valor_pago")
   private BigDecimal valorPago;

   @Column(name="valor_correcao")
   private BigDecimal valorCorrecao;

   @Column(name="valor_juro")
   private BigDecimal valorJuro;

   @Column(name="valor_multa")
   private BigDecimal valorMulta;

   @Column(name="valor_diferenca")
   private BigDecimal valorDiferenca;

   @Column(name="tipo_pagamento")
   private String tipoPagamento;

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


   public  Guias  getGuias() { 
      return guias;
   }
   public void  setGuias(Guias guias) { 
      this.guias = guias;
   }

   public Long getNumeroGuia() { 
      return numeroGuia;
   }
   public void  setNumeroGuia(Long numeroGuia) { 
      this.numeroGuia = numeroGuia;
   }

   public Long getNumeroPagamento() { 
      return numeroPagamento;
   }
   public void  setNumeroPagamento(Long numeroPagamento) { 
      this.numeroPagamento = numeroPagamento;
   }

   public Date getDataPagamento() { 
      return dataPagamento;
   }
   public void  setDataPagamento(Date dataPagamento) { 
      this.dataPagamento = dataPagamento;
   }

   public BigDecimal getValorPago() { 
      return valorPago;
   }
   public void  setValorPago(BigDecimal valorPago) { 
      this.valorPago = valorPago;
   }

   public BigDecimal getValorCorrecao() { 
      return valorCorrecao;
   }
   public void  setValorCorrecao(BigDecimal valorCorrecao) { 
      this.valorCorrecao = valorCorrecao;
   }

   public BigDecimal getValorJuro() { 
      return valorJuro;
   }
   public void  setValorJuro(BigDecimal valorJuro) { 
      this.valorJuro = valorJuro;
   }

   public BigDecimal getValorMulta() { 
      return valorMulta;
   }
   public void  setValorMulta(BigDecimal valorMulta) { 
      this.valorMulta = valorMulta;
   }

   public BigDecimal getValorDiferenca() { 
      return valorDiferenca;
   }
   public void  setValorDiferenca(BigDecimal valorDiferenca) { 
      this.valorDiferenca = valorDiferenca;
   }

   public String getTipoPagamento() { 
      return tipoPagamento;
   }
   public void  setTipoPagamento(String tipoPagamento) { 
      this.tipoPagamento = tipoPagamento;
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