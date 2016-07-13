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
@Table(name="notas_fiscais_prestadores")
public class NotasFiscaisPrestadores implements Serializable {

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

   @Column(name="tipo_pessoa")
   private String tipoPessoa;

   @Column(name="nome")
   private String nome;

   @Column(name="nome_fantasia")
   private String nomeFantasia;

   @Column(name="inscricao_municipal")
   private String inscricaoMunicipal;

   @Column(name="optante_simples")
   private String optanteSimples;

   @Column(name="cep")
   private String cep;

   @Column(name="bairro")
   private String bairro;

   @Column(name="endereco")
   private String endereco;

   @Column(name="numero")
   private String numero;

   @Column(name="complemento")
   private String complemento;

   @Column(name="email")
   private String email;

   @Column(name="telefone")
   private String telefone;

   @Column(name="celular")
   private String celular;

   @Column(name="fax")
   private String fax;

   @Column(name="numero_pis")
   private String numeroPis;

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

   public String getTipoPessoa() { 
      return tipoPessoa;
   }
   public void  setTipoPessoa(String tipoPessoa) { 
      this.tipoPessoa = tipoPessoa;
   }

   public String getNome() { 
      return nome;
   }
   public void  setNome(String nome) { 
      this.nome = nome;
   }

   public String getNomeFantasia() { 
      return nomeFantasia;
   }
   public void  setNomeFantasia(String nomeFantasia) { 
      this.nomeFantasia = nomeFantasia;
   }

   public String getInscricaoMunicipal() { 
      return inscricaoMunicipal;
   }
   public void  setInscricaoMunicipal(String inscricaoMunicipal) { 
      this.inscricaoMunicipal = inscricaoMunicipal;
   }

   public String getOptanteSimples() { 
      return optanteSimples;
   }
   public void  setOptanteSimples(String optanteSimples) { 
      this.optanteSimples = optanteSimples;
   }

   public String getCep() { 
      return cep;
   }
   public void  setCep(String cep) { 
      this.cep = cep;
   }

   public String getBairro() { 
      return bairro;
   }
   public void  setBairro(String bairro) { 
      this.bairro = bairro;
   }

   public String getEndereco() { 
      return endereco;
   }
   public void  setEndereco(String endereco) { 
      this.endereco = endereco;
   }

   public String getNumero() { 
      return numero;
   }
   public void  setNumero(String numero) { 
      this.numero = numero;
   }

   public String getComplemento() { 
      return complemento;
   }
   public void  setComplemento(String complemento) { 
      this.complemento = complemento;
   }

   public String getEmail() { 
      return email;
   }
   public void  setEmail(String email) { 
      this.email = email;
   }

   public String getTelefone() { 
      return telefone;
   }
   public void  setTelefone(String telefone) { 
      this.telefone = telefone;
   }

   public String getCelular() { 
      return celular;
   }
   public void  setCelular(String celular) { 
      this.celular = celular;
   }

   public String getFax() { 
      return fax;
   }
   public void  setFax(String fax) { 
      this.fax = fax;
   }

   public String getNumeroPis() { 
      return numeroPis;
   }
   public void  setNumeroPis(String numeroPis) { 
      this.numeroPis = numeroPis;
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