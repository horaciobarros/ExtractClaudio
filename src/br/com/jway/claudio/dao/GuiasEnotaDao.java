package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.entidadesOrigem.GuiasEnota;
import br.com.jway.claudio.util.HibernateUtil;

public class GuiasEnotaDao {
	

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public GuiasEnotaDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public List<GuiasEnota> findAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from GuiasEnota g");
		List<GuiasEnota> lista = query.list();
		tx.commit();
		session.close();

		return lista;
	}

}
