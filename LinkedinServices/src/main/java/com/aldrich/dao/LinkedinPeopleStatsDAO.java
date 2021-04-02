package com.aldrich.dao;

import java.util.List;

import com.aldrich.BO.LinkedinInputBO;
import com.aldrich.entity.DataExtractionStatusInfo;
import com.aldrich.entity.PeopleStatsInfo;

public interface LinkedinPeopleStatsDAO {

	public List<LinkedinInputBO> getLinkedinLinks();

	public Object saveStatus(DataExtractionStatusInfo info);

	public List<Long> checkForExistance(Integer compId, String type);

	public Object saveStats(PeopleStatsInfo info);

	public List<PeopleStatsInfo> checkForExistanceByCompanyId(Integer companyId, String name);

}
