package com.aldrich.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aldrich.dao.CompanyDAO;
import com.aldrich.entity.Company;
import com.aldrich.pase.vo.CompanyDomainVO;
import com.aldrich.pase.vo.CrunchbaseCompanyDetailsVO;
import com.aldrich.pase.vo.UrlRedirectionVO;
import com.aldrich.util.PASEConstants;

@Repository
public class CompanyDAOImpl implements CompanyDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class.getName());

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> checkForExistanceByCompanyDomainName(String domainName) {
		List<String> validCompaniesList = new ArrayList<>();
		validCompaniesList.add(PASEConstants.VALID_TRACKING_COMPANY);
		validCompaniesList.add(PASEConstants.VALID_NOT_TRACKING_COMPANY);
		Query query = this.sessionFactory.openSession().createQuery(
				"select model from Company model where model.domain =:domainName and model.relevant in(:validCompaniesList) ");
		query.setParameter("domainName", domainName);
		query.setParameterList("validCompaniesList", validCompaniesList);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> checkForExistanceByCompanyName(String name) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select model from Company model where model.name =:name");
		query.setParameter("name", name);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllCompaniesDetails() {
		List<String> list = new ArrayList<>();
		list.add("yes-tracking");
		Query query = this.sessionFactory.openSession().createQuery(
				"select model.id,model.name from Company model where model.relevant in(:list) order by model.id");
		query.setParameterList("list", list);
		return query.list();
	}

	@Override
	public Object save(Company company) {
		return this.sessionFactory.openSession().save(company);
	}

	@Override
	public void updateDetails(Long companyId, String status) {
		try {
			Query query = this.sessionFactory.openSession()
					.createQuery("update Company model set model.relevant =:relevant where model.id =:id");
			query.setParameter("id", companyId);
			query.setParameter("relevant", status);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProductService(Integer companyId, String value) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update Company model set model.redirectedDomain =:redirectedDomain where model.id =:id");
			query.setParameter("id", companyId);
			query.setParameter("redirectedDomain", value);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompaniesByStartsWith(String keyword) {
		String bindingValue = keyword + "%";
		Query query = this.sessionFactory.openSession().createQuery(
				"select model.id,model.name,model.tracking from Company model where (model.relevant='yes-tracking') and model.name like '"
						+ bindingValue + "'");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getSampleTop5CompaniesforAPI(Integer sectorId, String type) {
		Query query = null;
		if (sectorId == 0) {
			query = this.sessionFactory.openSession().createSQLQuery(
					"select c.id,c.name,c.url,(select ct.name from category ct where ct.id=cp.fk_category_id) as category,(select ct.name from category ct where ct.id=cp.fk_sub_category_id) as subcategory,cp.logo_url,ps.company_score from company c,company_profile cp,pase_score ps where c.id=cp.fk_company_id and c.id=ps.fk_company_id order by ps.company_score desc limit 500;");
		} else {
			query = this.sessionFactory.openSession().createSQLQuery(
					"select c.id,c.name,c.url,(select ct.name from category ct where ct.id=cp.fk_category_id) as category,(select ct.name from category ct where ct.id=cp.fk_sub_category_id) as subcategory,cp.logo_url,ps.company_score from company c,company_profile cp,pase_score ps where c.id=cp.fk_company_id and c.id=ps.fk_company_id and cp.fk_category_id=:sectorId  order by ps.company_score desc limit 500;");
			query.setParameter("sectorId", sectorId);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> countCompanies() {

		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select count(c.id) as count from company c, company_type_mapping ctm where c.id=ctm.fk_company_id and ctm.fk_company_type_id=1;");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getSampleTop5CompaniesforAPI(Integer sectorId) {
		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select c.id,c.name,c.url,(select ct.name from category ct where ct.id=cp.fk_category_id) as category,(select ct.name from category ct where ct.id=cp.fk_sub_category_id) as subcategory,cp.logo_url,ps.company_score from company c,company_profile cp,pase_score ps where c.id=cp.fk_company_id and c.id=ps.fk_company_id and cp.fk_category_id=:sectorId  order by ps.company_score desc limit 250;");

		query.setParameter("sectorId", sectorId);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getSampleTop5CompaniesforAPI() {

		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select c.id,c.name,c.url,(select ct.name from category ct where ct.id=cp.fk_category_id) as category,(select ct.name from category ct where ct.id=cp.fk_sub_category_id) as subcategory,cp.logo_url,ps.company_score from company c,company_profile cp,pase_score ps where c.id=cp.fk_company_id and c.id=ps.fk_company_id order by ps.company_score desc limit 250;");

		return query.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompanyDetails() {
		try {
			List<Object[]> companies = this.sessionFactory.openSession()
					.createQuery(
							"SELECT model.name,model.id,model.domain,model.url from Company model where model.relevant='yes-tracking' order by model.id")
					.list();
			return companies;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllCompanyDetails() {
		try {
			List<Object[]> companies = this.sessionFactory.openSession().createQuery("SELECT model.name,model.id,model.domain,model.url from Company model where model.relevant='yes-tracking' order by model.id").list();
			return companies;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompanyDetails(Long CompID) {
		try {

			Query query = this.sessionFactory.openSession().createQuery(
					"select model.name,model.id,model.domain from Company model where model.id = :companyID");
			query.setParameter("companyID", CompID);
			List<Object[]> companies = query.list();
			return companies;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long checkCompanyExistence(String company) {
		long companyID = 0l;
		Query query = null;
		try {
			query = this.sessionFactory.openSession()
					.createQuery("select model.id from Company model where model.name = :companyName");
			query.setParameter("companyName", company);
			@SuppressWarnings("unchecked")
			List<Object[]> companyList = query.list();
			if (companyList != null && !companyList.isEmpty()) {
				for (Object objCompany : companyList) {
					try {
						companyID = (long) objCompany;
						break;
					} catch (Exception e) {
						LOGGER.error("Exception raised checkCompanyExistence - CompanyDAO :", e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception raised checkCompanyExistence - CompanyDAO :", e.getMessage());
		}
		return companyID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getExceptionFiredURI() {
		try {

			List<Long> employees = this.sessionFactory.openSession()
					.createQuery(
							"select model.fkCompanyId from SocialMedialLink model where model.exceptionCodes is NOT NULL")
					.list();
			return employees;
		} catch (Exception e) {

			e.printStackTrace();
		}
		List<Long> employees = new ArrayList<>();
		return employees;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDomainVO> getCompanyDomains() {

		List<String> list = new ArrayList<>();
		list.add("sf");
		Query query = this.sessionFactory.openSession().createQuery(
				"select model.id,model.domain,model.url,model.redirectedUrl,model.name from Company model where "
						+ "model.tracking in(:list)  order by model.id");
		query.setParameterList("list", list);
		// query.setMaxResults(500);
		List<CompanyDomainVO> CompanyDomainList = new ArrayList<>();
		List<Object[]> objList = query.list();
		for (Object[] obj : objList) {
			try {
				if (obj.length > 1) {
					CompanyDomainVO companyDomainInfo = new CompanyDomainVO();
					companyDomainInfo.setCompanyId((Long) obj[0]);
					companyDomainInfo.setDomain((String) obj[4]);
					companyDomainInfo.setUrl((String) obj[2]);
					if (obj[3] != null)
						companyDomainInfo.setRedirectURL(obj[3].toString());
					CompanyDomainList.add(companyDomainInfo);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return CompanyDomainList;
	}

	@Override
	public int updateRedirectionFields(UrlRedirectionVO objURLRedirectionVO) {
		int status = 0;
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update Company model set model.redirectedUrl =?,model.redirectedDomain=?,model.url =?,model.status=? where model.id =?");
			query.setParameter(0, objURLRedirectionVO.getRedirectedUrl());
			query.setParameter(1, objURLRedirectionVO.getRedirectedDomain());
			query.setParameter(2, objURLRedirectionVO.getUrl());
			query.setParameter(3, objURLRedirectionVO.getWebsiteStatus());
			query.setParameter(4, objURLRedirectionVO.getId());
			status = query.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return status;
	}

	@Override
	public int updateURL(String actualURL, Long id, String websiteStatus) {
		int status = 0;
		try {
			Query query = this.sessionFactory.openSession()
					.createQuery("update Company model set model.url =?,,model.status=? where model.id =?");
			query.setParameter(0, actualURL);
			query.setParameter(1, websiteStatus);
			query.setParameter(2, id);
			status = query.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<CompanyDomainVO> getCompanyDomainsForAll() {

		List<String> list = new ArrayList<String>();
		list.add(PASEConstants.RELEVANT_YES_TRACKING);

		Query query = this.sessionFactory.openSession()
				.createQuery("select model.id,model.domain,model.url from Company model where "
						+ "model.relevant in(:list) order by model.id asc");
		query.setParameterList("list", list);
		List<CompanyDomainVO> CompanyDomainList = new ArrayList<CompanyDomainVO>();
		@SuppressWarnings("unchecked")
		List<Object[]> objList = query.list();
		for (Object[] obj : objList) {
			try {
				if (obj.length > 1) {
					CompanyDomainVO companyDomainInfo = new CompanyDomainVO();
					companyDomainInfo.setCompanyId((Long) obj[0]);
					companyDomainInfo.setDomain((String) obj[1]);
					companyDomainInfo.setUrl((String) obj[2]);
					CompanyDomainList.add(companyDomainInfo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return CompanyDomainList;
	}

	@Override
	public Long getCompanyIdByDomain(String domain) {
		Long companyId = (Long) this.sessionFactory.openSession()
				.createQuery("select c.id from company c where c.domain=" + domain).uniqueResult();
		return companyId;
	}

	@Override
	public String getCompanyDomainById(long comanyid) {
		String company_domain = (String) this.sessionFactory.openSession()
				.createSQLQuery("select c.domain from company c where c.id=" + comanyid).uniqueResult();
		return company_domain;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> checkForExistanceByDomain(String domain) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select model.id from Company model where model.domain = :domainName order by model.id");
		query.setParameter("domainName", domain);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCheckIfStagesCompany(Integer pase_id) {
		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select pase_id, legal_name from salesforce_leads_info where deal_stage_code IN ('Stage-9','Stage-6') and pase_id=:pase_id");
		query.setParameter("pase_id", pase_id);
		return query.list();
	}

	@Override
	public void updateCompanyRelavancy(Long companyId, String status) {
		try {
			Query query = this.sessionFactory.openSession()
					.createQuery("update Company model set model.relevant =:relevant where model.id =:id");
			query.setParameter("id", companyId);
			query.setParameter("relevant", status);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCompanyBasicInfo(long companyId) {
		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select c.id,c.name,c.url,(select ct.name from category ct where ct.id=cp.fk_category_id) as category,(select ct.name from category ct where ct.id=cp.fk_sub_category_id) as subcategory,cp.employee_size,cp.location,cp.city,cp.state,cp.stage from company c,company_profile cp where c.id=cp.fk_company_id and c.id=:companyId");
		query.setParameter("companyId", companyId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getAllCompanyInfo() {
		List<String> list = new ArrayList<>();
		list.add("yes-tracking");

		Query query = this.sessionFactory.openSession().createQuery(
				"select model.id,model.name from Company model where " + "model.relevant in(:list)  order by model.id");
		query.setParameterList("list", list);
		List<Company> CompanyDomainList = new ArrayList<>();
		List<Object[]> objList = query.list();
		for (Object[] obj : objList) {
			try {
				if (obj.length > 1) {
					Company companyDomainInfo = new Company();
					// companyDomainInfo.setCompanyId((Long) obj[0]);
					companyDomainInfo.setId((Long) obj[0]);
					companyDomainInfo.setName((String) obj[1]);
					// companyDomainInfo.setUrl((String) obj[2]);
					CompanyDomainList.add(companyDomainInfo);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return CompanyDomainList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CrunchbaseCompanyDetailsVO getCrunchbaseCompanyDetails(int companyID) {
		List<Object[]> objCompanyList = null;
		CrunchbaseCompanyDetailsVO companyDetails = null;
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"select model.name,model.domain,model.url from Company model where model.id =:companyId");
			query.setParameter("companyId", Long.parseLong(String.valueOf(companyID)));
			objCompanyList = query.list();
			if (objCompanyList != null && !objCompanyList.isEmpty()) {
				for (Object[] objCompany : objCompanyList) {
					try {
						companyDetails = new CrunchbaseCompanyDetailsVO();
						companyDetails.setId(companyID);
						companyDetails.setCompanyName(objCompany[0].toString());
						companyDetails.setDomainName(objCompany[1].toString());
						companyDetails.setUrl(objCompany[2].toString());
					} catch (Exception e) {
						LOGGER.error("Exception raised :", e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception raised :", e.getMessage());
		}
		return companyDetails;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanyData() {
		List<Company> companyList = null;
		try {
			Query query = this.sessionFactory.openSession()
					.createQuery("from Company model where model.status =:status");
			query.setParameter("status", "operating");
			companyList = query.list();
		} catch (Exception e) {
			LOGGER.error("Exception raised in company dao:", e.getMessage());
		}
		return companyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> checkForExistanceByDomainId() {
		@SuppressWarnings("unused")
		List<String> list = new ArrayList<String>();
		Query query = null;
		query = this.sessionFactory.openSession()
				.createQuery("select model.id from Company model where  model.domain=:no");
		query.setParameter("no", PASEConstants.RELEVANT_NO);
		return query.list();
	}

	@Override
	public void updateScore(Company company, Long companyId) {
		try {
			Query query = this.sessionFactory.openSession()
					.createQuery("update Company model set model.score=:score where model.id =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("score", company.getScore());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanyScoreByPartnerId(Long Id) {

		List<Company> list = null;
		Query query = null;
		try {
			query = this.sessionFactory.openSession()
					.createQuery("select model from Company model where model.id =:Id");
			query.setParameter("Id", Id);
			list = query.list();
		} catch (Exception e) {
			LOGGER.error("Exception raised checkCompanyExistence - CompanyDAO :", e.getMessage());
		}
		return list;
	}

	// added for new apis

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTopCompaniesforNewAPI() {

		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select c.id,c.name,c.url,cp.city,cp.state,cp.location,cp.emp_count,c.notes as stage,es.revenue_max,es.revenue_min,(select ct.name from category ct where ct.id=cp.fk_category_id) as category,ps.company_score from company c, company_profile cp, pase_score ps,estimated_revenue es where c.id=cp.fk_company_id and c.id=ps.fk_company_id and c.id= es.fk_company_id order by ps.company_score desc limit 50");
		// query.setParameter("sectorId", 1);

		return query.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTopCompaniesforNewAPI(Integer sectorId) {
		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select c.id,c.name,c.url,cp.city,cp.state,cp.location,cp.emp_count,c.notes as stage,es.revenue_max,es.revenue_min,(select ct.name from category ct where ct.id=cp.fk_category_id) as category,ps.company_score from company c, company_profile cp, pase_score ps,estimated_revenue es where c.id=cp.fk_company_id and c.id=ps.fk_company_id and c.id= es.fk_company_id and cp.fk_category_id=:sectorId order by ps.company_score desc limit 50");

		query.setParameter("sectorId", sectorId);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTopCompaniesforNewAPI(Integer sectorId, String companyName, String fundingReceived,
			String noOfEmp, String state, String city, String zip, String airportCode, String dealStage) {

		String whereClause = "";

		if (companyName != "NA")
			whereClause = " and " + whereClause + "c.name='" + companyName + "'";
		if (noOfEmp != "NA")
			whereClause = whereClause + " and cp.emp_count=" + noOfEmp;
		if (state != "NA")
			whereClause = whereClause + " and cp.state='" + state + "'";
		if (city != "NA")
			whereClause = whereClause + " and cp.city='" + city + "'";
		if (zip != "NA")
			whereClause = whereClause + " and cp.zip_code='" + zip + "'";

		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select c.id,c.name,c.url,cp.city,cp.state,cp.location,cp.emp_count,c.notes as stage,es.revenue_max,es.revenue_min,(select ct.name from category ct where ct.id=cp.fk_category_id) as category,ps.company_score from company c, company_profile cp, pase_score ps,estimated_revenue es where c.id=cp.fk_company_id and c.id=ps.fk_company_id and c.id= es.fk_company_id and cp.fk_category_id=:sectorId "
						+ whereClause + " order by ps.company_score desc limit 50");

		query.setParameter("sectorId", sectorId);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTopCompaniesforNewAPI(String companyName, String fundingReceived, String noOfEmp,
			String state, String city, String zip, String airportCode, String dealStage) {

		String whereClause = "";

		if (companyName != "NA")
			whereClause = " and " + whereClause + "c.name='" + companyName + "'";
		if (noOfEmp != "NA")
			whereClause = whereClause + " and cp.emp_count=" + noOfEmp;
		if (state != "NA")
			whereClause = whereClause + " and cp.state='" + state + "'";
		if (city != "NA")
			whereClause = whereClause + " and cp.city='" + city + "'";
		if (zip != "NA")
			whereClause = whereClause + " and cp.zip_code='" + zip + "'";

		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select c.id,c.name,c.url,cp.city,cp.state,cp.location,cp.emp_count,c.notes as stage,es.revenue_max,es.revenue_min,(select ct.name from category ct where ct.id=cp.fk_category_id) as category,ps.company_score from company c, company_profile cp, pase_score ps,estimated_revenue es where c.id=cp.fk_company_id and c.id=ps.fk_company_id and c.id= es.fk_company_id "
						+ whereClause + " order by ps.company_score desc limit 50");
		// query.setParameter("sectorId", 1);

		return query.list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDomainVO> getAdsSpendDomains() {
		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select c.id,c.name,c.url,c.domain from company c where c.relevant='yes-tracking' order by c.id desc limit 100000;");
		List<CompanyDomainVO> CompanyDomainList = new ArrayList<>();
		List<Object[]> objList = query.list();
		for (Object[] obj : objList) {
			try {
				if (obj.length > 1) {
					CompanyDomainVO companyDomainInfo = new CompanyDomainVO();
					companyDomainInfo.setCompanyId(Long.valueOf(obj[0].toString()));
					companyDomainInfo.setCompanyName(obj[1].toString());
					companyDomainInfo.setUrl(obj[2].toString());
					companyDomainInfo.setDomain(obj[3].toString());
					CompanyDomainList.add(companyDomainInfo);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return CompanyDomainList;
	}

	@Override
	public void updateProductServiceCompany(Long companyId, String result) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update Company model set model.redirectedDomain =:redirectedDomain where model.id =:id");
			query.setParameter("id", companyId);
			query.setParameter("redirectedDomain", result);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
