package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.model.NotasFiscaisSubst;
import br.com.jway.claudio.util.HibernateUtil;

public class NotasFiscaisSubstDao {
	
	StringBuilder hql;
	private SessionFactory sessionFactory;
	
	public NotasFiscaisSubstDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void save(NotasFiscaisSubst nfc) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(nfc);
		session.beginTransaction().commit();
		session.close();
	}
	
	public List<NotasFiscaisSubst> findNaoEnviados() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("from NotasFiscaisSubst c where hash is null").setFirstResult(0).setMaxResults(1000);
		List<NotasFiscaisSubst> lista = query.list();
		tx.commit();session.close();

		return lista;
	}

	public void saveHash(List<NotasFiscaisSubst> listaAtualizados, String hash){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("update NotasFiscaisSubst set hash = '"+hash+"' where ");
		
		for (NotasFiscaisSubst c : listaAtualizados){
			builder.append("id = "+c.getId()+" or ");
		}
		
		String sql = builder.toString();
		sql = sql.toString().substring(0,sql.length()-4);
		Query query = session.createQuery(sql);
		query.executeUpdate();
		tx.commit();session.close();
	}

}
