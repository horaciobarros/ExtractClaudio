package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.model.Competencias;
import br.com.jway.claudio.util.HibernateUtil;

public class CompetenciasDao {

	StringBuilder hql;
	private SessionFactory sessionFactory;
	
	public CompetenciasDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public Competencias findByDescricao(String descricao) {
		Query query = sessionFactory.openSession()
				.createQuery("from Competencias cp  " + " where cp.descricao like '%" + descricao.trim() + "%'");

		try {
			List<Competencias> competencias = query.list();

			if (competencias.size() > 0) {
				return competencias.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void save(Competencias cp) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(cp);
		session.beginTransaction().commit();
		session.close();
	}
	
	public List<Competencias> findNaoEnviados() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("from Competencias c where hash is null").setFirstResult(0).setMaxResults(1000);;
		List<Competencias> lista = query.list();
		tx.commit();session.close();

		return lista;
	}

	public void saveHash(List<Competencias> listaAtualizados, String hash){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("update Competencias set hash = '"+hash+"' where ");
		
		for (Competencias c : listaAtualizados){
			builder.append("id = "+c.getId()+" or ");
		}
		
		String sql = builder.toString();
		sql = sql.toString().substring(0,sql.length()-4);
		Query query = session.createQuery(sql);
		query.executeUpdate();
		tx.commit();session.close();
	}
}
