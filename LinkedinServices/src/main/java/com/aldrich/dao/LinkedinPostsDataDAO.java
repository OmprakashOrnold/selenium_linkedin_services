package com.aldrich.dao;

import java.util.List;

import com.aldrich.BO.LinkedinInputBO;
import com.aldrich.entity.DataExtractionStatusInfo;

public interface LinkedinPostsDataDAO {

	public List<LinkedinInputBO> getLinkedinLinks();

	public Object saveStatus(DataExtractionStatusInfo info);

	public List<Long> checkForExistance(Integer compId, String type);

}
