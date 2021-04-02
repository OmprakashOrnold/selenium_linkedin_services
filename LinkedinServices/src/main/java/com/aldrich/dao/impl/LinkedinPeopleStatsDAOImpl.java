package com.aldrich.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aldrich.BO.LinkedinInputBO;
import com.aldrich.dao.LinkedinPeopleStatsDAO;
import com.aldrich.entity.DataExtractionStatusInfo;
import com.aldrich.entity.PeopleStatsInfo;

@Repository
public class LinkedinPeopleStatsDAOImpl implements LinkedinPeopleStatsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedinInputBO> getLinkedinLinks() {
		Query query = null;
		LinkedinInputBO linkedinInputBO = null;
		List<Object[]> objList = null;
		List<LinkedinInputBO> linkedinInputBOList = null;
		try {
			linkedinInputBOList = new ArrayList<LinkedinInputBO>();
			// uniqueId's Query For salesNavigator
			query = this.sessionFactory.openSession().createSQLQuery(
					"SELECT * FROM pase_local.people_stats_input;");
			objList = query.list();
			if (objList != null && objList.size() > 0) {
				for (Object[] list : objList) {
					try {
						linkedinInputBO = new LinkedinInputBO();
						linkedinInputBO.setPaseID(Long.parseLong(list[0].toString()));
						linkedinInputBO.setLinkedinURL(list[1].toString());
						linkedinInputBOList.add(linkedinInputBO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return linkedinInputBOList;
	}	

	@Override
	public Object saveStatus(DataExtractionStatusInfo info) {
		return this.sessionFactory.openSession().save(info);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> checkForExistance(Integer compId, String type) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select status.id from DataExtractionStatusInfo status where status.fk_company_id = :compId and status.type = :type");
		query.setParameter("compId", compId);
		query.setParameter("type", type);
		return query.list();
	}

	@Override
	public Object saveStats(PeopleStatsInfo info) {
		return this.sessionFactory.openSession().save(info);
	}

	@SuppressWarnings({ "nls", "unchecked" })
	@Override
	public List<PeopleStatsInfo> checkForExistanceByCompanyId(Integer companyId, String name) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select model from PeopleStatsInfo model where model.fk_company_id =:companyId and name =:name");
		query.setParameter("companyId", companyId);
		query.setParameter("name", name);
		return query.list();
	}

}
