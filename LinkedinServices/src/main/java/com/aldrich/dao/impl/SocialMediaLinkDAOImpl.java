package com.aldrich.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aldrich.BO.LinkedinInputBO;
import com.aldrich.dao.SocialMediaLinkDAO;
import com.aldrich.entity.Category;
import com.aldrich.entity.CompanyConfirmLocation;
import com.aldrich.entity.CompanyEmployeeProfile;
import com.aldrich.entity.CompanyInfo;
import com.aldrich.entity.CompanyProfile;
import com.aldrich.entity.PeopleStatsInfo;
import com.aldrich.entity.SocialMediaLink;

@Repository
public class SocialMediaLinkDAOImpl implements SocialMediaLinkDAO 
{
	@Autowired
	private SessionFactory sessionFactory;



	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedinInputBO> getEmployeeUniqueIdAndFkCompanyId() 
	{
		Query query=null;
		LinkedinInputBO linkedinInputBO=null;
		List<Object[]> objList=null;
		List<LinkedinInputBO> linkedinInputBOList=null;
		try
		{
			linkedinInputBOList=new ArrayList<LinkedinInputBO>();
			query = this.sessionFactory.openSession().createSQLQuery("SELECT fk_company_id,unique_id FROM socialmedia_link where fk_company_id=144012 and fk_link_type_info_id=4;");
			objList=query.list();
			if(objList!=null && objList.size()>0)
			{
				for(Object[] list:objList)
				{
					try
					{
						linkedinInputBO=new LinkedinInputBO();
						linkedinInputBO.setPaseID(Long.parseLong(list[0].toString()));
						linkedinInputBO.setUnique_id((list[1].toString()));
						
						linkedinInputBOList.add(linkedinInputBO);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return linkedinInputBOList;
	}
	
	
	@Override
	public void updateCompanyType(Long companyId, String type) {
		try {
			Query query = this.sessionFactory.openSession()
					.createQuery("update Company model set model.redirectedUrl =:redirectedUrl where model.id =:id");
			query.setParameter("id", companyId);
			query.setParameter("redirectedUrl", type);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getCompanyDomainById(long comanyid) {
		String company_domain = (String) this.sessionFactory.openSession()
				.createSQLQuery("select c.domain from company c where c.id=" + comanyid).uniqueResult();
		return company_domain;

	}

	
	@Override
	public Object save(CompanyEmployeeProfile companyEmployeeProfile) {
		return this.sessionFactory.openSession().save(companyEmployeeProfile);
	}
	
	
	@SuppressWarnings("nls")
	@Override
	public void updateActiveStatus(CompanyEmployeeProfile companyEmployeeProfile,Long fk_company_id) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyEmployeeProfile model set model.active =:active where model.fk_company_id =:fk_company_id ");
			query.setParameter("fk_company_id", fk_company_id);
			query.setParameter("active", companyEmployeeProfile.getActive());
				
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("nls")
	@Override
	public void updateActiveStatusByMemberLevel(CompanyEmployeeProfile companyEmployeeProfile,Long fk_company_id,String full_name,String title) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyEmployeeProfile model set model.fk_company_id =:fk_company_id , model.full_name =:full_name ,model.title =:title, model.active =:active  where model.full_name =:full_name ");
			query.setParameter("fk_company_id", fk_company_id);
			query.setParameter("full_name", full_name);
			query.setParameter("title", title);
			query.setParameter("active", companyEmployeeProfile.getActive());
				
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyEmployeeProfile> checkForExistance(Long fk_company_id, String full_name, String title) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select model from CompanyEmployeeProfile model where model.fk_company_id =:fk_company_id and full_name =:full_name and title =:title");
		query.setParameter("fk_company_id", fk_company_id);
		query.setParameter("full_name", full_name);
		query.setParameter("title", title);
		return query.list();
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getEmployeeFullName(String full_name,Long fk_company_id,String title) {
		Query query = this.sessionFactory.openSession()
				.createSQLQuery("select fk_company_id,full_name,title from lavu_employees where full_name='" + full_name+ "' and fk_company_id='" + fk_company_id+ "' and title='" + title+ "' ;");
		return query.list();
	}
	
	
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
					"select fk_company_id,link,domain from socialmedia_link_gcse_info_new1 where link_type='linkedin' and fk_company_id IN (select id from company where notes='txn');");
			objList = query.list();
			if (objList != null && objList.size() > 0) {
				for (Object[] list : objList) {
					try {
						linkedinInputBO = new LinkedinInputBO();
						linkedinInputBO.setPaseID(Long.parseLong(list[0].toString()));
						linkedinInputBO.setLinkedinURL(list[1].toString());
						linkedinInputBO.setDomain((list[2].toString()));
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
	public Object save(CompanyInfo companyInfo) {
		return this.sessionFactory.openSession().save(companyInfo);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyInfo> checkForExistance(Long fk_company_id) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select model from CompanyEmployeeProfile model where model.fk_company_id =:fk_company_id ");
		query.setParameter("fk_company_id", fk_company_id);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SocialMediaLink> checkForExistanceByCompanyIdAndLinkTypeId(Long companyId, Long linkTypeInfoId) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select model from SocialMediaLink model where model.fkCompanyId =:companyId and model.fkLinkTypeInfoId =:linkTypeInfo");
		query.setParameter("companyId", companyId);
		query.setParameter("linkTypeInfo", linkTypeInfoId);
		return query.list();
	}
	
	@Override
	public Object save(SocialMediaLink socialMediaLinkDtls) {
		return this.sessionFactory.openSession().save(socialMediaLinkDtls);
	} // ExtractSocialMediaLinkService ends...
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> checkForExistance(String categoryName) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select model from Category model where model.code = :categoryName");
		query.setParameter("categoryName", categoryName);
		return query.list();
	}

	@Override
	public Object save(Category category) {
		return this.sessionFactory.openSession().save(category);
	}
	
	@Override
	public Object save(CompanyConfirmLocation info) {
		return this.sessionFactory.openSession().save(info);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> checkForExistance(int compId, CompanyConfirmLocation info) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select status.id from CompanyConfirmLocation status where status.fk_company_id = :compId and status.city = :city and status.state = :state and status.country = :country and status.postal_code = :zipcode");
		query.setParameter("compId", compId);
		query.setParameter("city", info.getCity());
		query.setParameter("state", info.getState());
		query.setParameter("country", info.getCountry());
		query.setParameter("zipcode", info.getPostal_code());
		return query.list();
	}

	@Override
	public void updateCompanyProfileFromLinkedin(Long companyId, CompanyProfile proinfo) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.empCode=:empCode,model.employeeSize=:employeeSize,model.empCount=:empCount,model.location=:location,model.city=:city,model.state=:state,model.country=:country,model.zipCode=:zipCode,model.phoneNumber=:phoneNumber,model.description=:description,model.specialties=:specialties,model.foundedYear=:foundedYear,model.fkCategoryId=:fkCategoryId where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("empCode", proinfo.getEmpCode());
			query.setParameter("employeeSize", proinfo.getEmployeeSize());
			query.setParameter("empCount", proinfo.getEmpCount());
			query.setParameter("location", proinfo.getLocation());
			query.setParameter("city", proinfo.getCity());
			query.setParameter("state", proinfo.getState());
			query.setParameter("country", proinfo.getCountry());
			query.setParameter("zipCode", proinfo.getZipCode());
			query.setParameter("phoneNumber", proinfo.getPhoneNumber());
			query.setParameter("description", proinfo.getDescription());
			query.setParameter("specialties", proinfo.getSpecialties());
			query.setParameter("foundedYear", proinfo.getFoundedYear());
			query.setParameter("fkCategoryId", proinfo.getFkCategoryId());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//SELECT * FROM pase_local.linkedin_jobs_data_input;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedinInputBO> getEmployeeUniqueNameAndFkCompanyId() {
		Query query = null;
		LinkedinInputBO linkedinInputBO = null;
		List<Object[]> objList = null;
		List<LinkedinInputBO> linkedinInputBOList = null;
		try {
			linkedinInputBOList = new ArrayList<LinkedinInputBO>();
			// uniqueId's Query For salesNavigator
			query = this.sessionFactory.openSession().createSQLQuery(
					"SELECT * FROM pase_local.linkedin_jobs_data_input;");
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
	public Object save(PeopleStatsInfo info) {
		return this.sessionFactory.openSession().save(info);
	}

	@SuppressWarnings({ "nls", "unchecked" })
	@Override
	public List<PeopleStatsInfo> checkForExistanceByCompanyId(Integer companyId, String name, String type) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select model from PeopleStatsInfo model where model.fk_company_id =:companyId and name =:name and type =:type");
		query.setParameter("companyId", companyId);
		query.setParameter("name", name);
		query.setParameter("type", type);
		return query.list();
	}

	@Override
	public void updateDetails(PeopleStatsInfo info) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update PeopleStatsInfo model set model.total_emp_count =:total_emp_count,model.value =:value,model.emp_size =:emp_size,model.group_name =:group_name,model.per_value =:per_value,model.activity_datetime =:activity_datetime where model.fk_company_id = :fk_company_id AND model.name = :name AND model.type = :type");
			query.setParameter("total_emp_count", info.getTotal_emp_count());
			query.setParameter("value", info.getValue());
			query.setParameter("emp_size", info.getEmp_size());
			query.setParameter("fk_company_id", info.getFk_company_id());
			query.setParameter("name", info.getName());
			query.setParameter("type", info.getType());
			query.setParameter("group_name", info.getGroup_name());
			query.setParameter("per_value", info.getPer_value());
			query.setParameter("activity_datetime", info.getActivity_datetime());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDetailsNew(BigInteger id, String gname, double pervalue) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update PeopleStatsInfo model set model.group_name =:group_name,model.per_value =:per_value where model.id = :id");
			query.setParameter("id", id);
			query.setParameter("group_name", gname);
			query.setParameter("per_value", pervalue);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getCategoryId(long fkCompanyId) {
		Query query = this.sessionFactory.openSession().createQuery(
				"select model.fkCategoryId from CompanyProfile model where model.fkCompanyId = :fkCompanyId");
		query.setParameter("fkCompanyId", fkCompanyId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompaniesList() {
		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select id,total_emp_count,name,value,type from people_stats_info where per_value is null order by id;");
		return query.list();
	}

}
