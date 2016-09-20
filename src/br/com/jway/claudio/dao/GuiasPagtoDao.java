package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.model.GuiasPagto;
import br.com.jway.claudio.util.HibernateUtil;

/**
 * @author Fernando Werneck - 20/09/2016
 * Analista Desenvolvedor
 * fernandowtb@hotmail.com
 * www.jwaysistemas.com.br
 * (31) 98594-8242
 */
public class GuiasPagtoDao {
	

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public GuiasPagtoDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	
	public List<GuiasPagto> findAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from GuiasPagto g ");
		List<GuiasPagto> lista = query.list();
		tx.commit();
		session.close();

		return lista;
	}
	
	public GuiasPagto findById(int id) {
		Query query = sessionFactory.openSession()
				.createQuery("from GuiasPagto e where e.id = :id" 
						).setParameter("id", id);

		try {
			List<GuiasPagto> g = query.list();

			if (g.size() > 0) {
				return g.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
