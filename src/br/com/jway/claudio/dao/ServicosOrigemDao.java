package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.jway.claudio.entidadesOrigem.EscrituracoesOrigem;
import br.com.jway.claudio.entidadesOrigem.ServicosOrigem;
import br.com.jway.claudio.util.HibernateUtil;
import br.com.jway.claudio.util.Util;

public class ServicosOrigemDao {

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public ServicosOrigemDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public ServicosOrigem findByIdOrigem(Long id) {
		Query query = sessionFactory.openSession().createQuery("from ServicosOrigem s where s.idOrigem = :id")
				.setParameter("id", id);

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

	public ServicosOrigem findByCodigo(String codigo) {
		Integer aux = Util.castToInteger(codigo);
		try {
			codigo = Integer.toString(aux);
			Query query = sessionFactory.openSession().createQuery("from ServicosOrigem e where e.codigo = :codigo")
					.setParameter("codigo", codigo);

			try {
				List<ServicosOrigem> servicos = query.list();

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
	
	public ServicosOrigem findByCodigoServicoCodigoCnae(String codigoServico, String codigoCnae) {
		Integer aux = Util.castToInteger(codigoServico);
		try {
			codigoServico = Integer.toString(aux);
			Query query = sessionFactory.openSession().createQuery("from ServicosOrigem e where e.codigo = :codigoServico and e.cnaes = :codigoCnae")
					.setParameter("codigoServico", codigoServico)
					.setParameter("cnaes", codigoCnae);

			try {
				List<ServicosOrigem> servicos = query.list();

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
	
	public ServicosOrigem save(ServicosOrigem s){
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
