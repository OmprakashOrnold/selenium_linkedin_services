package com.aldrich.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aldrich.dao.CompanyProfileDAO;
import com.aldrich.entity.CompanyProfile;

@SuppressWarnings({ "nls", "unchecked", "boxing" })
@Repository
public class CompanyProfileDAOImpl implements CompanyProfileDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<CompanyProfile> checkForExistanceByInvestorCompanyId(Long investorCompanyId) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select model from CompanyProfile model where model.fkCompanyId =:investorCompanyId");
		query.setParameter("investorCompanyId", investorCompanyId);
		return query.list();
	}

	@Override
	public List<CompanyProfile> getCompanyProfileInfo(Long companyId) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select model from CompanyProfile model where model.fkCompanyId =:fkCompanyId");
		query.setParameter("fkCompanyId", companyId);
		return query.list();
	}

	@Override
	public Object save(CompanyProfile companyProfile) {
		return this.sessionFactory.openSession().save(companyProfile);
	}

	@Override
	public List<Object[]> getAllCompaniesProfilesDetails() {
		Query query = this.sessionFactory.openSession().createQuery(
				"select CP.company.id,CP.company.name,CP.company.url,CP.description,CP.specialties,CP.company.domain from "
						+ " CompanyProfile CP where CP.description is not null or CP.specialties is not null");
		return query.list();
	}

	@Override
	public List<Object[]> getClients(String companyId) {

		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select cum.fk_customer_company_id, c.name, cp.logo_url,cp.description FROM customer cum, company c, company_profile cp WHERE cum.fk_customer_company_id=c.id AND cum.fk_customer_company_id=cp.fk_company_id AND cum.fk_company_id=:companyId order by cum.confidence_score desc");

		query.setParameter("companyId", Integer.parseInt(companyId));

		return query.list();
	}

	@Override
	public List<Object[]> getCompetetors(String companyId) {
		String relevant = "no";
		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select  distinct com.fk_competitior_company_id, c.name, cp.logo_url,com.confidence_score,com.fk_source_id,c.domain,cp.description "
						+ "FROM competitior com, company c, company_profile cp, company_type_mapping ctm  " + "WHERE "
						+ "com.fk_competitior_company_id = c.id "
						+ "AND com.fk_competitior_company_id = cp.fk_company_id "
						+ "AND com.fk_company_id = :companyId  " + "AND ctm.fk_company_id = c.id "
						+ "AND ctm.fk_company_type_id not in(4,10)  " + "AND com.fk_source_id not in(266374)"
						+ "AND com.fk_competitior_company_id NOT IN (:companyId) "
						+ "AND c.relevant NOT IN (:relevant) "
						+ "AND c.domain NOT IN (select c1.domain from company c1 where c1.id = :companyId ) order by field(fk_source_id , 263976, 281103, 266374);");

		query.setParameter("companyId", Integer.parseInt(companyId));
		query.setParameter("relevant", relevant);
		return query.list();
	}

	@Override
	public List<Object[]> getCompanySection1Data(String companyId) {

		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select c.id,c.name,c.url,c.domain,cp.description,cp.logo_url,sl.unique_name,sl.fk_link_type_info_id,cp.location,cp.city,cp.state,cp.country,cp.zip_code from company c left outer join company_profile cp on (c.id = cp.fk_company_id) left outer join socialmedia_link sl on (c.id = sl.fk_company_id) WHERE c.id=:companyId AND sl.fk_link_type_info_id IN (1,2,3,4,6) AND sl.exception_code=0");

		query.setParameter("companyId", Integer.parseInt(companyId));

		return query.list();
	}

	@Override
	public List<Object[]> getCompanyBasicInfo(String companyId) {

		Query query = this.sessionFactory.openSession()
				.createSQLQuery("select c.id,c.name,c.url,c.domain from company c WHERE c.id=:companyId");

		query.setParameter("companyId", Integer.parseInt(companyId));

		return query.list();
	}

	@Override
	public void update(CompanyProfile companyProfile) {
		Session session = this.sessionFactory.openSession();

		session.saveOrUpdate(companyProfile);

		session.flush();

	}

	@Override
	public List<Object[]> getCompanyScoreAndRank(String companyId) {

		Query query = this.sessionFactory.openSession().createSQLQuery(
				"select ps.company_score,ps.industry_rank from pase_score ps where fk_company_id=:companyId order by ps.activity_datetime desc");

		query.setParameter("companyId", Integer.parseInt(companyId));

		return query.list();
	}

	@Override
	public List<String> getEmployeeCountByCompanyId(Long companyId) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select CP.employeeSize from CompanyProfile CP where CP.fkCompanyId = :companyId");

		query.setParameter("companyId", companyId);

		return query.list();
	}

	@Override
	public List<CompanyProfile> checkForExistanceByCompanyId(Long companyId) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select model from CompanyProfile model where model.fkCompanyId =:companyId");
		query.setParameter("companyId", companyId);
		return query.list();
	}

	@Override
	public List<Object[]> getLocationsByStartsWith(String keyword) {

		String bindingValue = keyword + "%";

		Query query = this.sessionFactory.openSession().createQuery(
				"select CP.city,CP.state from Company C, CompanyProfile CP where C.id=CP.fkCompanyId and C.tracking='demo' and (CP.city like '"
						+ bindingValue + "' or CP.state like '" + bindingValue + "')");

		return query.list();
	}

	@Override
	public void updateEmployeeRange(Long companyId, String empRange, String empCode) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.empCode=:empCode,model.employeeSize=:employeeSize where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("empCode", empCode);
			query.setParameter("employeeSize", empRange);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CompanyProfile> getCompaniesByStage(String stage) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select model from CompanyProfile model where model.stage =:stage");
		query.setParameter("stage", stage);
		return query.list();
	}

	@Override
	public void updateEmployeeCount(Long companyId, int empCount) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.empCount=:empCount where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("empCount", empCount);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getCompanyScoringByCompanyId(Long companyId) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select CP.employeeSize from CompanyProfile CP where CP.fkCompanyId = :companyId");
		query.setParameter("companyId", companyId);
		return (String) query.uniqueResult();
	}

	@Override
	public List<CompanyProfile> getCompaniesBySubCategory(long subCategoryId) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select model from CompanyProfile model where model.fkSubCategoryId = :fkSubCategoryId");
		query.setParameter("fkSubCategoryId", subCategoryId);
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

	@Override
	public void updateDtls(String domain, String type, String count, String industry, String geo) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update UrlExtractionInfo model set model.type=:type,model.count=:count,model.industry=:industry,model.geography=:geography where model.domain =:domain");
			query.setParameter("domain", domain);
			query.setParameter("type", type);
			query.setParameter("count", count);
			query.setParameter("industry", industry);
			query.setParameter("geography", geo);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCountry(Long companyId, String country) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.country=:country where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("country", country);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateState(Long companyId, String state) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.state=:state where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("state", state);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCity(Long companyId, String city) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.city=:city where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("city", city);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateLocation(Long companyId, String location) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.location=:location where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("location", location);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateZipCode(Long companyId, String zipCode) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.zipCode=:zipCode where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("zipCode", zipCode);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateShortDescAndCBTags(Long companyId, String short_desc, String cbtags, String emailId,
			String phoneNumber) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.short_desc=:short_desc,model.cbTags=:cbTags,model.emailId=:emailId,model.phoneNumber=:phoneNumber where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("short_desc", short_desc);
			query.setParameter("cbTags", cbtags);
			query.setParameter("emailId", emailId);
			query.setParameter("phoneNumber", phoneNumber);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateFoundedYear(Long companyId, Integer foundedYear) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.foundedYear=:foundedYear where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("foundedYear", foundedYear);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDescription(Long companyId, String description) {
		try {
			Query query = this.sessionFactory.openSession().createQuery(
					"update CompanyProfile model set model.description=:description where model.fkCompanyId =:companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("description", description);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
