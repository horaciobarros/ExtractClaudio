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
@Table(name="prestadores")
public class Prestadores implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="inscricao_prestador")
   private String inscricaoPrestador;

   @Column(name="autorizado")
   private String autorizado;

   @Column(name="enquadramento")
   private String enquadramento;

   @Column(name="telefone")
   private String telefone;

   @Column(name="celular")
   private String celular;

   @Column(name="fax")
   private String fax;

   @Column(name="email")
   private String email;

   @Column(name="motivo")
   private String motivo;

   @Column(name="dh_envio")
   private Date dhEnvio;

   @Column(name="hash")
   private String hash;
   
   @Column(name="inscricao_municipal")
   private String inscricaoMunicipal;

   public String getInscricaoMunicipal() {
	return inscricaoMunicipal;
}
public void setInscricaoMunicipal(String inscricaoMunicipal) {
	this.inscricaoMunicipal = inscricaoMunicipal;
}
public Long getId() { 
      return id;
   }
   public void  setId(Long id) { 
      this.id = id;
   }

   public String getInscricaoPrestador() { 
      return inscricaoPrestador;
   }
   public void  setInscricaoPrestador(String inscricaoPrestador) { 
      this.inscricaoPrestador = inscricaoPrestador;
   }

   public String getAutorizado() { 
      return autorizado;
   }
   public void  setAutorizado(String autorizado) { 
      this.autorizado = autorizado;
   }

   public String getEnquadramento() { 
      return enquadramento;
   }
   public void  setEnquadramento(String enquadramento) { 
      this.enquadramento = enquadramento;
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

   public String getEmail() { 
      return email;
   }
   public void  setEmail(String email) { 
      this.email = email;
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