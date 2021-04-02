package com.aldrich.dao;

import java.math.BigInteger;
import java.util.List;

import com.aldrich.BO.LinkedinInputBO;
import com.aldrich.entity.Category;
import com.aldrich.entity.CompanyConfirmLocation;
import com.aldrich.entity.CompanyEmployeeProfile;
import com.aldrich.entity.CompanyInfo;
import com.aldrich.entity.CompanyProfile;
import com.aldrich.entity.PeopleStatsInfo;
import com.aldrich.entity.SocialMediaLink;

public interface SocialMediaLinkDAO 
{
	public Object save(CompanyEmployeeProfile companyEmployeeProfile);

	public List<CompanyEmployeeProfile> checkForExistance(Long companyId, String fullname, String title);

	public List<Object[]> getEmployeeFullName(String full_name, Long fk_company_id, String title);

	public void updateActiveStatus(CompanyEmployeeProfile companyEmployeeProfile,Long fkCompanyId);
	
	public void updateActiveStatusByMemberLevel(CompanyEmployeeProfile companyEmployeeProfile,Long fkCompanyId,String personName,String designstion);

	public List<LinkedinInputBO> getLinkedinLinks();

	public List<LinkedinInputBO> getEmployeeUniqueIdAndFkCompanyId();

	public Object save(CompanyInfo companyInfo);

	public List<CompanyInfo> checkForExistance(Long fk_company_id);

	public List<SocialMediaLink> checkForExistanceByCompanyIdAndLinkTypeId(Long companyId, Long linkTypeInfoId);

	public Object save(SocialMediaLink socialMediaLinkDtls);

	public String getCompanyDomainById(long comanyid);

	public void updateCompanyType(Long companyId, String type);

	public List<Category> checkForExistance(String categoryName);

	public Object save(Category category);

	public Object save(CompanyConfirmLocation info);

	public List<Long> checkForExistance(int compId, CompanyConfirmLocation info);

	public void updateCompanyProfileFromLinkedin(Long companyId, CompanyProfile proinfo);

	public List<LinkedinInputBO> getEmployeeUniqueNameAndFkCompanyId();

	public Object save(PeopleStatsInfo info);

	public List<PeopleStatsInfo> checkForExistanceByCompanyId(Integer companyId, String name, String type);

	public void updateDetails(PeopleStatsInfo info);

	public void updateDetailsNew(BigInteger id, String gname, double pervalue);

	public List<Long> getCategoryId(long fkCompanyId);

	public List<Object[]> getCompaniesList();

}
