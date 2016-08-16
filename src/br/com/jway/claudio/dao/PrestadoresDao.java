package br.com.jway.claudio.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.model.Prestadores;
import br.com.jway.claudio.util.HibernateUtil;

public class PrestadoresDao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public PrestadoresDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public Prestadores findByInscricao(String inscricao) {
		Query query = sessionFactory.openSession()
				.createQuery("from Prestadores pr  " + " where pr.inscricaoPrestador like '%" + inscricao.trim() + "%'");

		try {
			List<Prestadores> prestadores = query.list();

			if (prestadores.size() > 0) {
				return prestadores.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Prestadores findByInscricaoMunicipal(String inscricao) {
		Query query = sessionFactory.openSession()
				.createQuery("from Prestadores pr where pr.inscricaoMunicipal like '%" + inscricao.trim() + "%'");

		try {
			List<Prestadores> prestadores = query.list();

			if (prestadores.size() > 0) {
				return prestadores.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public void save(Prestadores p) {
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.save(p);
			session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		finally{
			session.close();
		}
	}

	public List<Prestadores> findNaoEnviados() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("from Prestadores p where hash is null").setFirstResult(0).setMaxResults(1000);
		List<Prestadores> lista = query.list();
		tx.commit();session.close();

		return lista;
	}
	
	public List<Prestadores> findAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("from Prestadores p ");
		List<Prestadores> lista = query.list();
		tx.commit();
		session.close();

		return lista;
	}

	
	public void saveHash(List<Prestadores> listaAtualizados, String hash){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("update Prestadores set hash = '"+hash+"' where ");
		
		for (Prestadores c : listaAtualizados){
			builder.append("id = "+c.getId()+" or ");
		}
		
		String sql = builder.toString();
		sql = sql.toString().substring(0,sql.length()-4);
		Query query = session.createQuery(sql);
		query.executeUpdate();
		tx.commit();session.close();
	}

	public Map<String, Prestadores> findAllMapReturn() {
		
		List<Prestadores> lista = findAll();
		Map<String, Prestadores> mapPrestadores = new Hashtable<String, Prestadores>();
		for (Prestadores pr : lista) {
			mapPrestadores.put(pr.getInscricaoPrestador(), pr);
		}
		
		return mapPrestadores;
	}

}
