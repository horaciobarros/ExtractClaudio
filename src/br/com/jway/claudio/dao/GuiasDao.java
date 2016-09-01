package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.model.Guias;
import br.com.jway.claudio.util.HibernateUtil;

public class GuiasDao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public GuiasDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public void save(Guias g) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(g);
			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	public List<Guias> findNaoEnviados() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Guias c where hash is null").setFirstResult(0).setMaxResults(1000);
		List<Guias> lista = query.list();
		tx.commit();
		session.close();

		return lista;
	}

	public void saveHash(List<Guias> listaAtualizados, String hash) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("update Guias set hash = '" + hash + "' where ");

		for (Guias c : listaAtualizados) {
			builder.append("id = " + c.getId() + " or ");
		}

		String sql = builder.toString();
		sql = sql.toString().substring(0, sql.length() - 4);
		Query query = session.createQuery(sql);
		query.executeUpdate();
		tx.commit();
		session.close();
	}

	public Guias findByNumeroGuia(String nossoNumero) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Guias c where numeroGuia = '" + nossoNumero.trim() + "'");
		List<Guias> lista = query.list();
		tx.commit();
		session.close();

		if (lista.size() > 0) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	public Guias findByIdGuiaRecolhimento(String id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Guias c where idGuiaRecolhimento = '" + id.trim() + "'");
		List<Guias> lista = query.list();
		tx.commit();
		session.close();

		if (lista.size() > 0) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	public List<Guias> findAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Guias g ");
		List<Guias> lista = query.list();
		tx.commit();
		session.close();

		return lista;
	}

}
