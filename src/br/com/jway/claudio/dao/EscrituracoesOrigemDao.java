package br.com.jway.claudio.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.util.HibernateUtil;

public class EscrituracoesOrigemDao {
	
	StringBuilder hql;
	private SessionFactory sessionFactory;

	public EscrituracoesOrigemDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public EscrituracoesOrigem findById(Long id) {
		Query query = sessionFactory.openSession()
				.createQuery("from EscrituracoesOrigem e where e.id = :id" 
						).setParameter("id", id);

		try {
			List<EscrituracoesOrigem> escrituracoes = query.list();

			if (escrituracoes.size() > 0) {
				return escrituracoes.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public EscrituracoesOrigem findByIdNotaFiscal(String idNotaFiscal) {
		Query query = sessionFactory.openSession()
				.createQuery("from EscrituracoesOrigem e where e.idNotaFiscal = :idNotaFiscal" 
						).setParameter("idNotaFiscal", idNotaFiscal);

		try {
			List<EscrituracoesOrigem> escrituracoes = query.list();

			if (escrituracoes.size() > 0) {
				return escrituracoes.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public EscrituracoesOrigem findByNotaContribuinte(String numeroNota, String cpfCnpjContribuinte) {
		Query query = sessionFactory.openSession()
				.createQuery("from EscrituracoesOrigem e where e.numeroNota = :numeroNota and e.cpfCnpjContribuinte = :cpfCnpjContribuinte" 
						).setParameter("numeroNota", numeroNota).setParameter("cpfCnpjContribuinte", cpfCnpjContribuinte);

		try {
			List<EscrituracoesOrigem> escrituracoes = query.list();

			if (escrituracoes.size() > 0) {
				return escrituracoes.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EscrituracoesOrigem> findAll() {
		Query query = sessionFactory.openSession()
				.createQuery("from EscrituracoesOrigem e ");

		try {
			List<EscrituracoesOrigem> escrituracoes = query.list();

			return escrituracoes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, EscrituracoesOrigem> findAllMapReturn() {
		List<EscrituracoesOrigem> lista = findAll();
		Map<String, EscrituracoesOrigem> mapEscrituracoesOrigem = new Hashtable<String, EscrituracoesOrigem>();
		for (EscrituracoesOrigem e : lista) {
			mapEscrituracoesOrigem.put(e.getIdNotaFiscal(), e);
		}
		
		return mapEscrituracoesOrigem;
	}
	
	public EscrituracoesOrigem save(EscrituracoesOrigem e) {
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.save(e);
			session.getTransaction().commit();
		}
		catch(Exception e2){
			e2.printStackTrace();
			throw e2;
		}
		finally{
			session.close();
		}
		return e;
	}

}
