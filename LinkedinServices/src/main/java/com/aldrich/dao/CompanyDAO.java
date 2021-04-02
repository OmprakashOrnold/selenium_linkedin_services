package com.aldrich.dao;

import java.util.List;

import com.aldrich.entity.Company;
import com.aldrich.pase.vo.CompanyDomainVO;
import com.aldrich.pase.vo.CrunchbaseCompanyDetailsVO;
import com.aldrich.pase.vo.UrlRedirectionVO;

public interface CompanyDAO {
	public List<Company> checkForExistanceByCompanyDomainName(String domainName);

	public List<Object[]> getAllCompaniesDetails();

	public Object save(Company company);

	public List<Company> checkForExistanceByCompanyName(String name);

	public List<Object[]> getCompaniesByStartsWith(String keyword);

	public List<Object[]> countCompanies();

	public List<Object[]> getSampleTop5CompaniesforAPI();

	public List<Object[]> getSampleTop5CompaniesforAPI(Integer sectorId);

	public List<Object[]> getCompanyDetails();

	public List<Object[]> getCompanyDetails(Long id);

	public List<Long> getExceptionFiredURI();

	public List<CompanyDomainVO> getCompanyDomains();

	public List<Object[]> getSampleTop5CompaniesforAPI(Integer sectorId, String type);

	public int updateRedirectionFields(UrlRedirectionVO objURLRedirectionVO);

	public List<CompanyDomainVO> getCompanyDomainsForAll();

	public List<Long> checkForExistanceByDomain(String domain);

	public void updateDetails(Long companyId, String status);

	public Long getCompanyIdByDomain(String domain);

	public void updateCompanyRelavancy(Long companyId, String status);

	public List<Object[]> getCompanyBasicInfo(long companyId);

	public List<Company> getAllCompanyInfo();

	public CrunchbaseCompanyDetailsVO getCrunchbaseCompanyDetails(int companyID);

	public int updateURL(String actualURL, Long id, String websiteStatus);

	public long checkCompanyExistence(String company);

	public List<Company> getCompanyData();

	public List<Object[]> getAllCompanyDetails();

	public List<Long> checkForExistanceByDomainId();

	public void updateScore(Company company, Long companyId);

	public List<Company> getCompanyScoreByPartnerId(Long Id);

	public String getCompanyDomainById(long comanyid);

	public void updateCompanyType(Long companyId, String type);

	public List<CompanyDomainVO> getAdsSpendDomains();
	// added for new apis

	public List<Object[]> getTopCompaniesforNewAPI();

	public List<Object[]> getTopCompaniesforNewAPI(Integer sectorId);

	public List<Object[]> getTopCompaniesforNewAPI(String companyName, String fundingReceived, String noOfEmp,
			String state, String city, String zip, String airportCode, String dealStage);

	public List<Object[]> getTopCompaniesforNewAPI(Integer sectorId, String companyName, String fundingReceived,
			String noOfEmp, String state, String city, String zip, String airportCode, String dealStage);

	public void updateProductServiceCompany(Long companyId, String result);

	public void updateProductService(Integer companyId, String value);
	
	public List<Object[]> getCheckIfStagesCompany(Integer pase_id);

}
