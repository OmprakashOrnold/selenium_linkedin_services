package com.aldrich.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aldrich.dao.CompanyConfirmLocationDAO;
import com.aldrich.entity.CompanyConfirmLocation;

@Repository
public class CompanyConfirmLocationDAOImpl implements CompanyConfirmLocationDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Object save(CompanyConfirmLocation info) {
		return this.sessionFactory.openSession().save(info);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> checkForExistance(Long compId, CompanyConfirmLocation info) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select status.id from CompanyConfirmLocation status where status.fk_company_id = :compId and status.city = :city and status.state = :state and status.country = :country and status.postal_code = :zipcode");
		query.setParameter("compId", compId);
		query.setParameter("city", info.getCity());
		query.setParameter("state", info.getState());
		query.setParameter("country", info.getCountry());
		query.setParameter("zipcode", info.getPostal_code());
		return query.list();
	}
}
