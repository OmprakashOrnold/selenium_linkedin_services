package com.aldrich.dao;

import java.util.List;

import com.aldrich.entity.Jobs;

public interface JobsDAO {
	public void save(Jobs indeedJobs);

	public Jobs checkForExistance(Jobs indeedJobs);

	public List<Jobs> getIndeedJobTitles();

	public List<Jobs> getCompanyJobs(Long companyId);

	public List<Jobs> getCompanyJobsForAPI(Long companyId);
	
	public List<Jobs> getCompanyJobsWithoutTimeFrame(Long companyId);
	
	public void updateJobsValidation(long companyId, String jobUrl);
}
