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
@Table(name="notas_fiscais_servicos")
public class NotasFiscaisServicos implements Serializable {

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

   @Column(name="municipio_ibge")
   private String municipioIbge;

   @Column(name="item_lista_servico")
   private String itemListaServico;

   @Column(name="iCnaes")
   private String icnaes;

   @Column(name="descricao")
   private String descricao;

   @Column(name="descricao_cnae")
   private String descricaoCnae;

   @Column(name="valor_unitario")
   private BigDecimal valorUnitario;

   @Column(name="quantidade")
   private BigDecimal quantidade;

   @Column(name="valor_servico")
   private BigDecimal valorServico;

   @Column(name="valor_deducao")
   private BigDecimal valorDeducao;

   @Column(name="valor_desc_condicionado")
   private BigDecimal valorDescCondicionado;

   @Column(name="valor_desc_incondicionado")
   private BigDecimal valorDescIncondicionado;

   @Column(name="valor_base_calculo")
   private BigDecimal valorBaseCalculo;

   @Column(name="aliquota")
   private BigDecimal aliquota;

   @Column(name="valor_iss")
   private BigDecimal valorIss;

   @Column(name="aliquota_ibt_estadual")
   private BigDecimal aliquotaIbtEstadual;

   @Column(name="aliquota_ibt_federal")
   private BigDecimal aliquotaIbtFederal;

   @Column(name="aliquota_ibt_municipal")
   private BigDecimal aliquotaIbtMunicipal;

   @Column(name="dh_envio")
   private Date dhEnvio;

   @Column(name="hash")
   private String hash;
   
   @Column(name="id_servico_perdido")
   private Long idServicoPerdido;

   public Long getIdServicoPerdido() {
	return idServicoPerdido;
}
public void setIdServicoPerdido(Long idServicoPerdido) {
	this.idServicoPerdido = idServicoPerdido;
}
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

   public String getMunicipioIbge() { 
      return municipioIbge;
   }
   public void  setMunicipioIbge(String municipioIbge) { 
      this.municipioIbge = municipioIbge;
   }

   public String getItemListaServico() { 
      return itemListaServico;
   }
   public void  setItemListaServico(String itemListaServico) { 
      this.itemListaServico = itemListaServico;
   }

   public String getIcnaes() { 
      return icnaes;
   }
   public void  setIcnaes(String icnaes) { 
      this.icnaes = icnaes;
   }

   public String getDescricao() { 
      return descricao;
   }
   public void  setDescricao(String descricao) { 
      this.descricao = descricao;
   }

   public String getDescricaoCnae() { 
      return descricaoCnae;
   }
   public void  setDescricaoCnae(String descricaoCnae) { 
      this.descricaoCnae = descricaoCnae;
   }

   public BigDecimal getValorUnitario() { 
      return valorUnitario;
   }
   public void  setValorUnitario(BigDecimal valorUnitario) { 
      this.valorUnitario = valorUnitario;
   }

   public BigDecimal getQuantidade() { 
      return quantidade;
   }
   public void  setQuantidade(BigDecimal quantidade) { 
      this.quantidade = quantidade;
   }

   public BigDecimal getValorServico() { 
      return valorServico;
   }
   public void  setValorServico(BigDecimal valorServico) { 
      this.valorServico = valorServico;
   }

   public BigDecimal getValorDeducao() { 
      return valorDeducao;
   }
   public void  setValorDeducao(BigDecimal valorDeducao) { 
      this.valorDeducao = valorDeducao;
   }

   public BigDecimal getValorDescCondicionado() { 
      return valorDescCondicionado;
   }
   public void  setValorDescCondicionado(BigDecimal valorDescCondicionado) { 
      this.valorDescCondicionado = valorDescCondicionado;
   }

   public BigDecimal getValorDescIncondicionado() { 
      return valorDescIncondicionado;
   }
   public void  setValorDescIncondicionado(BigDecimal valorDescIncondicionado) { 
      this.valorDescIncondicionado = valorDescIncondicionado;
   }

   public BigDecimal getValorBaseCalculo() { 
      return valorBaseCalculo;
   }
   public void  setValorBaseCalculo(BigDecimal valorBaseCalculo) { 
      this.valorBaseCalculo = valorBaseCalculo;
   }

   public BigDecimal getAliquota() { 
      return aliquota;
   }
   public void  setAliquota(BigDecimal aliquota) { 
      this.aliquota = aliquota;
   }

   public BigDecimal getValorIss() { 
      return valorIss;
   }
   public void  setValorIss(BigDecimal valorIss) { 
      this.valorIss = valorIss;
   }

   public BigDecimal getAliquotaIbtEstadual() { 
      return aliquotaIbtEstadual;
   }
   public void  setAliquotaIbtEstadual(BigDecimal aliquotaIbtEstadual) { 
      this.aliquotaIbtEstadual = aliquotaIbtEstadual;
   }

   public BigDecimal getAliquotaIbtFederal() { 
      return aliquotaIbtFederal;
   }
   public void  setAliquotaIbtFederal(BigDecimal aliquotaIbtFederal) { 
      this.aliquotaIbtFederal = aliquotaIbtFederal;
   }

   public BigDecimal getAliquotaIbtMunicipal() { 
      return aliquotaIbtMunicipal;
   }
   public void  setAliquotaIbtMunicipal(BigDecimal aliquotaIbtMunicipal) { 
      this.aliquotaIbtMunicipal = aliquotaIbtMunicipal;
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