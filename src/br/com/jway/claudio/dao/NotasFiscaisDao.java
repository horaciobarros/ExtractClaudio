package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.model.NotasFiscais;
import br.com.jway.claudio.util.HibernateUtil;

public class NotasFiscaisDao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public NotasFiscaisDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public NotasFiscais save(NotasFiscais nf) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(nf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction().commit();
		session.close();
		return nf;
	}

	public List<NotasFiscais> findNaoEnviados() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from NotasFiscais c where hash is null").setFirstResult(0)
				.setMaxResults(500);
		List<NotasFiscais> lista = query.list();
		tx.commit();
		session.close();

		return lista;
	}

	public void saveHash(List<NotasFiscais> listaAtualizados, String hash) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("update NotasFiscais set hash = '" + hash + "' where ");

		for (NotasFiscais c : listaAtualizados) {
			builder.append("id = " + c.getId() + " or ");
		}

		String sql = builder.toString();
		sql = sql.toString().substring(0, sql.length() - 4);
		Query query = session.createQuery(sql);
		query.executeUpdate();
		tx.commit();
		session.close();
	}

	public NotasFiscais findByNumeroDocumentoInscricaoPrestador(String numeroNota, String inscricaoPrestador) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from NotasFiscais nf where numeroNota = '" + numeroNota
				+ "' and inscricaoPrestador = '" + inscricaoPrestador + "'");
		List<NotasFiscais> lista = query.list();
		tx.commit();
		session.close();
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
	
	public NotasFiscais findById(Long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from NotasFiscais nf where id = " + id);
		List<NotasFiscais> lista = query.list();
		tx.commit();
		session.close();
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

}
