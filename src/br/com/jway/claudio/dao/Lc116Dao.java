package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import br.com.jway.claudio.entidadesOrigem.Lc116;
import br.com.jway.claudio.util.HibernateUtil;

public class Lc116Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;
	
	public Lc116Dao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public Lc116 findByCodigo(String codigo) {
		Query query = sessionFactory.openSession()
				.createQuery("from Lc116 l  " + " where l.codigo = :cod")
				.setParameter("cod", codigo);

		try {
			List<Lc116> entidade = query.list();

			if (entidade.size() > 0) {
				return entidade.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
