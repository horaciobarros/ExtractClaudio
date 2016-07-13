package br.com.jway.claudio.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.model.GuiasNotasFiscais;
import br.com.jway.claudio.util.HibernateUtil;

public class GuiasNotasFiscaisDao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public GuiasNotasFiscaisDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public void save(GuiasNotasFiscais gnf) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(gnf);
		session.beginTransaction().commit();
		session.close();
	}

	public List<GuiasNotasFiscais> findNaoEnviados() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from GuiasNotasFiscais gnf where hash is null").setFirstResult(0).setMaxResults(1000);
		List<GuiasNotasFiscais> lista = query.list();
		tx.commit();
		session.close();

		return lista;
	}

	public void saveHash(List<GuiasNotasFiscais> listaAtualizados, String hash) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("update GuiasNotasFiscais set hash = '" + hash + "' where ");

		for (GuiasNotasFiscais c : listaAtualizados) {
			builder.append("id = " + c.getId() + " or ");
		}

		String sql = builder.toString();
		sql = sql.toString().substring(0, sql.length() - 4);
		Query query = session.createQuery(sql);
		query.executeUpdate();
		tx.commit();
		session.close();
	}

	public List<GuiasNotasFiscais> findPorNumeroGuia(
			Long numeroGuia) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from GuiasNotasFiscais gnf where numeroGuia = " + numeroGuia + "");
		List<GuiasNotasFiscais> lista = query.list();
		tx.commit();
		session.close();

		return lista;
	}

}
