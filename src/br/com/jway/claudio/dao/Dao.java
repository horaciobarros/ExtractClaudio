package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.jway.claudio.util.HibernateUtil;


public class Dao {

	StringBuilder hql;
	private SessionFactory sessionFactory;
	Session session;

	public Dao() {

		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}
	
	public void excluiDados(String nomeEntidade) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("delete from " + nomeEntidade + " ");
		query.executeUpdate();
		session.beginTransaction().commit();
		session.close();
	}

	public Long count(String nomeEntidade) {
		Query query = sessionFactory.openSession()
				.createQuery("select count(*) from " + nomeEntidade );

		try {
			List<Long> qtdes = query.list();
			return qtdes.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	

	
	
}