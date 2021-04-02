package com.aldrich.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aldrich.BO.LinkedinInputBO;
import com.aldrich.dao.LinkedinPostsDataDAO;
import com.aldrich.entity.DataExtractionStatusInfo;

@Repository
public class LinkedinPostsDataDAOImpl implements LinkedinPostsDataDAO{
	
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
			//input fkCompanyId and linkedin unique name
			
			query = this.sessionFactory.openSession().createSQLQuery(
					"SELECT * FROM pase_local.posts_data_input;");
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
}
