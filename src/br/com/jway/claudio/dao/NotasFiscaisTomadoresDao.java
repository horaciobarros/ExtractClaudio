package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.model.NotasFiscaisTomadores;
import br.com.jway.claudio.util.HibernateUtil;

public class NotasFiscaisTomadoresDao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public NotasFiscaisTomadoresDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public void save(NotasFiscaisTomadores nft) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(nft);
			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}

	}

	public List<NotasFiscaisTomadores> findNaoEnviados() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from NotasFiscaisTomadores nft where hash is null").setFirstResult(0)
				.setMaxResults(1000);
		List<NotasFiscaisTomadores> lista = query.list();
		tx.commit();
		session.close();

		return lista;
	}

	public void saveHash(List<NotasFiscaisTomadores> listaAtualizados, String hash) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("update NotasFiscaisTomadores set hash = '" + hash + "' where ");

		for (NotasFiscaisTomadores nft : listaAtualizados) {
			builder.append("id = " + nft.getId() + " or ");
		}

		String sql = builder.toString();
		sql = sql.toString().substring(0, sql.length() - 4);
		Query query = session.createQuery(sql);
		query.executeUpdate();
		tx.commit();
		session.close();
	}

	public NotasFiscaisTomadores findByNumeroGuia(String nossoNumero) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("from NotasFiscaisTomadores nft where numeroGuia = '" + nossoNumero.trim() + "'");
		List<NotasFiscaisTomadores> lista = query.list();
		tx.commit();
		session.close();

		if (lista.size() > 0) {
			return lista.get(0);
		} else {
			return null;
		}
	}

}
