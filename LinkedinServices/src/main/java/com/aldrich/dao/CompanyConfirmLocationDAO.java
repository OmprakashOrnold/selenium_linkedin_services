package com.aldrich.dao;

import java.util.List;

import com.aldrich.entity.CompanyConfirmLocation;

public interface CompanyConfirmLocationDAO {
	public Object save(CompanyConfirmLocation info);

	public List<Long> checkForExistance(Long compId, CompanyConfirmLocation info);
}
