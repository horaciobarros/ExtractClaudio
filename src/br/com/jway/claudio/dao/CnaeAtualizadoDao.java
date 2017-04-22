package br.com.jway.claudio.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.jway.claudio.entidadesOrigem.CnaeAtualizado;
import br.com.jway.claudio.util.HibernateUtil;
import br.com.jway.claudio.util.Util;

public class CnaeAtualizadoDao {
	
	private Util util = new Util();

	StringBuilder hql;
	private SessionFactory sessionFactory;

	public CnaeAtualizadoDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public CnaeAtualizado findByCodigo(String codigo) {
		Session session = sessionFactory.openSession();
		List<CnaeAtualizado> cnaes = new ArrayList<>();
		
		if (codigo.length() == 5){
			codigo = "0"+codigo;
		}
		if (codigo.length()==6){
			String codigo4 = codigo.substring(0,4);
			String codigo2 = codigo.substring(4);
			Query query = session.createQuery("from CnaeAtualizado c where c.cnae like :cnae4 and c.cnae like :cnae2 ")
					.setParameter("cnae4", codigo4 +"%").setParameter("cnae2", "%" + codigo2);
			cnaes = query.list();
		} 
		else{
			Query query = session.createQuery("from CnaeAtualizado c where c.cnae like :cnae")
					.setParameter("cnae", codigo);
			cnaes = query.list();
		}		
		try {
			if (cnaes.size() > 0) {
				return cnaes.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return null;
	}

}
