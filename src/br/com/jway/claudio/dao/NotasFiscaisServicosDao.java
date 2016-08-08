package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.model.NotasFiscaisServicos;
import br.com.jway.claudio.util.HibernateUtil;

public class NotasFiscaisServicosDao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public NotasFiscaisServicosDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public void save(NotasFiscaisServicos nfs) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(nfs);
			session.beginTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	public List<NotasFiscaisServicos> findNaoEnviados() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from NotasFiscaisServicos c where hash is null").setFirstResult(0)
				.setMaxResults(1000);
		List<NotasFiscaisServicos> lista = query.list();
		tx.commit();
		session.close();

		return lista;
	}

	public void saveHash(List<NotasFiscaisServicos> listaAtualizados, String hash) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("update NotasFiscaisServicos set hash = '" + hash + "' where ");

		for (NotasFiscaisServicos c : listaAtualizados) {
			builder.append("id = " + c.getId() + " or ");
		}

		String sql = builder.toString();
		sql = sql.toString().substring(0, sql.length() - 4);
		Query query = session.createQuery(sql);
		query.executeUpdate();
		tx.commit();
		session.close();
	}
}
