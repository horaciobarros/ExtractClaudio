package br.com.jway.claudio.util;
import java.io.File;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {

			File file = new File("hibernate.cfg.xml");
			if (!file.exists()) {
				JOptionPane
						.showMessageDialog(null,
								"arquivo de configuração do Banco de Dados não encontrado!");
				throw new ExceptionInInitializerError();
			} else {
				AnnotationConfiguration configuration = new AnnotationConfiguration();
				configuration.addAnnotatedClass(br.com.jway.claudio.model.Competencias.class);
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.Guias.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.GuiasNotasFiscais.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.NotasFiscais.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.NotasFiscaisCanceladas.class);
				
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.NotasFiscaisCondPagamentos.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.NotasFiscaisEmails.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.NotasFiscaisObras.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.NotasFiscaisPrestadores.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.NotasFiscaisServicos.class);
				
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.NotasFiscaisSubst.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.NotasFiscaisTomadores.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.NotasFiscaisXml.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.Pagamentos.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.Prestadores.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.PrestadoresAtividades.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.PrestadoresOptanteSimples.class);
				
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.Tomadores.class);
		
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.Pessoa.class);
				
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.MunicipiosIbge.class);
				
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.Bairros.class);
				
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.Logradouros.class);
				
				configuration
				.addAnnotatedClass(br.com.jway.claudio.entidadesOrigem.ServicosOrigem.class);
				
				configuration
				.addAnnotatedClass(br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem.class);
				
				configuration
				.addAnnotatedClass(br.com.jway.claudio.entidadesOrigem.Servicos.class);
				
				configuration
				.addAnnotatedClass(br.com.jway.claudio.model.GuiasPagto.class);
				
				SessionFactory sessionFactory = configuration.configure(file)
						.buildSessionFactory();
				return sessionFactory;
			}
		} catch (Throwable ex) {
			JOptionPane.showMessageDialog(null,
					"Erro em configuração do banco! Detalhes no log da aplicação");
			ex.printStackTrace();
			return null;
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}
