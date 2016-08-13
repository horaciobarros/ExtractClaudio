package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.util.HibernateUtil;

public class EscrituracoesOrigemDao {
	
	StringBuilder hql;
	private SessionFactory sessionFactory;

	public EscrituracoesOrigemDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public EscrituracoesOrigem findById(String id) {
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

}
