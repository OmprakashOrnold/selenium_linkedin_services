package com.aldrich.pase.vo;

import lombok.Getter;
import lombok.Setter;

public class CompanyDomainVO
{
	@Setter
	@Getter
	private Long companyId;

	@Setter
	@Getter
	private String domain;

	@Setter
	@Getter
	private String redirectedDomain;
	
	
	@Setter
	@Getter
	private String companyName;
	
	@Setter
	@Getter
	private String url;

	@Setter
	@Getter
	private String redirectURL;

	@Setter
	@Getter
	private boolean saved;
	
	@Setter
	@Getter
	private String apiKey;
}
