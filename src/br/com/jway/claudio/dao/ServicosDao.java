package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.jway.claudio.entidadesOrigem.Servicos;
import br.com.jway.claudio.util.HibernateUtil;
import br.com.jway.claudio.util.Util;

public class ServicosDao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public ServicosDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public Servicos findByIdOrigem(Long id) {
		Query query = sessionFactory.openSession().createQuery("from Servicos s where s.idOrigem = :id")
				.setParameter("id", id);

		try {
			List<Servicos> servicos = query.list();

			if (servicos.size() > 0) {
				return servicos.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Servicos findByCodigo(String codigo) {
		try {
			Query query = sessionFactory.openSession().createQuery("from Servicos e where e.codigo = :codigo")
					.setParameter("codigo", codigo);

			try {
				List<Servicos> servicos = query.list();

				if (servicos.size() > 0) {
					return servicos.get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Servicos findByCodigoServicoCodigoCnae(String codigoServico, String codigoCnae) {

		try {
			Query query = sessionFactory.openSession().createQuery("from Servicos e where e.codigo = :codigoServico and e.cnaes = :codigoCnae")
					.setParameter("codigoServico", codigoServico)
					.setParameter("codigoCnae", codigoCnae);

			try {
				List<Servicos> servicos = query.list();

				if (servicos.size() > 0) {
					return servicos.get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Servicos save(Servicos s){
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.save(s);
			session.getTransaction().commit();
		}
		catch(Exception e2){
			e2.printStackTrace();
			throw e2;
		}
		finally{
			session.close();
		}
		return s;
	}

}
