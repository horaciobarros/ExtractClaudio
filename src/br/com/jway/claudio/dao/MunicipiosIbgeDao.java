package br.com.jway.claudio.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import br.com.jway.claudio.model.MunicipiosIbge;
import br.com.jway.claudio.util.HibernateUtil;

public class MunicipiosIbgeDao  {
	
	StringBuilder hql;
	private SessionFactory sessionFactory;

	public MunicipiosIbgeDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	
	public String getCodigoIbge(String nomeMunicipio, String uf) {
		Query query = sessionFactory.openSession()
				.createQuery("from MunicipiosIbge m  " + " where upper(m.municipio) like '%" + nomeMunicipio.trim().toUpperCase() + "%' "
						+ " and upper(m.uf) like '%" + uf.trim().toUpperCase() + "%'");

		try {
			List<MunicipiosIbge> municipios = query.list();

			if (municipios.size() > 0) {
				return municipios.get(0).getCodigo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public String findUfByCodigoIbge(Long codigo) {
		Query query = sessionFactory.openSession()
				.createQuery("from MunicipiosIbge m where m.codigo = " + codigo 
						);

		try {
			List<MunicipiosIbge> municipios = query.list();

			if (municipios.size() > 0) {
				return municipios.get(0).getUf();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
