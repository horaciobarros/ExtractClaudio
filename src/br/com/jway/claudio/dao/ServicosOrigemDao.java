package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import br.com.jway.claudio.entidadesOrigem.ServicosOrigem;
import br.com.jway.claudio.util.HibernateUtil;
import br.com.jway.claudio.util.Util;

public class ServicosOrigemDao {
	
	StringBuilder hql;
	private SessionFactory sessionFactory;

	public ServicosOrigemDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public ServicosOrigem findById(Long id) {
		Query query = sessionFactory.openSession()
				.createQuery("from ServicosOrigem s where s.id = :id" 
						).setParameter("id", id);

		try {
			List<ServicosOrigem> servicos = query.list();

			if (servicos.size() > 0) {
				return servicos.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ServicosOrigem findByIdCodigo(String codigo) {
		Integer aux = Util.castToInteger(codigo);
		codigo = Integer.toString(aux);
		Query query = sessionFactory.openSession()
				.createQuery("from ServicosOrigem e where e.codigo = :codigo" 
						).setParameter("codigo", codigo);

		try {
			List<ServicosOrigem> servicos = query.list();

			if (servicos.size() > 0) {
				return servicos.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
