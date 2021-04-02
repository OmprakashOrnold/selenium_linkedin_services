package com.aldrich.dao;

import java.util.List;

import com.aldrich.entity.CompanyProfile;

public interface CompanyProfileDAO {
	public List<CompanyProfile> checkForExistanceByInvestorCompanyId(Long investorCompanyId);

	public Object save(CompanyProfile companyProfile);

	public List<Object[]> getAllCompaniesProfilesDetails();

	public List<Object[]> getClients(String companyId);

	public List<Object[]> getCompetetors(String companyId);

	public List<Object[]> getCompanySection1Data(String companyId);

	public List<Object[]> getCompanyScoreAndRank(String companyId);

	public void update(CompanyProfile companyProfile);

	public List<String> getEmployeeCountByCompanyId(Long companyId);

	public List<CompanyProfile> checkForExistanceByCompanyId(Long companyId);

	public List<Object[]> getLocationsByStartsWith(String keyword);

	public void updateEmployeeRange(Long companyId, String empRange, String empCode);

	public List<Object[]> getCompanyBasicInfo(String companyId);

	public List<CompanyProfile> getCompaniesByStage(String stage);

	public void updateEmployeeCount(Long companyId, int empCount);

	public String getCompanyScoringByCompanyId(Long companyId);

	public List<CompanyProfile> getCompaniesBySubCategory(long subCategoryId);

	public void updateCompanyProfileFromLinkedin(Long companyId, CompanyProfile proinfo);

	public void updateDtls(String domain, String type, String count, String industry, String geo);

	public List<CompanyProfile> getCompanyProfileInfo(Long companyId);

	public void updateCountry(Long companyId, String country);

	public void updateState(Long companyId, String state);

	public void updateCity(Long companyId, String city);

	public void updateLocation(Long companyId, String location);

	public void updateZipCode(Long companyId, String zipCode);

	public void updateShortDescAndCBTags(Long companyId, String short_desc, String cbtags, String emailId,
			String phoneNumber);

	public void updateFoundedYear(Long companyId, Integer foundedYear);
	
	public void updateDescription(Long companyId, String description);

}
