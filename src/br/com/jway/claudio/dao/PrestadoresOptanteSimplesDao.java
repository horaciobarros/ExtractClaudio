package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.jway.claudio.model.PrestadoresOptanteSimples;
import br.com.jway.claudio.util.HibernateUtil;

public class PrestadoresOptanteSimplesDao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public PrestadoresOptanteSimplesDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public void save(PrestadoresOptanteSimples pos) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(pos);
		session.beginTransaction().commit();
		session.close();
	}

	public List<PrestadoresOptanteSimples> findNaoEnviados() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("from PrestadoresOptanteSimples pos where hash is null").setFirstResult(0).setMaxResults(1000);
		List<PrestadoresOptanteSimples> lista = query.list();
		tx.commit();session.close();

		return lista;
	}
	
	public void saveHash(List<PrestadoresOptanteSimples> listaAtualizados, String hash){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("update PrestadoresOptanteSimples set hash = '"+hash+"' where ");
		
		for (PrestadoresOptanteSimples pos : listaAtualizados){
			builder.append("id = "+pos.getId()+" or ");
		}
		
		String sql = builder.toString();
		sql = sql.toString().substring(0,sql.length()-4);
		Query query = session.createQuery(sql);
		query.executeUpdate();
		tx.commit();session.close();
	}

	public void excluiPrestadoresSemNotas() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("delete from Prestadores_optante_simples where "
				+ "inscricao_prestador not in (select n.inscricao_prestador from notas_fiscais n) and "
				+ "inscricao_prestador not in(select g.inscricao_prestador from guias g)");

		String sql = builder.toString();
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		tx.commit();session.close();
		
	}

}
